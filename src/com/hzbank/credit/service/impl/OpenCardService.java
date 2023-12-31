package com.hzbank.credit.service.impl;

import com.hzbank.credit.bizenum.CardTypeEnum;
import com.hzbank.credit.cons.BizConstant;
import com.hzbank.credit.entity.CampusCard;
import com.hzbank.credit.entity.CashAdvance;
import com.hzbank.credit.entity.CreditCard;
import com.hzbank.credit.mapper.CampusCardMapper;
import com.hzbank.credit.mapper.CashAdvanceMapper;
import com.hzbank.credit.mapper.CreditCardMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.BaseUtil;
import com.hzbank.credit.util.CheckNumber;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * 开卡接口
 */
public class OpenCardService implements BaseService {
    @Override
    public void doBiz() throws Exception {

        System.out.println("=======================欢迎进入信用卡开卡服务======================");
        Scanner in = new Scanner(System.in);
        String campusid = null;
        String idnumber = null;
        String password = null;
        String currency = null;
        CampusCard campus = new CampusCard();
        //输入一卡通账号
        while(true)
        {
            System.out.println("请输入一卡通账号：");
            campusid = in.nextLine();

            //查询一卡通用户是否存在
            SqlSession openssion = MyBatisUtil.getSqlSession();
            CampusCardMapper mapper = openssion.getMapper(CampusCardMapper.class);
            campus = mapper.selectbyid(campusid);
            String pwd = campus.getTransactionPassword();
            byte[] bytes = BaseUtil.decryptBASE64(pwd);

            campus.setTransactionPassword(new String(bytes));

            if(campus==null) {
                System.out.println("用户不存在，请先创建一卡通账号！！");
                return;
            }
            //验证身份证和密码
            System.out.println("请输入身份证号:");
            idnumber = in.nextLine();
            System.out.println("请输入密码：");
            password = in.nextLine();
            if(!campus.getIdNumber().equals(idnumber))
                System.out.println("身份证输入错误");
            else if(!campus.getTransactionPassword().equals(password))
                System.out.println("密码输入错误");
            else
                break;
        }

        //选择币种
        while(true)
        {
            System.out.println("请选择币种：");
            System.out.println("1.美元");
            System.out.println("2.人民币");
            int sl = in.nextInt();
            if (sl == 1) {
                currency = "美元";
                break;
            }
            else if (sl == 2) {
                currency = "人民币";
                break;
            }
            else
                System.out.println("请输入正确选项！！");
        }

        //生成卡号
        String cardid = CheckNumber.generateBankCardNumber("6");
        CreditCard crecard = new CreditCard();
        crecard.setCreditCard(cardid);
        crecard.setAnnualFee(BizConstant.ANNUAL_FEE);
        crecard.setCampusCardNumber(campusid);
        crecard.setTransactionPassword(password);
        crecard.setCreditLimit(BizConstant.CREDIT_LIMIT);
        crecard.setCurrency(currency);
        crecard.setAvailableCredit(BizConstant.AVAILABL_CREDIT);
        crecard.setTransactionCount(0);
        crecard.setCardBalance(0f);
        crecard.setCardStatus(CardTypeEnum.CARD_NORMAL_TYPE.getName());

        //数据库插入卡号
        try {
            SqlSession copenssion = MyBatisUtil.getSqlSession();
            CreditCardMapper cmapper = copenssion.getMapper(CreditCardMapper.class);
            cmapper.insertCredit(crecard);

            // 保存预借现金表
            CashAdvanceMapper cashAdvanceMapper = copenssion.getMapper(CashAdvanceMapper.class);
            CashAdvance cashAdvance = new CashAdvance();
            cashAdvance.setDailyLoanAmount(BizConstant.DAILY_LOAN_AMOUNT);
            cashAdvance.setPreApprovedAmount(BizConstant.CREDIT_LIMIT * BizConstant.PRE_AMOUNT_PERCENT);
            cashAdvance.setCardNumber(cardid);
            cashAdvance.setLoanDate(new Date());
            cashAdvanceMapper.insertCashAdvance(cashAdvance);

            copenssion.commit();
            System.out.println("开卡成功！您的卡号为: " + cardid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.hzbank.credit.service.impl;

import com.hzbank.credit.bizenum.CardTypeEnum;
import com.hzbank.credit.cons.bizconstant;
import com.hzbank.credit.entity.CampusCard;
import com.hzbank.credit.entity.CreditCard;
import com.hzbank.credit.mapper.CampusCardMapper;
import com.hzbank.credit.mapper.CreditCardMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.CheckNumber;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Scanner;

/**
 * 开卡接口
 */
public class OpenCardService implements BaseService {
    @Override
    public void doBiz() {

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

            try {
                //查询一卡通用户是否存在
                SqlSession openssion = MyBatisUtil.getSqlSession();
                CampusCardMapper mapper = openssion.getMapper(CampusCardMapper.class);
                campus = mapper.selectbyid(campusid);
                if(campus==null) {
                    System.out.println("用户不存在，请先创建一卡通账号！！");
                    openssion.close();
                    return;
                }
                else {
                    openssion.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
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
        crecard.setCreditCardID(cardid);
        crecard.setAnnualFee(bizconstant.annualfee);
        crecard.setCampusCardNumber(campusid);
        crecard.setTransactionPassword(password);
        crecard.setCreditLimit(bizconstant.creditLimit);
        crecard.setCurrency(currency);
        crecard.setAvailableCredit(bizconstant.availablcredit);
        crecard.setTransactionCount(0);
        crecard.setCardBalance(0);
        crecard.setCardStaus(CardTypeEnum.CARD_NORMAL_TYPE.getName());

        //数据库插入卡号
        try {
            SqlSession copenssion = MyBatisUtil.getSqlSession();
            CreditCardMapper cmapper = copenssion.getMapper(CreditCardMapper.class);
            cmapper.insertCredit(crecard);
            System.out.println("开卡成功！");
            copenssion.commit();;
            copenssion.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.hzbank.credit.service.impl;

import com.hzbank.credit.Main;
import com.hzbank.credit.cons.BizConstant;
import com.hzbank.credit.entity.CashAdvance;
import com.hzbank.credit.entity.CreditCard;
import com.hzbank.credit.mapper.CashAdvanceMapper;
import com.hzbank.credit.mapper.CreditCardMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.DateTimeFormatUtil;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * 信用卡还款额度服务
 */
public class CreditCardPaymentService implements BaseService {
    @Override
    public void doBiz() throws IOException {
        // 获取登录的信用卡号
        String cardNo = Main.loginCardNo;
        if(cardNo == null){
            System.out.println("还未登录, 请先登录");
            return;
        }
        // 计算信用卡欠款额度
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);

        // 信用卡信息
        CreditCard creditCardInfo = creditCardMapper.getCreditCardInfo(cardNo);

        CashAdvanceMapper cashAdvanceMapper = sqlSession.getMapper(CashAdvanceMapper.class);
        // 预借现金信息
        CashAdvance cashAdvance = cashAdvanceMapper.getCashAdvanceByCardNumDate(cardNo, DateTimeFormatUtil.dateToStr(new Date()));

        // 信用卡授信额度 + 透支额度 - 透支剩余额度 - 剩余额度
        float spendMoney = BizConstant.CREDIT_LIMIT + BizConstant.AVAILABL_CREDIT - creditCardInfo.getCreditLimit() - creditCardInfo.getAvailableCredit();
        // 信用欠款 = 消费额度 + 预约取款额度 + （利息，每日定时任务根据交易流水计算）
        float preMoney = (BizConstant.CREDIT_LIMIT * BizConstant.PRE_AMOUNT_PERCENT) - cashAdvance.getPreApprovedAmount();
        System.out.println("=============欢迎进入信用卡还款功能卡号: " + cardNo + " 当前应还消费额度为: " + spendMoney + "(" + creditCardInfo.getCurrency() + ") 应还预借现金额度为: " + preMoney + "(" + creditCardInfo.getCurrency() + ")====================");
        Scanner scn = new Scanner(System.in);
        Integer type = 0;
        while (true){
            System.out.println("请输入还款类型:");
            System.out.println("1. 消费额度");
            System.out.println("2. 预借现金额度");
            type = scn.nextInt();
            if(type != 1 && type != 2) {
                System.out.println("请输入正确类型");
            }else{
                break;
            }
        }

        System.out.println("请输入还款金额:");
        String money = scn.next();
        float moneyF = 0;
        while (true){
            try {
                moneyF = Float.parseFloat(money);
                break;
            }catch (Exception e){
                System.out.println("输入金额不正确，请重新输入:");
                money = scn.next();
            }
        }

        if(type == 1){
            if(moneyF > spendMoney){
                System.out.println("还款金额大于消费额度");
                return;
            }
        }else if(type == 2){
            if(moneyF > preMoney){
                System.out.println("还款金额大于预借额度");
                return;
            }
        }

        float remainPre = 0;
        float remainAdvance = 0;
        if(type == 1){
            // 进行消费额度还款
            paymentSpend(cardNo, creditCardInfo, sqlSession, moneyF);
            remainPre = spendMoney - moneyF;
            remainAdvance = preMoney;
        }else if(type == 2){
            // 进行预借额度还款
            paymentAdvance(cardNo, cashAdvance, sqlSession, moneyF);
            remainPre = spendMoney;
            remainAdvance = preMoney - moneyF;
        }

        sqlSession.commit();
        System.out.println(cardNo + "还款成功, 当前剩余应还消费额度为: " + remainPre + "(" + creditCardInfo.getCurrency() + ") 剩余应还预借现金额度为: " + remainAdvance + "(" + creditCardInfo.getCurrency() + ")");
    }

    /**
     * 预借现金还款
     * @param cardNo
     * @param cashAdvance
     * @param sqlSession
     */
    private void paymentAdvance(String cardNo, CashAdvance cashAdvance, SqlSession sqlSession, float money) {
        CashAdvanceMapper cashAdvanceMapper = sqlSession.getMapper(CashAdvanceMapper.class);
        CashAdvance newCashAdvance = new CashAdvance();
        newCashAdvance.setPreApprovedAmount(cashAdvance.getPreApprovedAmount() + money);
        cashAdvanceMapper.updateCashAdvanceByEntity(newCashAdvance, cardNo);
    }

    /**
     * 信用卡信息还款
     * @param cardNo
     * @param creditCardInfo
     * @param sqlSession
     */
    private void paymentSpend(String cardNo, CreditCard creditCardInfo, SqlSession sqlSession, float money) {
        CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);

        CreditCard newCreditCard = new CreditCard();

        newCreditCard.setCreditLimit(creditCardInfo.getCreditLimit() + money);

        creditCardMapper.updateCreditCardByEntity(newCreditCard, cardNo);
    }
}

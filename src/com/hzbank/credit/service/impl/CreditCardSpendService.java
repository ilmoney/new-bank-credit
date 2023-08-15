package com.hzbank.credit.service.impl;

import com.hzbank.credit.Main;
import com.hzbank.credit.bizenum.SpendTypeEnum;
import com.hzbank.credit.entity.CreditCard;
import com.hzbank.credit.entity.Transactions;
import com.hzbank.credit.mapper.CreditCardMapper;
import com.hzbank.credit.mapper.TransactionsMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * 开卡消费接口
 */
public class CreditCardSpendService implements BaseService {

    @Override
    public void doBiz() throws IOException {
        Scanner scn = new Scanner(System.in);
        String cardNo = Main.loginCardNo;
        // 设置卡号
        if(cardNo == null){
            System.out.println("还未登录, 请先登录");
            return;
        }

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        CreditCardMapper creditCardMapper = sqlSession.getMapper(CreditCardMapper.class);
        CreditCard creditCardInfo = creditCardMapper.getCreditCardInfo(cardNo);

        Transactions transactions = new Transactions();
        System.out.println("=============欢迎进入刷卡消费功能" + creditCardInfo.getCreditCard() + "额度为: " + creditCardInfo.getCreditLimit() + "(" + creditCardInfo.getCurrency() + ") 可透支额度为: " + creditCardInfo.getAvailableCredit() + "(" + creditCardInfo.getCurrency() + ")====================");
        System.out.println("请输入消费金额:");
        String money = scn.next();
        boolean parseMoneyFlag = false;
        float moneyF = 0;
        // 解析消费金额
        while (!parseMoneyFlag){
            try {
                moneyF = Float.valueOf(money);
                parseMoneyFlag = true;
            }catch (Exception e){
                System.out.println("输入金额不正确, 请重新输入:");
                money = scn.next();
            }
        }

        // 金额大于可用额度和透支金额
        if(moneyF > (creditCardInfo.getAvailableCredit() + creditCardInfo.getCreditLimit())){
            System.out.println("输入的金额大于可透支额度");
            return;
        }

        CreditCard newCreditCardInfo = new CreditCard();

        // 比可用信用额度低，优先扣款可用额度
        if(moneyF <= creditCardInfo.getCreditLimit()){
            newCreditCardInfo.setCreditLimit(creditCardInfo.getCreditLimit() - moneyF);
        }else{
            newCreditCardInfo.setCreditLimit(0f);
            newCreditCardInfo.setAvailableCredit(creditCardInfo.getAvailableCredit() + creditCardInfo.getCreditLimit() - moneyF);
        }

        // 获取消费次数
        Integer transactionCount = creditCardInfo.getTransactionCount();
        newCreditCardInfo.setTransactionCount(transactionCount + 1);

        // 如果之前消费次数为2次，加上这次可以取消信用卡年金
        if(transactionCount >= 2){
            newCreditCardInfo.setAnnualFee(0f);
        }
        // 更新信用卡信息
        creditCardMapper.updateCreditCardByEntity(newCreditCardInfo, cardNo);

        // 判断是否是特约商户
        System.out.println("请输入消费商户:");
        String source = scn.next();
        transactions.setCreditCard(cardNo);

        // 设置消费类型
        transactions.setExpenseType(String.valueOf(SpendTypeEnum.CREDIT_CARD_SPEND_TYPE.getType()));
        transactions.setExpenseSource(source);
        transactions.setExpenseTime(new Date());
        transactions.setExpenseAmount(moneyF);

        // 保存消费流水记录
        TransactionsMapper transactionsMapper = sqlSession.getMapper(TransactionsMapper.class);
        transactionsMapper.insert(transactions);

        sqlSession.commit();
        System.out.println(cardNo + " 消费成功, 消费金额: " + moneyF + "剩余可用额度为: " + newCreditCardInfo.getCreditLimit() + "可透支额度为: " + newCreditCardInfo.getAvailableCredit());
    }
}

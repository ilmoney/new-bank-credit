package com.hzbank.credit.service.impl;

import com.hzbank.credit.Main;
import com.hzbank.credit.bizenum.SpendTypeEnum;
import com.hzbank.credit.entity.Transactions;
import com.hzbank.credit.mapper.TransactionsMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

/**
 * 开卡消费接口
 */
public class CreditCardSpendService implements BaseService {

    @Override
    public void doBiz() throws IOException {
        Scanner scn = new Scanner(System.in);

        Transactions transactions = new Transactions();
        System.out.println("进入刷卡消费");
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

        // 判断是否是特约商户
        System.out.println("请输入消费商户:");
        String source = scn.next();

        // 设置卡号
        transactions.setCreditCard(Main.loginCardNo);
        // 设置消费类型
        transactions.setExpenseType(String.valueOf(SpendTypeEnum.CREDIT_CARD_SPEND_TYPE.getType()));
        transactions.setExpenseSource(source);
        transactions.setExpenseTime(new Date());
        transactions.setExpenseAmount(moneyF);
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        // 保持消费流水记录
        TransactionsMapper transactionsMapper = sqlSession.getMapper(TransactionsMapper.class);
        transactionsMapper.insert(transactions);

        sqlSession.commit();
        sqlSession.close();
        System.out.println(Main.loginCardNo + " 消费成功, 消费金额: " + moneyF);
    }
}

package com.hzbank.credit.service.impl;

import com.hzbank.credit.Main;
import com.hzbank.credit.bizenum.SpendTypeEnum;
import com.hzbank.credit.entity.CashAdvance;
import com.hzbank.credit.entity.Transactions;
import com.hzbank.credit.mapper.CashAdvanceMapper;
import com.hzbank.credit.mapper.TransactionsMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.DateTimeFormatUtil;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * 预借现金服务
 */
public class CashAdvanceService implements BaseService {

    @Override
    public void doBiz() throws IOException {

        Scanner scn = new Scanner(System.in);
        // 获取登录的信用卡号
        String cardNo = Main.loginCardNo;
        if(cardNo == null){
            System.out.println("还未登录, 请先登录");
            return;
        }
        // 查询出当前卡预借额度信息
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        CashAdvanceMapper cashAdvanceMapper = sqlSession.getMapper(CashAdvanceMapper.class);
        Date now = new Date();
        CashAdvance cashAdvance = cashAdvanceMapper.getCashAdvanceByCardNumDate(cardNo, DateTimeFormatUtil.dateToStr(now));
        // 查询出的信息为null
        if(cashAdvance == null){
            System.out.println("当前卡号未查询到预借额度信息");
            return;
        }
        System.out.println("欢迎进入预借现金业务, 当前可预借额度为: " + cashAdvance.getPreApprovedAmount() + ", 当日最大可预借额度为: " + cashAdvance.getDailyLoanAmount());

        System.out.println("请输入预借额度:");
        String money = scn.next();
        float moneyF = 0;
        boolean checkMoney = false;
        while (!checkMoney){
            try {
                moneyF = Float.parseFloat(money);
                checkMoney = true;
            }catch (Exception e){
                System.out.println("输入金额不正确，请重新输入:");
                money = scn.next();
            }
        }
        if(moneyF > cashAdvance.getDailyLoanAmount()){
            System.out.println("输入金额超过每日最大可借金额，请退出重试");
            return;
        }
        if(moneyF > cashAdvance.getPreApprovedAmount()){
            System.out.println("输入金额超过最大预借借金额，请退出重试");
            return;
        }
        // 剩余预借额度
        float remainPreAmount = cashAdvance.getPreApprovedAmount() - moneyF;
        // 当日预借额度
        float dailyLoanAmount = cashAdvance.getDailyLoanAmount() -  moneyF;
        // 进行预借，更新对应表数据
        CashAdvance newCashAdvance = new CashAdvance();
        newCashAdvance.setPreApprovedAmount(remainPreAmount);
        newCashAdvance.setDailyLoanAmount(dailyLoanAmount);
        cashAdvanceMapper.updateCashAdvanceByEntity(newCashAdvance, cardNo);

        // 保存交易流水
        Transactions transactions = new Transactions();
        transactions.setCreditCard(cardNo);
        // 设置消费类型
        transactions.setExpenseType(String.valueOf(SpendTypeEnum.CASH_ADVANCE_TYPE.getType()));
        transactions.setExpenseSource("");
        transactions.setExpenseTime(new Date());
        transactions.setExpenseAmount(moneyF);

        // 保存消费流水记录
        TransactionsMapper transactionsMapper = sqlSession.getMapper(TransactionsMapper.class);
        transactionsMapper.insert(transactions);

        System.out.println(cardNo + "预借成功, 预借额度: " + moneyF + ", 剩余预借额度: " + remainPreAmount + "当日剩余预借额度: " + dailyLoanAmount);
        sqlSession.commit();
    }
}

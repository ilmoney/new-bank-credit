package com.hzbank.credit;

import com.hzbank.credit.bizenum.BizTypeEnum;
import com.hzbank.credit.mapper.CreditCardMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.BizUtil;
import com.hzbank.credit.util.MyBatisUtil;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    // 登录的卡号
    public static String loginCardNo = null;
    public static void main(String[] args) throws IOException {
        // 初始化sqlSession，防止后面连接数据库连接报错
        init();
        Scanner scn = new Scanner(System.in);
        while (true){
            System.out.println("===========================功能页！欢迎进入信用卡业务=======================");
            System.out.println("0.登录");
            System.out.println("1. 一卡通账户开户");
            System.out.println("2. 开卡");
            System.out.println("3. 修改密码");
            System.out.println("4. 刷卡消费");
            System.out.println("5. 预借现金");
            System.out.println("6. 信用卡还款");
            System.out.println("7. 销卡");
            System.out.println("8. 退出");
            System.out.println("请输出需要办理的业务:");

            // 输入业务类型
            int bizType = scn.nextInt();

            // 如果输入的是退出, 则直接退出程序
            if(bizType == BizTypeEnum.EXIT_BIZ_TYPE.getType()){
                break;
            }
            BaseService bizService = BizUtil.getServiceByType(bizType);
            try {
                bizService.doBiz();
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                MyBatisUtil.getSqlSession().close();
            }

        }
    }

    private static void init() throws IOException {
        MyBatisUtil.getSqlSession().getMapper(CreditCardMapper.class);
        MyBatisUtil.getSqlSession().close();
    }
}

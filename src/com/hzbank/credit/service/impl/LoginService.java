package com.hzbank.credit.service.impl;

import com.hzbank.credit.Main;
import com.hzbank.credit.entity.CreditCard;
import com.hzbank.credit.mapper.CreditCardMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Scanner;

public class LoginService implements BaseService {


    @Override
    public void doBiz() {
        System.out.println("============0、欢迎来到登录业务===============");
        Scanner in = new Scanner(System.in);
        String cardid = null;
        CreditCard credit = new CreditCard();
        String password = null;
        while(true)
        {
            System.out.println("请输入卡号:");
            cardid = in.nextLine();
            try {
                SqlSession openssion = MyBatisUtil.getSqlSession();
                CreditCardMapper mapper = openssion.getMapper(CreditCardMapper.class);
                credit = mapper.getCreditCardInfo(cardid);
                openssion.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(credit == null)
                System.out.println("卡号不存在，请重新输入！");
            else
                break;
        }

        while(true)
        {
            System.out.println("请输入密码：");
            password = in.nextLine();
            if(password.equals(credit.getTransactionPassword())) {
                System.out.println("登录成功！");
                Main.loginCardNo = cardid;
                return;
            }
            else
            {
                System.out.println("密码输入错误！");
            }
        }
    }
}

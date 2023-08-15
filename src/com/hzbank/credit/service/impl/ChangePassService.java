package com.hzbank.credit.service.impl;

import com.hzbank.credit.entity.CreditCard;
import com.hzbank.credit.mapper.CreditCardMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Scanner;

public class ChangePassService implements BaseService {
    @Override
    public void doBiz() throws IOException {
        System.out.println("=============欢迎进入修改密码功能====================");
        Scanner in = new Scanner(System.in);
        String cardid = null;
        String idnumber = null;
        String password = null;
        CreditCard credicard = new CreditCard();
        while (true) {
            System.out.println("请输入卡号：");
            cardid = in.nextLine();
            //查询用户是否存在
            SqlSession openssion = MyBatisUtil.getSqlSession();
            CreditCardMapper mapper = openssion.getMapper(CreditCardMapper.class);
            credicard = mapper.getCrediCardInfo(cardid);
            if(credicard==null)
                System.out.println("卡号不存在，请重新输入");
            else
                break;
        }
//        while(true) {
//            System.out.println("请输入身份证号：");
//            idnumber = in.nextLine();
//            if(!idnumber.equals(credicard.getCreditCardID()))
//            {
//                System.out.println("身份证输入不正确！！");
//            }
//            else {
//                break;
//            }
//        }


        while(true) {
            System.out.println("请输入密码：");
            password = in.nextLine();
            if(!password.equals(credicard.getTransactionPassword()))
            {
                System.out.println("密码输入不正确！！");
            }
            else {
                break;
            }
        }
        String newpass = null;
        String newpasscheck = null;
        while(true) {
            System.out.println("请设置新密码：");
            newpass = in.nextLine();
            System.out.println("请再次输入密码：");
            newpasscheck = in.nextLine();
            if(!newpass.equals(newpasscheck))
                System.out.println("两次密码输入不正确！");
            else
                break;
        }

        //更改密码
        SqlSession openssion = MyBatisUtil.getSqlSession();
        CreditCardMapper pmapper = openssion.getMapper(CreditCardMapper.class);
        pmapper.updatePass(newpass,cardid);
        System.out.println("密码修改成功！");
        openssion.commit();
        openssion.close();

    }
}

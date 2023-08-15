package com.hzbank.credit.service.impl;

import com.hzbank.credit.entity.CampusCard;
import com.hzbank.credit.entity.CreditCard;
import com.hzbank.credit.mapper.CampusCardMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.CheckNumber;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Scanner;

public class OpenCardService implements BaseService {
    @Override
    public void doBiz() {
        Scanner in = new Scanner(System.in);
        String campusid = null;
        String idnumber = null;
        String password = null;
        CampusCard campus = new CampusCard();
        //输入一卡通账号
        while(true)
        {
            System.out.println("请输入一卡通账号：");
            campusid = in.nextLine();
            System.out.println("请输入身份证号:");
            idnumber = in.nextLine();
            System.out.println("请输入密码：");
            password = in.nextLine();
            try {
                SqlSession openssion = MyBatisUtil.getSqlSession();
                CampusCardMapper mapper = openssion.getMapper(CampusCardMapper.class);
                campus = mapper.selectbyid(campusid,idnumber,password);
                if(campus==null)
                    System.out.println("用户不存在，请先创建一卡通账号！！");
                else
                    break;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //生成卡号
        String cardid = CheckNumber.generateBankCardNumber("6");
        CreditCard crecard = new CreditCard();
        crecard.setCreditCardID(cardid);
        crecard.setAnnualFee(100);


    }
}

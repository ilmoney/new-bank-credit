package com.hzbank.credit.service.impl;

import com.hzbank.credit.cons.BizConstant;
import com.hzbank.credit.entity.CampusCard;
import com.hzbank.credit.mapper.CampusCardMapper;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.BaseUtil;
import com.hzbank.credit.util.CheckNumber;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 一卡通开卡接口
 */
public class OpenAccountService implements BaseService {
    public void doBiz(){
        System.out.println("=============1、欢迎进入一卡通账户开户服务================");
        Scanner in = new Scanner(System.in);
        String password = null;
        String IDCard = null;
        String phonenumber = null;
        //身份证号
        while(true)
        {
            System.out.println("请输入身份证号：");
            IDCard = in.nextLine();
            if(checkid(IDCard))
                break;
        }

        //手机号
        while(true)
        {
            System.out.println("请输入手机号:");
            phonenumber = in.nextLine();
            if(checkphone(phonenumber))
                break;
        }
        //生成一卡通账号
        String campuscardID = CheckNumber.generateBankCardNumber("3");
        //设置密码
        while(true)
        {
            System.out.println("请设置密码:");
            String password_one = in.nextLine();
            System.out.println("请再次输入密码：");
            String password_two = in.nextLine();
            if(!password_one.equals(password_two)) {
                System.out.println("两次密码输入不一致，请重新设置密码");
            }
            else{
                password = password_one;
                break;
            }
        }
        //开户
        try {
            SqlSession openSession = MyBatisUtil.getSqlSession();
            CampusCardMapper mapper = openSession.getMapper(CampusCardMapper.class);
            CampusCard campusCardEntity = new CampusCard();
            campusCardEntity.setCampusCardID(campuscardID);
            campusCardEntity.setIdNumber(IDCard);
            campusCardEntity.setPhoneNumber(phonenumber);
            password = BaseUtil.encryptBASE64(password.getBytes(StandardCharsets.UTF_8));
            campusCardEntity.setTransactionPassword(password);
            campusCardEntity.setBalance(0f);
            mapper.insertCampuse(campusCardEntity);
            openSession.commit();
            System.out.println("创建一卡通账号" + campuscardID  + "成功并返回首页！！！！");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    private boolean checkid(String IDCard)
    {
        if(IDCard.length()!=18) {
            System.out.println("身份证号长度错误！");
            return false;
        }
        boolean isValid = CheckNumber.validateIDCard(IDCard);
        if (!isValid) {
            System.out.println("身份证号不合法");
            return false;
        }
        return true;
    }
    private boolean checkphone(String phonenumber)
    {
        if(phonenumber.length()!=11) {
            System.out.println("手机号码长度错误！");
            return false;
        }
        boolean isValid = CheckNumber.validatePhoneNumber(phonenumber);
        if (!isValid) {
            System.out.println("手机号不合法");
            return false;
        }
        return true;
    }

}

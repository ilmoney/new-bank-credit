package com.hzbank.credit.util;

import java.util.Random;
import java.util.regex.Pattern;

public class CheckNumber {
    public static boolean validateIDCard(String idCard) {
        String regex = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
        return Pattern.matches(regex, idCard);
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^1[3-9]\\d{9}$";
        return Pattern.matches(regex, phoneNumber);
    }


    public static String generateBankCardNumber(String preix) {
        // 银行卡号通常以数字开头，且长度为16位
        StringBuilder bankCardNumber = new StringBuilder(preix);  // 假设以"4"开头
        Random random = new Random();
        for (int i = 1; i < 16; i++) {
            int digit = random.nextInt(10);  // 生成0到9之间的随机数字
            bankCardNumber.append(digit);
        }
        return bankCardNumber.toString();
    }
}

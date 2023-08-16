package com.hzbank.credit.cons;

public class BizConstant {
    public static final float ANNUAL_FEE = 100;
    public static final float CREDIT_LIMIT = 200000;
    public static final float AVAILABL_CREDIT = (float) (BizConstant.CREDIT_LIMIT*0.01);

    public static final float DAILY_LOAN_AMOUNT = 2000;

    // 预借现金额度为授信额度的70%
    public static final float PRE_AMOUNT_PERCENT = 0.7f;

    // BASE64加密字符串
    public static final String CODE_STR = "123456";

}

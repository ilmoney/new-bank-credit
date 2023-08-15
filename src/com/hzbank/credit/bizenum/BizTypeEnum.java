package com.hzbank.credit.bizenum;

public enum BizTypeEnum {

    LOGIN_BIZ_TYPE(0, "登录"),
    OPEN_ACCOUNT_BIZ_TYPE(1, "一卡通账户开户"),
    CREDIT_CARD_BIZ_TYPE(2, "开卡"),
    CHANGE_PASS_BIZ_TYPE(3, "修改密码"),
    CREDIT_CARD_SPEND_BIZ_TYPE(4, "刷卡消费"),
    CASH_ADVANCE_BIZ_TYPE(5, "预借现金"),
    CREDIT_CARD_PAYMENT_BIZ_TYPE(6, "信用卡还款"),
    PIN_CARD_BIZ_TYPE(7, "销卡"),
    EXIT_BIZ_TYPE(8,"退出");

    private Integer type;
    private String name;

    BizTypeEnum(Integer type, String name){
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

package com.hzbank.credit.bizenum;

/**
 * 消费类型枚举类
 */
public enum SpendTypeEnum {

    NONE_SPEND_TYPE(0, "无效"),
    CREDIT_CARD_SPEND_TYPE(1, "刷卡消费"),
    CASH_ADVANCE_TYPE(2, "预约取现");

    private Integer type;
    private String name;

    SpendTypeEnum(Integer type, String name) {
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

package com.hzbank.credit.bizenum;

/**
 * 卡状态枚举
 */
public enum CardTypeEnum {
    CARD_NORMAL_TYPE(0, "正常"),
    CARD_FROZEN_TYPE(1,"冻结"),
    CARD_CLOSED_TYPE(2,"销户"),
    CARD_LOST_TYPE(3,"挂失");

    private Integer type;
    private String name;




    CardTypeEnum(Integer type,String name)
    {
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

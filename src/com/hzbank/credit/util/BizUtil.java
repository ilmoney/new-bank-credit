package com.hzbank.credit.util;

import com.hzbank.credit.bizenum.BizTypeEnum;
import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.service.impl.*;

import static javafx.application.Platform.exit;

public class BizUtil {
    public static BaseService getServiceByType(int type){

        BaseService baseService = null;
        if(type == BizTypeEnum.OPEN_ACCOUNT_BIZ_TYPE.getType()){
            // 一卡通开户类型
            baseService = new OpenAccountService();
        }else if(type == BizTypeEnum.CREDIT_CARD_BIZ_TYPE.getType()){
            // 信用卡开卡功能
            baseService = new OpenCardService();
        }else if(type == BizTypeEnum.CREDIT_CARD_SPEND_BIZ_TYPE.getType()){
            // 刷卡消费功能
            baseService = new CreditCardSpendService();
        }
        else if(type == BizTypeEnum.CHANGE_PASS_BIZ_TYPE.getType())
        {
            //修改密码功能
            baseService = new ChangePassService();
        }
        else if(type == BizTypeEnum.PIN_CARD_BIZ_TYPE.getType())
        {
            //销户功能
            baseService = new CancellationService();
        }
        else if(type == BizTypeEnum.NONE_BIZ_TYPE.getType())
        {
            //登录功能
            baseService = new LoginService();
        }
        else if(type == BizTypeEnum.EXIT_BIZ_TYPE.getType())
        {
            //退出
            baseService = new ExitService();
        }
        return baseService;
    }
}

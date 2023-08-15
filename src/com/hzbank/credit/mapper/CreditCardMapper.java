package com.hzbank.credit.mapper;

import com.hzbank.credit.entity.CashAdvance;
import com.hzbank.credit.entity.CreditCard;
import org.apache.ibatis.annotations.Param;

public interface CreditCardMapper {
    CreditCard getCreditCardInfo(@Param("id") String id);
    void insertCredit(@Param("crecard") CreditCard crecard);

    void updatePass(@Param("password") String password,@Param("cardnumber") String cardnumber);

    // 额外参数传入主键，防止未set
    void updateCreditCardByEntity(@Param("creditCard") CreditCard creditCard, @Param("cardNumber") String cardNumber);
}

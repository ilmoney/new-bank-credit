package com.hzbank.credit.mapper;

import com.hzbank.credit.entity.CreditCard;
import org.apache.ibatis.annotations.Param;

public interface CreditCardMapper {
    CreditCard getCrediCardInfo(String id);
    void insertCredit(@Param("crecard") CreditCard crecard);

    void updatePass(@Param("password") String password,@Param("cardnumber") String cardnumber);
}

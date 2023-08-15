package com.hzbank.credit.mapper;

import com.hzbank.credit.entity.CreditCard;
import org.apache.ibatis.annotations.Param;

public interface CreditCardMapper {
    CreditCard getCrediCardInfo(String id);
    CreditCard insertCredit(@Param("crecard") CreditCard crecard);
}

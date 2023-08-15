package com.hzbank.credit.mapper;

import com.hzbank.credit.entity.CashAdvance;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CashAdvanceMapper {

    CashAdvance getCashAdvanceByCardNumDate(@Param("cardNumber") String cardNumber, @Param("date") String date);

    // 额外参数传入主键，防止未set
    void updateCashAdvanceByEntity(@Param("cashAdvance") CashAdvance cashAdvance, @Param("cardNumber") String cardNumber);

    void insertCashAdvance(@Param("cashAdvance") CashAdvance cashAdvance);

}

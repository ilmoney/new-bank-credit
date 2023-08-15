package com.hzbank.credit.mapper;

import com.hzbank.credit.entity.CampusCard;
import org.apache.ibatis.annotations.Param;

public interface CampusCardMapper {
    void insertCreditCard(@Param("campus") CampusCard campus);
}

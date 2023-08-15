package com.hzbank.credit.mapper;

import com.hzbank.credit.entity.CampusCard;
import org.apache.ibatis.annotations.Param;

public interface CampusCardMapper {
    void insertCampuse(@Param("campus") CampusCard campus);
    CampusCard selectbyid(@Param("id") String id);
}

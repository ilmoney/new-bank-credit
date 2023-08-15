package com.hzbank.credit.mapper;

import com.hzbank.credit.entity.CampusCard;
import org.apache.ibatis.annotations.Param;

public interface CampusCardMapper {
    void insertCampuse(@Param("campus") CampusCard campus);
    CampusCard selectbyid(@Param("id") String id,@Param("idnumber") String idnumber,@Param("password") String password);
}

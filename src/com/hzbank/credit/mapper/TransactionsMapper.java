package com.hzbank.credit.mapper;

import com.hzbank.credit.entity.Transactions;
import org.apache.ibatis.annotations.Param;

public interface TransactionsMapper {
    void insert(@Param("transactions") Transactions transactions);
}

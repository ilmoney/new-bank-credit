package com.hzbank.credit.service;

import java.io.IOException;

/**
 * baseService，每个业务service继承该service，实现多态
 */
public interface BaseService {
    void doBiz() throws Exception;
}

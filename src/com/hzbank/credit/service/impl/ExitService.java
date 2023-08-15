package com.hzbank.credit.service.impl;

import com.hzbank.credit.service.BaseService;

import java.io.IOException;

import static javafx.application.Platform.exit;

/**
 * 退出接口
 */
public class ExitService implements BaseService {
    @Override
    public void doBiz() throws IOException {
        exit();
    }
}

package com.hzbank.credit.service.impl;

import com.hzbank.credit.Main;
import com.hzbank.credit.service.BaseService;

import java.io.IOException;
import java.util.Scanner;

public class CancellationService implements BaseService {
    @Override
    public void doBiz() throws IOException {
        System.out.println("=======欢迎进入销户功能===========");
        Scanner in = new Scanner(System.in);
        if(Main.loginCardNo==null) {
            System.out.println("请先登录！");
            return;
        }
        System.out.println("您确定要销户吗");
        System.out.println("1.yes");
        System.out.println("2.no");
        int c = in.nextInt();
        if(c==1)
            System.out.println("销户成功");
    }
}

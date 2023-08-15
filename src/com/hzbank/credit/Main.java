package com.hzbank.credit;

import com.hzbank.credit.service.BaseService;
import com.hzbank.credit.util.BizUtil;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        while (true){
            System.out.println("欢迎进入信用卡业务");
            System.out.println("1. 一卡通账户开户");
            System.out.println("2. 信息卡开卡(美元、人民币)");
            System.out.println("3. 修改密码");
            System.out.println("4. 刷卡消费");
            System.out.println("5. 预借现金");
            System.out.println("6. 信用卡还款");
            System.out.println("7. 销卡");
            System.out.println("请输出需要办理的业务:");

            // 输入业务类型
            int bizType = scn.nextInt();
            BaseService bizService = BizUtil.getServiceByType(bizType);
            bizService.doBiz();
        }
    }
}

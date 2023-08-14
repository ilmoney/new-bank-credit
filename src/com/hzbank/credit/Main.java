package com.hzbank.credit;

import com.hzbank.credit.entity.UserInfo;
import com.hzbank.credit.mapper.UserInfoMapper;
import com.hzbank.credit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        //2.获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            //3.获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            UserInfoMapper mapper = openSession.getMapper(UserInfoMapper.class);
            UserInfo empByID = mapper.getUserInfoById(2);
            System.out.println(empByID);
        }finally {
            openSession.close();
        }
    }
}

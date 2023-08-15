package com.hzbank.credit.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {

        if(sqlSessionFactory == null){
            String resource = "com/hzbank/credit/conf/mybatis-config.xml";

            InputStream inputStream = Resources.getResourceAsStream(resource);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        return sqlSessionFactory;

    }

    public static SqlSession getSqlSession() throws IOException {
        if(sqlSession == null){
            //1.获取SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
            //2.获取sqlSession对象
            sqlSession = sqlSessionFactory.openSession();
        }
        return sqlSession;
    }

}

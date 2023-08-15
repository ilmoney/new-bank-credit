package com.hzbank.credit.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatisUtil工具类
 */
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
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取sqlSession对象
        if(sqlSession == null){
            sqlSession = sqlSessionFactory.openSession();
        }
        try {
            // 3. 如果sqlSession被关闭了，开启新的sqlSession
            if(sqlSession.getConnection().isClosed()){
                sqlSession = sqlSessionFactory.openSession();
            }
        }catch (Exception e){
            // 4. 抛出异常创建新的sqlSession
            sqlSession = sqlSessionFactory.openSession();
        }
        return sqlSession;
    }

}

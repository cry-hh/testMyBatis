package com.igeek.ch03.mapper;

import com.igeek.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class UserMapperTest {

    //关联会话工厂对象
    private SqlSessionFactory factory;

    //在启动测试方法之前做一些准备工作
    @Before
    public void setUp() throws Exception {
        //加载mybatis的核心配置文件，产生流对象
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建会话工厂对象
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void findByName(){
        //创建会话对象
        SqlSession sqlSession = factory.openSession();
        //获得代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.findByName("明");
        for(User user : list){
            System.out.println(user);
        }
        //关闭资源
        sqlSession.close();
    }

    @Test public void updateUser() {
        SqlSession sqlSesison = factory.openSession();
        UserMapper userMapper = sqlSesison.getMapper(UserMapper.class);
        User user = userMapper.selectUserById(29);
        user.setUsername("李四");
        userMapper.updateUser(user);
        sqlSesison.commit(); sqlSesison.close();
    }

    @Test public void deleteUser() {
        SqlSession sqlSesison = factory.openSession();
        UserMapper userMapper = sqlSesison.getMapper(UserMapper.class);
        userMapper.deleteUser(29);
        sqlSesison.commit();
        sqlSesison.close();
    }
}

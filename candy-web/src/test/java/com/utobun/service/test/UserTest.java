package com.utobun.service.test;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.utobun.candy.dao.TestDao;
import com.utobun.candy.domain.Account;
import com.utobun.candy.domain.User;
import com.utobun.candy.param.UserRegisterParam;
import com.utobun.candy.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-servlet.xml")
@Transactional
public class UserTest {
   
    @Resource
    private UserService userService;
    @Resource
    private TestDao testDao;
    
    @Test
    @Transactional
    public void userTest(){
        
        User user = new User();
        Account account = new Account();
        user.setEmail("jianmo_oaa@163.com");
        user.setUserName("jianmo_o");
        account.setPassword("12121212");
        account.setState(0);
        account.setUserName(user.getUserName());
        
        
    }
}

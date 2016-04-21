package com.utobun.service.test;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.utobun.candy.domain.Account;
import com.utobun.candy.domain.User;
import com.utobun.candy.service.UserService;


@ContextConfiguration("classpath:spring-servlet.xml")
@Transactional
public class UserTest {
   
    @Resource
    private UserService userService;
    @Resource
    private Test testDao;
    
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

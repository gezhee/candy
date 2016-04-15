package com.utobun.candy.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utobun.candy.contants.ReturnData;
import com.utobun.candy.controller.base.ControllerAbstract;
import com.utobun.candy.entity.Account;
import com.utobun.candy.entity.User;
import com.utobun.candy.service.UserService;

@Controller
@RequestMapping(value="/web/user/")
public class UserController extends ControllerAbstract{
    
    @Resource
    private UserService userService;
    
    @RequestMapping(value = "register.do")
    public ReturnData register(User user, Account account){
        return null;
    }
    
    @RequestMapping(value = "login.do")
    public ReturnData login(String userName,String pasword){
        return null;
    }
    
    @RequestMapping(value = "checkUserName")
    public ReturnData checkUserName(String userName){
        return null;
    }
    
    
}
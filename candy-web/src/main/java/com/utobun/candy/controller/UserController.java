package com.utobun.candy.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utobun.candy.contants.ReturnCode;
import com.utobun.candy.contants.ReturnData;
import com.utobun.candy.controller.base.ControllerAbstract;
import com.utobun.candy.param.UserRegisterParam;
import com.utobun.candy.service.UserService;
import com.utobun.candy.util.ReturnUtil;

@Controller
@RequestMapping(value="/web/b/")
public class UserController extends ControllerAbstract{
    
    @Resource
    private UserService userService;
    
    @RequestMapping(value = "register.do")
    public ReturnData register(UserRegisterParam param,HttpServletRequest req){
        
        Object verifyCode = req.getSession().getAttribute("verifyCode");
        
        if(verifyCode!=null && !((String)verifyCode).equalsIgnoreCase(param.getVerifyCode())){
            return ReturnUtil.fail(ReturnCode.VERIFY_CODE);
        }
        
        return userService.userRegister(param);
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
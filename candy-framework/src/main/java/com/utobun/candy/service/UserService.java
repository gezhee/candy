package com.utobun.candy.service;

import com.utobun.candy.contants.ReturnData;
import com.utobun.candy.param.UserRegisterParam;


public interface UserService {

    /** 
     * userRegister: 用户注册<br/> 
     * @author HemingWu
     * @param user
     * @param account
     * @return  
     */ 
    public ReturnData userRegister(UserRegisterParam param);
    
    
    /** 
     * userLogin: 用户登陆<br/> 
     * @author HemingWu 
     * @param userName
     * @param password
     * @return  
     */ 
    public ReturnData userLogin(String userName, String password);
}

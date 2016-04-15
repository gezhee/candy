package com.utobun.candy.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.utobun.candy.contants.ReturnData;
import com.utobun.candy.dao.AccountDao;
import com.utobun.candy.dao.UserDao;
import com.utobun.candy.domain.Account;
import com.utobun.candy.domain.User;
import com.utobun.candy.param.UserRegisterParam;
import com.utobun.candy.service.UserService;
import com.utobun.candy.service.base.BaseServiceAbstract;
import com.utobun.candy.util.EncryptUtil;
import com.utobun.candy.util.ReturnUtil;

@Service
public class UserServiceImpl extends BaseServiceAbstract implements UserService{
    
    @Resource
    private UserDao userDao;
    
    @Resource
    private AccountDao accountDao;
    
    @Override
    @Transactional
    public ReturnData userRegister(UserRegisterParam param) {
        
        User user = new User();
        user.setUserName(param.getName());
        user.setEmail(param.getEmail());
        userDao.add(user);
        
        Account account = new Account();
        account.setUserId(user.getId());
        //使用MD5加密密码
        String password = EncryptUtil.MD5(param.getPassword());
        account.setPassword(password);
        account.setUserName(param.getName());
        account.setUserId(user.getId());
        account.setState(0);
        accountDao.add(account);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("user", user);
        return ReturnUtil.success(data);
    }
    
    
    public ReturnData userLogin(String userName, String password){
        return null;
        
    }
    


}

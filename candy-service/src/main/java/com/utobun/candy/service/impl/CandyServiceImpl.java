package com.utobun.candy.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.utobun.candy.contants.ReturnData;
import com.utobun.candy.service.CandyService;
import com.utobun.candy.service.base.BaseServiceAbstract;

public class CandyServiceImpl extends BaseServiceAbstract implements CandyService{

    @Override
    public ReturnData welocome(HttpServletRequest req) {
        Cookie[] cookie = req.getCookies();
        return null;
    }

    @Override
    public ReturnData identifyPic(int length) {
        // TODO Auto-generated method stub
        return null;
    }

}

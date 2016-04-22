package com.utobun.candy.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.utobun.candy.contants.ReturnCode;
import com.utobun.candy.contants.ReturnData;

/** 
* @ClassName: ReturnUtil 
* @Description: 接口数据返回工具类 
* @author HemingWu
* @date 2015年8月25日 下午11:25:01  
*/
public class ReturnUtil
{
    protected static Logger log = LoggerFactory.getLogger(ReturnUtil.class.getName());
    
    public static ReturnData fail(BindingResult br) 
    {
        FieldError fe = null;
        
        String paramErrors = "";
        
        for (int i = 0; i < br.getFieldErrors().size(); i++) 
        {
            fe = br.getFieldErrors().get(i);
            paramErrors += "; " + fe.getDefaultMessage() + ": " + fe.getField() + "=" + fe.getRejectedValue();
        }
        
        log.error(paramErrors.substring(2));

        return ReturnUtil.fail(ReturnCode.PARAM);
    }
    
    public static ReturnData fail(ReturnCode rc) 
    {
        Map<String, Object> data = new HashMap<String, Object>();
        log.info(rc.toString());
        return new ReturnData(rc, data);
    }
    public static ReturnData fail(ReturnCode rc, Map<String, Object> data) 
    {
        log.info(rc.toString());
        return new ReturnData(rc, data);
    }
    public static ReturnData success() 
    {
        Map<String, Object> data = new HashMap<String, Object>();
        return new ReturnData(ReturnCode.SUCCESS, data);
    }
    public static ReturnData success(Map<String, Object> data) 
    {
        return new ReturnData(ReturnCode.SUCCESS, data);
    }
}

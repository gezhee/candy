package com.utobun.candy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.utobun.candy.contants.ReturnData;

/** 
 * ClassName: IdentifyService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: 提供验证码服务. <br/> 
 * date: 2016年3月1日 下午1:14:51 <br/> 
 * 
 * @author HemingWu 
 * @version  
 * @since JDK 1.8 
 */
public interface VerifyService {
    
    /** 
     * identifyPic: 以图片的形式作为验证码<br/> 
     * @author HemingWu 
     * @param length
     * @param ifIdentify
     * @return  
     */ 
    ReturnData verifyPic(HttpServletRequest req);
}

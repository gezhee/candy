package com.utobun.candy.service;

import javax.servlet.http.HttpServletRequest;

import com.utobun.candy.contants.ReturnData;

/** 
 * ClassName: CandyService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: 平台的服务类，取名叫candy. <br/> 
 * date: 2016年3月1日 下午12:43:15 <br/> 
 * 
 * @author HemingWu 
 * @version  
 * @since JDK 1.8 
 */
public interface CandyService {
    
    /** 
     * welocome: 用户首次进入平台的欢迎方法<br/> 
     * @author HemingWu 
     * @param req
     * @return  
     */ 
    ReturnData welocome(HttpServletRequest req);
    
    
    /** 
     * identifyPic: 用户登陆验证图片<br/> 
     * @author HemingWu 
     * @param length
     * @return  
     */ 
    ReturnData identifyPic(int length);
    
}

package com.utobun.candy.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @ClassName: CookieUtil
* @Description: 操作cookie的类
* @author HemingWu
* @date 2015年10月4日 下午5:30:03
* 
*/

public class CookieUtil {
    /** 
    * @Title: addCookie 
    * @Description: 增加cookie
    * @param response
    * @param name
    * @param value
    * @param maxAge 
    * @return void
    * @throws 
    */ 
    public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        if(maxAge>0)  cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    
    /** 
    * @Title: getCookieByName 
    * @Description: 获取Cookie 
    * @param request
    * @param name
    * @return Cookie
    * @throws 
    */ 
    
    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }   
    }
    
    
    /** 
    * @Title: ReadCookieMap 
    * @Description: 将cookie封装到Map中
    * @param request
    * @return 
    * @return Map<String,Cookie>
    * @throws 
    */ 
    
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}

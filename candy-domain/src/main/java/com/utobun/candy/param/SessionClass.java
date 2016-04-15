package com.utobun.candy.param;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 * ClassName: SessionClass <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: session范围的对象. <br/> 
 * date: 2016年3月1日 下午1:36:07 <br/> 
 * 
 * @author HemingWu 
 * @version  
 * @since JDK 1.8 
 */
@Component
public class SessionClass {
    
    /** 
     * length:验证图片拼接位置的长度.  
     */
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
}

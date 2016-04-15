package com.utobun.candy.contants;

import java.util.Map;

/** 
* @ClassName: ReturnData 
* @Description: 接口返回数据结构 
* @author HemingWu
* @date 2015年8月25日 下午11:27:57  
*/
public class ReturnData
{
private static final long serialVersionUID = 1L;
    
    private ReturnCode returnCode; // 编码
    private Map<String, Object> data; // 内容

    public ReturnData(ReturnCode returnCode, Map<String, Object> data) {
        this.returnCode = returnCode;
        this.data = data;
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

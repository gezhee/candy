package com.utobun.candy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utobun.candy.contants.ReturnCode;
import com.utobun.candy.contants.ReturnData;
import com.utobun.candy.controller.base.ControllerAbstract;
import com.utobun.candy.service.IdentifyService;
import com.utobun.candy.tool.ReturnTool;

/** 
 * ClassName: IdentifyController <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO 验证控制类(可选). <br/> 
 * date: 2016年3月1日 下午3:24:52 <br/> 
 * 
 * @author HemingWu 
 * @version  
 * @since JDK 1.8 
 */
@Controller
@RequestMapping(value="/web/identify/")
public class IdentifyController extends ControllerAbstract{
    
    
    @Resource
    private IdentifyService identifyService;
    /** 
     * identifyPic: 图片验证控制类<br/> 
     * @author HemingWu 
     * @param length
     * @param ifIdentify
     * @return  
     */ 
    public ReturnData identifyPic(int length, boolean ifIdentify){
        
        try{
            return identifyService.identifyPic(length, ifIdentify);
        }
        catch(Exception e){
            return ReturnTool.fail(ReturnCode.SYSTEM_WRONG);
        }
    }
}

package com.utobun.candy.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utobun.candy.contants.ReturnData;
import com.utobun.candy.controller.base.ControllerAbstract;
import com.utobun.candy.service.VerifyService;

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
@RequestMapping(value="/web/b/")
public class VerifyController extends ControllerAbstract{
    
    @Resource
    private VerifyService verifyService;
    
    @RequestMapping("verify.do")
    public ReturnData identifyImg(HttpServletRequest req){
        return verifyService.verifyPic(req);
    }
}

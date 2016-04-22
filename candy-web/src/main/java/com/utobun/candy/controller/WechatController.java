package com.utobun.candy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utobun.candy.controller.base.ControllerAbstract;
import com.utobun.candy.util.SignUtil;

/**
 * @ClassName:  WechatController
 * @Description:微信
 * @author: HemingWu
 * @date:   2016年4月22日 下午3:29:45
 */

@Controller
@RequestMapping(value="/wechat/")
public class WechatController extends ControllerAbstract{
    
    @RequestMapping(value="authService.do")
    public void AuthService(HttpServletRequest req,HttpServletResponse res){
     // 微信加密签名  
        String signature = req.getParameter("signature");  
        // 时间戳  
        String timestamp = req.getParameter("timestamp");  
        // 随机数  
        String nonce = req.getParameter("nonce");  
        // 随机字符串  
        String echostr = req.getParameter("echostr");
        PrintWriter out;
        try {
            out = res.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
                out.print(echostr);  
            }
            out.close();
            out = null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.info("something wrong",e);
        }
    }
}

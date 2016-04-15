package com.utobun.candy.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utobun.candy.contants.Contants;
import com.utobun.candy.contants.ReturnData;
import com.utobun.candy.param.SessionClass;
import com.utobun.candy.service.VerifyService;
import com.utobun.candy.service.base.BaseServiceAbstract;
import com.utobun.candy.util.ReturnUtil;
import com.utobun.candy.util.VerifyCodeUtil;

/** 
 * ClassName: IdentifyServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: 提供验证服务的实现类. <br/> 
 * date: 2016年3月1日 下午1:24:48 <br/> 
 * 
 * @author HemingWu 
 * @version  
 * @since JDK 1.8 
 */
@Service
public class VerifyServiceImpl extends BaseServiceAbstract implements VerifyService{

    /** 
     * 返回一张图片给前端,
     * 前端挖取图片后,
     * 返回需要拼接上的长度,
     * 把该长度存储在session范围的对象中,
     * 用户验证时比较此长度，进行验证. 
     * @see com.utobun.candy.service.IdentifyService#identifyPic(int) 
     */ 
    
    
    @Override
    @Transactional
    public ReturnData verifyPic(HttpServletRequest req) {
        
        Map<String, Object> data = new HashMap<String, Object>();
        HttpSession session = req.getSession();
        //用时间戳来构造文件名，避免文件重复
        String imgName = String.valueOf(new Date().getTime());
        //获取web文件所在的物理路径
        String webRootPath = session.getServletContext().getRealPath("");
        //构造验证码图片存放的临时路径
        String tmpPath = Contants.TMP_PATH+"identifyImg/";
        File dir = new File(webRootPath+tmpPath);
        
        File file = new File(dir,imgName+".jpg");
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        session.setAttribute("verifyCode", verifyCode);
        try {
            VerifyCodeUtil.outputImage(200, 80, file, verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.put("img", req.getContextPath()+tmpPath+imgName+".jpg");
        return ReturnUtil.success(data);
    }

}

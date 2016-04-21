package com.utobun.candy.service.test;

import java.io.IOException;

import com.google.zxing.NotFoundException;
import com.utobun.candy.util.image.ImageUtil;
import com.utobun.candy.util.image.QRCodeUtil;

public class ImageTest {

    public static void main(String[] args){
        String path = Object.class.getResource("/").getPath()+"upload/";
        String dest = path + "qr.png";
        String target = path + "logoQr.png";
        String content = "http://www.baidu.com";
        String logo = path + "logo.png";
        QRCodeUtil.QRParam param= new QRCodeUtil().new QRParam();
        param.setCharset(ImageUtil.IMAGE_TYPE_PNG);
        param.setContent("http://www.baidu.com");
        param.setLogoPath(path + "logo.png");
        param.setLogoWidth(70);
        param.setLogoHeight(70);
        param.setNeedCompress(true);
        param.setQrCodesize(400);
        param.setTarget(path + "logoQr.png");
        
        //生成二维码
        try {
            QRCodeUtil.generateQRcode(content, dest, ImageUtil.IMAGE_TYPE_PNG, 500);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        //生成带logo二维码
        try {
            
            QRCodeUtil.generateLogoQRcode(param);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        //解析二维码
        try {
            System.out.println(QRCodeUtil.parseQRcode(dest));
        } catch (NotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

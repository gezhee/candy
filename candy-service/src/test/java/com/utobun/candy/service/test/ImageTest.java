package com.utobun.candy.service.test;

import java.io.IOException;

import com.utobun.candy.util.ImageUtil;

public class ImageTest {

    public static void main(String[] args){
        String path = Object.class.getResource("/").getPath()+"upload/";
        String src = path + "logo.jpg";
        try {
            ImageUtil.cutImage(src,path,10,10,10,10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

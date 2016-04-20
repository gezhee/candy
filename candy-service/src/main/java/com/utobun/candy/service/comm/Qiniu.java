package com.utobun.candy.service.comm;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.utobun.candy.comm.CandyConf;

public class Qiniu {
    
    protected static Logger log = LogManager.getLogger(Qiniu.class.getName());
    private String ACCESS_KEY = CandyConf.getValue("qiniu_ak");
    private String SECRET_KEY = CandyConf.getValue("qiniu_sk");
    private String bucketname = CandyConf.getValue("qiniu_candy");
    private String path = Object.class.getResource("/").getFile()+"/upload/";
    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    
    //创建上传对象
    private UploadManager uploadManager = new UploadManager();
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){
        return auth.uploadToken(bucketname);
    }
    
    //单文件上传
    public void upload(String key,String fileName){
        
        String file = path+fileName;
        try{
            //调用put方法上传
            Response res = uploadManager.put(file, key, getUpToken());
            //打印返回的信息
            log.info(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            log.info(r.toString());
            try {
                //响应的文本信息
                log.info(r.bodyString());
            } catch (QiniuException e1) {
                 
            }
        }
    }
    
    public static void main(String[] args){
        Qiniu qiniu = new Qiniu();
        qiniu.upload("test3.png","test.png");
    }
}

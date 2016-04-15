package com.utobun.candy.contants;

import java.io.File;
import java.util.Map;

/**
* @ClassName: EmailVo
* @Description: 邮件参数类
* @author HemingWu jianmo_o@163.com
* @date 2015年10月5日 下午2:01:07
* 
*/

public class EmailVo {
    
    /**
    * @Fields sender : 邮件发送者，必须和配置的email_username一致
    */
    private String sender;
    
    /**
    * @Fields receivers : 邮件接收者
    */
    private String[] receivers;
    
    /**
    * @Fields contents : 邮件内容
    */
    private Map<String,Object> contents;
    
    /**
    * @Fields testContent : 文本内容
    */
    private String textContent;
    
    /**
    * @Fields subject : 邮件主题
    */
    private String subject;
    
    /**
    * @Fields attachFile : 邮件附件
    */
    private File[] attachFile;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String[] getReceivers() {
        return receivers;
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
    }

    public Map<String, Object> getContents() {
        return contents;
    }

    public void setContents(Map<String, Object> contents) {
        this.contents = contents;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public File[] getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(File[] attachFile) {
        this.attachFile = attachFile;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
    
}

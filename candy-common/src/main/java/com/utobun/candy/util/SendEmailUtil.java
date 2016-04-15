package com.utobun.candy.util;

import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.utobun.candy.contants.EmailVo;
import freemarker.template.Template;

/**
* @ClassName: SendEmailUtil
* @Description: 发送邮件工具类
* @author HemingWu jianmo_o@163.com
* @date 2015年10月5日 下午1:29:43
* 
*/
@Service
public class SendEmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private FreeMarkerConfigurer freeMarker;
    /** 
    * @Title: sendEmailWithText 
    * @Description: 发送简单的文本邮件 
    * @param emailVo
    * @param date
    * @return void
    * @throws 
    */ 
    
    public void sendEmailWithText(EmailVo emailVo, Date date){
        SimpleMailMessage simpleTextMessage = new SimpleMailMessage();
        simpleTextMessage.setFrom(emailVo.getSender());
        simpleTextMessage.setTo(emailVo.getReceivers());
        simpleTextMessage.setText(emailVo.getTextContent());
        if(null == date){
            date = new Date();
        }
        simpleTextMessage.setSentDate(date);
        javaMailSender.send(simpleTextMessage);
    }
    
    /** 
    * @Title: sendEmailWithFile 
    * @Description: 发送带附件的简单文本邮件 
    * @param emailVo
    * @param date
    * @throws MessagingException
    * @return void
    * @throws 
    */ 
    
    public void sendSimpleEmailWithFile(EmailVo emailVo , Date date) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(emailVo.getSender());
        helper.setValidateAddresses(true);
        String contents=emailVo.getTextContent();
        if(contents!=null && !contents.isEmpty()){
            helper.setText(contents, true);
        }
        helper.setSubject(emailVo.getSubject());
        helper.setTo(emailVo.getReceivers());
        if(null == date){
            date = new Date();
        }
        helper.setSentDate(date);
        javaMailSender.send(message);
    }
    
    /** 
    * @Title: sendEmailWithTempalate 
    * @Description: 使用freeMarker模板发送邮件 
    * @param emailVo
    * @param date
    * @param template为放在classpath:template文件夹下的ftl文件
    * @return void
    * @throws 
    */ 
    
    public void sendEmailWithTempalate(EmailVo emailVo , Date date, String template) 
                throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(emailVo.getSender());
        helper.setSubject(emailVo.getSubject());
        String htmlText=getMailText(emailVo.getContents(), template);
        helper.setText(htmlText, true);
        if(date == null){
            date = new Date();
        }
        helper.setSentDate(date);
        javaMailSender.send(message);
    }
    
    /** 
    * @Title: sendEmailWithFile 
    * @Description: 使用freemarker模板发送带附件的邮件 
    * @param emailVo
    * @param date
    * @param template
    * @throws MessagingException
    * @return void
    * @throws 
    */ 
    
    public void sendEmailWithFile(EmailVo emailVo , Date date, String template)
            throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(emailVo.getSender());
        helper.setSubject(emailVo.getSubject());
        String htmlText=getMailText(emailVo.getContents(), template);
        helper.setText(htmlText, true);
        helper.setTo(emailVo.getReceivers());
        if(date == null){
            date = new Date();
        }
        helper.setSentDate(date);
        javaMailSender.send(message);
    }
    
    //通过模板构造邮件内容，参数username将替换模板文件中的${username}标签.
    private String getMailText(Map<String,Object> content, String template){      
        String htmlText="";      
        try {
            //通过指定模板名获取FreeMarker模板实例
            Template tpl=freeMarker.getConfiguration().getTemplate(template);
            //解析模板并替换动态数据，最终username将替换模板文件中的${username}标签
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return htmlText;
    }
    
}


package com.mr.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * Created by ydd on 2018/5/9.
 */
public class MailUtil {

    /**
     * 通过javaMail发送邮件
     * @param fromEmail 发送人的邮箱
     * @param eMailAuthPassword 发送人的授权码
     * @param eMailType     邮箱的类型
     * @param emailTo       收件人的邮箱
     * @param content       正文
     * @param subject       主题
     * @return
     */
    public static boolean sendMail(String fromEmail,String eMailAuthPassword,String eMailType,
                                   String emailTo,String content,String subject) {

        /*fromEmail="1900455297@qq.com";//你的QQ邮箱
        eMailType="smtp.qq.com";
        eMailAuthPassword="ipalhkwbxypochaf";//ipalhkwbxypochaf
        //QQ邮箱授权码 开通POP3/SMTP服务 的授权码*/
        String body = content; //正文内容
        try {
            //****************************创建会话************************************
            //JavaMail需要Properties来创建一个session对象。它将寻找字符串mail.smtp.host，属性值就是发送邮件的主机
            Properties props = new Properties();
            props.put("mail.smtp.host",eMailType); //发件人使用发邮件的电子信箱服务器
            props.put("mail.password",eMailAuthPassword);//发送方的密码
            //transport类用协议指定的语言发送消息（通常是SMTP），指定邮件发送协议
            props.put("mail.transport.protocol", "smtp");
            props.setProperty("mail.debug", "true");// 调试模式
            props.put("mail.smtp.auth","true"); //这样才能通过验证， 向SMTP服务器提交用户认证

            //下面这三句很重要，如果没有加入进去，qq邮箱会验证不成功，一直报503错误
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");

            //获得默认的session对象，建立会话
            Session mailSession = Session.getInstance(props);
            mailSession.setDebug(true);//debug模式
            //*****************************构造消息***************************************
            MimeMessage mimeMessage = new MimeMessage(mailSession);//生成消息体
            InternetAddress from=new InternetAddress(fromEmail);//发件人地址
            mimeMessage.setFrom(from);
            InternetAddress to=new InternetAddress(emailTo); //设置收件人地址并规定其类型
            mimeMessage.setRecipient(Message.RecipientType.TO,to);

            mimeMessage.setSentDate(new Date()); //设置发信时间‘
            mimeMessage.setSubject(subject); //设置主题
            mimeMessage.setText(body); //设置 正文

            //给消息对象设置内容
            BodyPart bodyPart=new MimeBodyPart();	//新建一个存放信件内容的BodyPart对象
            bodyPart.setContent(body, "text/html;charset= GB2312");	//设置 发送邮件内容为HTML类型,并为中文编码
            // 一般保存电子邮件内容的容器是Multipart抽象类，它定义了增加和删除及获得电子邮件不同部分内容的方法。由于Multipart是抽象类，我们必须为它使用一个具体的子类，JavaMail API提供javax.mail.Internet.MimeMultpart类来使用MimeMessage对象。
            Multipart multipart=new MimeMultipart();
            multipart.addBodyPart(bodyPart);

            mimeMessage.setContent(multipart);
            mimeMessage.saveChanges();

            //发送消息
            Transport transport=mailSession.getTransport("smtp");
            transport.connect(eMailType,fromEmail,eMailAuthPassword);
            //发邮件人帐户和开通POP3/IMAP的“授权码”
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();
//*******************************发送消息********************************
            mimeMessage.writeTo(System.out);//保存消息 并在控制台 显示消息对象中属性信息
            System.out.println("邮件已成功发送到 " + emailTo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
    }
}

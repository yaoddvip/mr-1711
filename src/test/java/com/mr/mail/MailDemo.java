package com.mr.mail;

import com.mr.util.MailUtil;
import org.junit.Test;

/**
 * Created by ydd on 2018/5/9.
 */
public class MailDemo {

    @Test
    public void test1(){
        String fromEmail = "925904115@qq.com";
        String eMailAuthPassword = "lgwbqemhktalbdgd";
        String eMailType = "smtp.qq.com";
        String emailTo = "1294076932@qq.com";
        String content = "验证邮件发送";
        String subject = "javaMail";

        MailUtil.sendMail(fromEmail,eMailAuthPassword,
                eMailType,emailTo,content,subject);

    }

    @Test
    public void test2(){
        String fromEmail = "yaodongdongvip@163.com";
        String eMailAuthPassword = "qwertyuiop123";
        String eMailType = "smtp.163.com";
        String emailTo = "1294076932@qq.com";
        String content = "abcd";
        String subject = "aaa";
        MailUtil.sendMail(fromEmail,eMailAuthPassword,
                eMailType,emailTo,content,subject);

    }



}

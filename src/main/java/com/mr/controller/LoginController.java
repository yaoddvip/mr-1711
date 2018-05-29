package com.mr.controller;

import com.mr.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ydd on 2018/5/8.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Value("${LOGIN_CHECK_KEY}")
    private String LOGIN_CHECK_KEY;

    //登录
    @ResponseBody
    @RequestMapping("login")
    public String login(String userName , String userPsw , String userCheck,
                        HttpSession session){

        if(userName.equals("admin")
                && session.getAttribute(LOGIN_CHECK_KEY).equals(userCheck)){ //登录成功
            return "success";
        }
        return "error";
    }

    @ResponseBody
    @RequestMapping("check")
    public String check(String userCheck,HttpSession session){
        System.out.println(LOGIN_CHECK_KEY);
        String code = (String) session.getAttribute(LOGIN_CHECK_KEY);
        if(code.equals(userCheck)){
            return "1"; //1：代表正确
        }
        return "2";//2代表错误
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/user/check")
    public void createCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");

        CaptchaUtil util = CaptchaUtil.Instance();
        // 将验证码输入到session中，用来验证
        String code = util.getString();
        request.getSession().setAttribute(LOGIN_CHECK_KEY, code);
        // 输出打web页面
        ImageIO.write(util.getImage(), "jpg", response.getOutputStream());
    }

}

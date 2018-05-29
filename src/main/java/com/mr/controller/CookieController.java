package com.mr.controller;

import com.mr.model.User;
import com.mr.util.CookieUtils;
import com.mr.util.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by ydd on 2018/5/21.
 */
@Controller
public class CookieController {

    @ResponseBody
    @RequestMapping("/coo/set")
    public String setCookieValue(HttpServletRequest request , HttpServletResponse response){
        User user = new User();
        user.setUserId(1);
        user.setUserName("张三");
        user.setUserBir(new Date());
        user.setUserAge(19);

        String cookieName = "cookie1";
        CookieUtils.setCookie(request,response,cookieName, JsonUtils.objectToJson(user),true);

        return "success";
    }

    @ResponseBody
    @RequestMapping("/coo/get")
    public String getCookieValue(HttpServletRequest request ){

        String cookieName = "cookie1";
        String json = CookieUtils.getCookieValue(request, cookieName, true);

        return json;
    }

}

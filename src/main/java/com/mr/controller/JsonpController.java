package com.mr.controller;

import com.mr.model.User;
import com.mr.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by ydd on 2018/5/10.
 */
@Controller
@RequestMapping("jsonp")
public class JsonpController {

    /**
     *
     * produces="text/html;charset=UTF-8" 解决中文乱码问题
     * @param callback
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/testJsonp",produces="text/html;charset=UTF-8")
    public String testJsonp(String callback){
        User user = new User(1,"张三" ,
                18 , 1 ,new Date());
        //判断是否为jsonp跨域请求
        if(StringUtils.isNotBlank(callback)){//判断不为null，是jsonp请求
            //callback({json字符串})
            return callback+"("+ JsonUtils.objectToJson(user)+")";
        }
        //如果不是跨域请求，返回json
        return JsonUtils.objectToJson(user);
    }
}

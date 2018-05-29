package com.mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ydd on 2018/5/14.
 */
@Controller
public class TestController {

    private String a = "abc";
    private Integer b = 1;

    @ResponseBody
    @RequestMapping("/test/test")
    public String test(){
        System.out.println("test...");
        System.out.println(a);
        test2();
        return "success";
    }

    public void test2(){
        System.out.println("test2....");
    }
}

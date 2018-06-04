package com.mr.httpclient;

import com.mr.util.JsonUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ydd on 2018/6/4.
 */
public class Demo {
    /**
     * 发送get请求，访问服务
     */
    @Test
    public void test1(){
        String s = HttpClientUtil.doGet("http://localhost:8082/client/test1");
        System.out.println(s);
    }

    /**
     * get请求，传递参数
     */
    @Test
    public void test2(){
        Map<String, String> param = new HashMap<String, String>();
        param.put("name","张伟");
        param.put("age","18");
        String s = HttpClientUtil.doGet("http://localhost:8082/client/test2",param);
        System.out.println(s);
    }

    /**
     * post方法调用，传递参数
     */
    @Test
    public void test3(){
        Map<String, String> param = new HashMap<String, String>();
        param.put("name","张伟");
        param.put("age","18");
        String s = HttpClientUtil.doPost("http://localhost:8082/client/test3",param);
        System.out.println(s);
    }

    /**
     * post方法调用，返回json数据
     */
    @Test
    public void test4(){
        String s = HttpClientUtil.doPost("http://localhost:8082/client/test4");
        System.out.println(s);//{"id":1,"name":"张三","age":19}
        User user = JsonUtils.jsonToPojo(s, User.class);
        System.out.println(user);//User{id=1, name='张三', age=19}
    }


}

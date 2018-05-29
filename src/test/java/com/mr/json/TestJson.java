package com.mr.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ydd on 2018/5/7.
 */
public class TestJson {

    /**
     * 将对象转换为json字符串
     */
    @Test
    public void test1() throws JsonProcessingException {
        //创建对象
        User user = new User(1,"张三",18, new Date());
        //通过jackson将user对象转换为json字符串
        //ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
        ObjectMapper mapper = new ObjectMapper();
        //通过writeValueAsString(对象)将对象转为字符串
        String json = mapper.writeValueAsString(user);
        //输出
        System.out.println(json);//{"id":1,"name":"张三","age":18,"bir":1525660078911}


        //将list转换为json数据
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(new User(2,"李四",20, new Date()));
        String listJson = mapper.writeValueAsString(list);
        //[{"id":1,"name":"张三","age":18,"bir":1525660253560},{"id":2,"name":"李四","age":20,"bir":1525660253884}]
        System.out.println(listJson);
    }


    /**
     * 将json字符串转换为对象
     */
    @Test
    public void test2() throws IOException {
        //先定义一个json字符串
        String str = "{\"id\":1,\"name\":\"张三\",\"age\":18,\"bir\":1525660078911}";

        ObjectMapper mapper = new ObjectMapper();
        //readValue("json字符串", 类型)；
        User user = mapper.readValue(str, User.class);
        //User(id=1, name=张三, age=18, bir=Mon May 07 10:27:58 CST 2018)
        System.out.println(user);
    }


    /**
     * 将json字符串转换为List对象
     */
    @Test
    public void test3() throws IOException {
        //先定义一个json字符串
        String str = "[{\"id\":1,\"name\":\"张三\",\"age\":18,\"bir\":1525660253560},{\"id\":2,\"name\":\"李四\",\"age\":20,\"bir\":1525660253884}]";

        ObjectMapper mapper = new ObjectMapper();
        //readValue("json字符串", 类型)；
        List<User> users = mapper.readValue(str, new TypeReference<List<User>>() {
        });
        System.out.println(users);
    }

    /**
     * 通过工具类将对象转换为json字符串
     */
    @Test
    public void test4(){
        //创建对象
        User user = new User(1,"张三",18, new Date());
        String json = JsonUtils.objectToJson(user);
        //{"id":1,"name":"张三","age":18,"bir":1525660943342}
        System.out.println(json);
    }


    @Test
    public void test5() {

        String str = "{\"id\":1,\"name\":\"张三\",\"age\":18,\"bir\":1525660943342}";

        User user = JsonUtils.jsonToPojo(str, User.class);

        //User(id=1, name=张三, age=18, bir=Mon May 07 10:42:23 CST 2018)
        System.out.println(user);
    }
    @Test
    public void test6() {

        String str = "[{\"id\":1,\"name\":\"张三\",\"age\":18,\"bir\":1525660253560},{\"id\":2,\"name\":\"李四\",\"age\":20,\"bir\":1525660253884}]";

        List<User> users = JsonUtils.jsonToList(str, User.class);

        //[User(id=1, name=张三, age=18, bir=Mon May 07 10:30:53 CST 2018), User(id=2, name=李四, age=20, bir=Mon May 07 10:30:53 CST 2018)]
        System.out.println(users);
    }

}

package com.mr.controller;

import com.mr.model.User;
import com.mr.redis.JedisClient;
import com.mr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by ydd on 2018/5/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "list" , produces="text/html;charset=UTF-8")
    public String getUserList(){
        String json = jedisClient.get("userList");
        return json;
    }

    /**
     * 查询数据
     * @return
     */
    @RequestMapping("/users")
    public String getList(ModelMap map){
        List<User> list = userService.getList();
        map.put("list" , list);
        return "user/list";
    }

    /**
     * 增加
     * 注意：数据保持同步
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public String add(User user){
        userService.add(user);

        //增加完之后，重定向到查询页面
        return "redirect:/user/users";
    }


}

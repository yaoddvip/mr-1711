package com.mr;

import com.mr.json.JsonUtils;
import com.mr.json.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by ydd on 2018/5/11.
 */
public class JedisDemo {

    @Test
    public void test1(){
        //通过jedis连接redis服务
        Jedis jedis = new Jedis("127.0.0.1" , 6379);

        //测试连接
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.close();
    }

    @Test
    public void test2(){
        //通过jedis连接redis服务
        Jedis jedis = new Jedis("127.0.0.1" , 6379);

        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        jedis.flushDB();

        Set<String> key = jedis.keys("*");
        System.out.println(key);
        jedis.close();
    }

    @Test
    public void test3(){
        Jedis jedis = new Jedis();
        String set = jedis.set("k1", "v1");

        System.out.println(jedis.get("k1"));
        jedis.close();
    }

    @Test
    public void test4(){
        //模拟查询一个list集合数据
        List<User> list = new ArrayList<User>();
        list.add(new User(1 , "张三", 18 ,new Date()));
        list.add(new User(2 , "张三1", 19 ,new Date()));
        list.add(new User(3 , "张三2", 10 ,new Date()));

        Jedis jedis = new Jedis();
        //string类型  不能直接存放对象，需要json字符串
        jedis.set("userList" , JsonUtils.objectToJson(list));
        jedis.close();
    }

    //获取redis中的list集合
    @Test
    public void test5(){
        Jedis jedis = new Jedis();
        String json = jedis.get("userList");
        List<User> users = JsonUtils.jsonToList(json, User.class);
        for(User user : users){
            System.out.println(user);
        }
        jedis.close();
    }

    @Test
    public void test6(){
        JedisPool pool = new JedisPool();
        Jedis jedis = pool.getResource();
        String json = jedis.get("userList");
        System.out.println(json);
        //jedis使用完之后，放回到连接池中,2.9之后，直接将连接关闭
        jedis.close();
    }

    @Test
    public void test7(){
        //模拟查询一个list集合数据
        List<User> list = new ArrayList<User>();
        list.add(new User(1 , "张三", 18 ,new Date()));
        list.add(new User(2 , "张三1", 19 ,new Date()));
        list.add(new User(3 , "张三2", 10 ,new Date()));

        JedisPool pool = new JedisPool();
        Jedis jedis = pool.getResource();

        jedis.hset("users" , "user" , JsonUtils.objectToJson(list));

        jedis.close();
    }

    @Test
    public void test8(){

        JedisPool pool = new JedisPool();
        Jedis jedis = pool.getResource();

        String json = jedis.hget("users", "user");
        System.out.println(json);

        jedis.close();
    }


}

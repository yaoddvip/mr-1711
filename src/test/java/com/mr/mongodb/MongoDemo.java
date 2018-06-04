package com.mr.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ydd on 2018/5/30.
 */
public class MongoDemo {

    /**
     * 插入单条数据
     */
    @Test
    public void test(){
        //连接服务器
        MongoClient client = new MongoClient("127.0.0.1",27017);
        //选择相对应的库
        MongoDatabase database = client.getDatabase("mr-demo");
        //选择相对应的集合
        MongoCollection<Document> users = database.getCollection("users");
        //创建集合数据
        Document document = new Document();
        document.append("name","张三");
        document.append("age",8);
        //集合中添加数据
        users.insertOne(document);
        //关闭资源
        client.close();
    }

    /**
     * 测试数据性能
     */
    @Test
    public void test1(){
        long startTime = System.currentTimeMillis();
        //连接服务器
        MongoClient client = new MongoClient("127.0.0.1",27017);
        //选择相对应的库
        MongoDatabase database = client.getDatabase("mr-demo");
        //选择相对应的集合
        MongoCollection<Document> users = database.getCollection("users");

        for (int i = 0; i < 100000; i++) {
            //创建集合数据
            Document document = new Document();
            document.append("name","张三");
            document.append("age",8);
            //集合中添加数据
            users.insertOne(document);
        }
        //关闭资源
        client.close();
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间："+(endTime-startTime));
    }

    /**
     * 插入多条数据
     */
    @Test
    public void test2(){
        //连接服务器
        MongoClient client = new MongoClient("127.0.0.1",27017);
        //选择相对应的库
        MongoDatabase database = client.getDatabase("mr-demo");
        //选择相对应的集合
        MongoCollection<Document> users = database.getCollection("users");

        Document d1 = new Document("name","张三");
        d1.append("age",58);
        Document d2 = new Document("name","李四");
        d2.append("age",68);
        Document d3 = new Document("name","王五");
        d3.append("age",78);

        users.insertMany(Arrays.asList(d1 , d2 , d3));
        //关闭资源
        client.close();
    }

    /**
     * 单个条件删除
     */
    @Test
    public void del1(){
        //连接服务器
        MongoClient client = new MongoClient("127.0.0.1",27017);
        //选择相对应的库
        MongoDatabase database = client.getDatabase("mr-demo");
        //选择相对应的集合
        MongoCollection<Document> users = database.getCollection("users");
        //创建条件
        Bson bson = Filters.eq("name", "张三");
        users.deleteOne(bson);
        //关闭资源
        client.close();
    }

    /**
     * 多条件删除
     */
    @Test
    public void del2(){
        //连接服务器
        MongoClient client = new MongoClient("127.0.0.1",27017);
        //选择相对应的库
        MongoDatabase database = client.getDatabase("mr-demo");
        //选择相对应的集合
        MongoCollection<Document> users = database.getCollection("users");
        //创建条件
        //delete from users where age >=38 and age <= 58
        Bson bson = Filters.gte("age", 38);
        Bson bson2 = Filters.lte("age", 58);
        Bson and = Filters.and(bson, bson2);
        users.deleteMany(and);
        //关闭资源
        client.close();
    }
}

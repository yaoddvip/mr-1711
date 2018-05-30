package com.mr.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * Created by ydd on 2018/5/30.
 */
public class MongoDemo {

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

}

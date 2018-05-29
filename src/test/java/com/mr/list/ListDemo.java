package com.mr.list;

import org.junit.Test;

import java.util.*;

/**
 * Created by ydd on 2018/5/8.
 */
public class ListDemo {

    @Test
    public void test1(){
        List<String> list = new ArrayList<String>();
        for(int i = 1 ; i<25 ;i++){
            list.add(i+"");
            System.out.println(i);
        }
    }


    @Test
    public void test2(){

        Set<String> set = new HashSet<String>();
        set.add("d");
        set.add("a");
        set.add("xx");
        set.add("c");
        System.out.println(set);


        Set<String> set1 = new LinkedHashSet<String>();
        set1.add("d");
        set1.add("a");
        set1.add("xx");
        set1.add("c");
        System.out.println(set1);
    }

    @Test
    public void test3(){
        Map<String , String > map = new HashMap<String, String>();

        //put()方法赋值，如果有值存在，则替换。返回的是被替换的值，
        String put = map.put("k1", "v1");
        String put1 = map.put("k1", "v2");
        System.out.println(put);
        System.out.println(put1);
    }

}

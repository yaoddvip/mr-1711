package com.mr.collection;

import org.junit.Test;

import java.util.*;

/**
 * Created by ydd on 2018/5/8.
 */
public class CollectionDemo {

    /**
     * clear()清除集合中所有的元素
     */
    @Test
    public void test1(){
        Collection collection = new ArrayList<String>();
        //如果此 collection 由于调用而发生更改，则返回 true
        collection.add("1711");
        collection.add("1712");
        collection.add("1709");
        System.out.println(collection);
        System.out.println("=================");//[1711, 1712, 1709]
        collection.clear();
        System.out.println(collection);//[]
    }

    /**
     * contains
     */
    @Test
    public void test2(){
        Collection collection = new ArrayList<String>();
        collection.add("1711");
        collection.add("1712");
        collection.add("1709");
        //判断集合中是否包含1711，如果包含返回true,否则返回false
        boolean b = collection.contains("1710");
        System.out.println(b);
    }


    /**
     * toArray 将集合转换为数组
     */
    @Test
    public void test3(){
        Collection collection = new ArrayList<String>();
        collection.add("1711");
        collection.add("1712");
        collection.add("1709");
        Object[] array = collection.toArray();
        System.out.println(array.length);
    }

    /**
     * remove 移除
     *
     * 注意：如果集合中有多个该元素，则移除第一元素
     */
    @Test
    public void test4(){
        Collection collection = new ArrayList<String>();
        collection.add("1711");
        collection.add("1712");
        collection.add("1709");
        collection.add("1711");
        System.out.println(collection);
        boolean b = collection.remove("1711");
        System.out.println(collection);
    }

    @Test
    public void test5(){
        List<String> list = new ArrayList<String>();
        list.add("1711");
        list.add("1712");
        list.add("1709");
        list.add("1711");

        for(String str : list){
            System.out.println(str);
        }

        /*for(int i = 0;i<list.size() ; i++){
            System.out.println(list.get(i));
        }*/
    }

    @Test
    public void test6() {
        Set<String> set = new HashSet<String>();
        set.add("1711");
        set.add("1712");
        set.add("1709");
        //foreach(类型  变量名: 需要循环的对象)
        for(String str : set){
            System.out.println(str);
        }
    }

    /**
     * iterator 迭代器
     *      hasNext()：是否有下一个元素
     *         next()：返回下一个元素的值
     */
    @Test
    public void test7(){
        Collection collection = new HashSet<String>();
        collection.add("1711");
        collection.add("1712");
        collection.add("1709");
        collection.add("1711");

        Iterator iterator = collection.iterator();
        //通过iterator.hasNext() 是否有下一个元素
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //循环完之后，再次调用，则报错
        System.out.println(iterator.next());
    }

}

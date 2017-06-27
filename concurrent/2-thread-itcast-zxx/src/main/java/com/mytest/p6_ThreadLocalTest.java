package com.mytest;

import java.util.Random;

/**
 * threadlocal概念及使用技巧
 * threadlocal：jdk提供的类似Map<Thread,Object>机制，主要为了实现线程数据共享
 *
 * 应用场景：
 * 线程需要共享又需要隔离
 * struts2的ActionContext
 *
 * Created by 37 on 2017/3/21.
 */
public class p6_ThreadLocalTest {
    public static void main(String[] args) {
        for(int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MyThreadData instance = MyThreadData.instance();
                    int data = new Random().nextInt();

                    instance.setAge(data);
                    instance.setName("name" + data);

                    System.out.print(Thread.currentThread());
                    System.out.print(instance.getName());
                    System.out.println(instance.getAge());
                }
            }).start();
        }
    }
}

/**
 * 设计思路
 * 用一个单例类来封装每个线程需要的信息
 * 把自己存在了ThreadLocal，对外提供方法，从ThreadLocal里获取自己
 *
 * 模仿Struts2
 */
class MyThreadData {
    private static ThreadLocal<MyThreadData> data = new ThreadLocal<MyThreadData>();
    private String name;
    private int age;

    private MyThreadData() {
    }

    public static MyThreadData instance() {
        MyThreadData myThreadData = data.get();
        if(myThreadData == null) {
            myThreadData = new MyThreadData();
            data.set(myThreadData);
        }
        return myThreadData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
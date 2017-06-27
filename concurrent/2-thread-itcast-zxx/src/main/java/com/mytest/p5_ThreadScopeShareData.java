package com.mytest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 5.线程范围内共享数据
 * 用一个map来记录
 * Created by 37 on 2017/3/21.
 */
public class p5_ThreadScopeShareData {
    private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread() + " has put data " + data);
                    threadData.put(Thread.currentThread(), data);

                    new A().get();
                    new B().get();

                }
            }).start();
        }

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                threadData.put(Thread.currentThread(), new Random().nextInt());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadData.put(Thread.currentThread(), new Random().nextInt());
            }
        }).start();*/
    }

    static class A {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println(Thread.currentThread() + "get data " + data);
        }
    }

    static class B {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println(Thread.currentThread() + " get data " + data);
        }
    }
}

package com.mytest;

/**
 * 4.线程的同步
 * 面试题：子线程10次，主线程100次，循环往复50次
 *
 * 加锁：只能保证互相不干扰，不能保证顺序有变化。
 * 解决方法：把业务逻辑分离出来
 *
 * Created by 37 on 2017/3/21.
 */
public class p4_TraditionalThreadCommunication {
    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 50; i++) {
                            synchronized (lock) {
                                for (int j = 1; j <= 10; j++) {
                                    System.out.println("sub " + j + " of " + i);
                                }
                            }

                        }
                    }
                }
        ).start();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 50; i++) {
                            synchronized (lock) {
                                for (int j = 1; j <= 100; j++) {
                                    System.out.println("main " + j + " of " + i);
                                }
                            }
                        }
                    }
                }
        ).start();
    }




}

package com.mytest;

/**
 * 线程同步-线程安全版
 * 引例：线程安全办
 * Created by 37 on 2017/3/21.
 */
public class p3_TraditionalThreadSynchronized_2 {
    public static void main(String[] args) {
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("zhangxiaoxiang");
                }

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("lihuoming");
                }

            }
        }).start();
    }

    static class Outputer {
        //用各种方法让他们同步即可
        //synchronized块 synchronized方法 静态synchronized方法
        public synchronized void output(String name) {
            for(int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}

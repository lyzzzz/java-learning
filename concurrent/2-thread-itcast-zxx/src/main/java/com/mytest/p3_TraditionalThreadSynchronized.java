package com.mytest;

/**
 * 3.线程同步-线程不安全版
 * 引例：用一个Outputer输出字符串，因为字符串不具有原子性。所以会产生线程安全问题
 * Created by 37 on 2017/3/21.
 */
public class p3_TraditionalThreadSynchronized {
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
        public void output(String name) {
            for(int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}

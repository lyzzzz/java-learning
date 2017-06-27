package com.mytest;

/**
 * 1.传统的线程方式
 */
public class p1_TraditionalThread {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1:" + Thread.currentThread().getName());
                    System.out.println("2:" + this.getName());
                }

            }
        };
        thread.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2:" + Thread.currentThread().getName());
            }
        };
        thread2.start();
    }
}
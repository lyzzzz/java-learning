package com.test.concurrent.ch2_code;

/**
 * Created by lyzzzz on 2017-05-14.
 */
public class c5_SimpleWN {
    static Object lock = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("t1 start.");

                try {
                    System.out.println("t1 wait.");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("t1 end.");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("t2 start.");

                System.out.println("t2 notify.");
                lock.notify();
                System.out.println("t2 end.");
            }
        }
    }

    public static void main(String[] args) {
        new T1().start();
        new T2().start();
    }
}

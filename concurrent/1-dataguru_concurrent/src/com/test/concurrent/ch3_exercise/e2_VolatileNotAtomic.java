package com.test.concurrent.ch3_exercise;

/**
 2. 网上有些文章说，volatile不能保证原子性，但是，课程里却说，volatile可以保证原子性，你怎么看这个问题
 */
public class e2_VolatileNotAtomic {
    public static volatile int a = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++) {
                    a++;
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++) {
                    a++;
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(a);
    }
}

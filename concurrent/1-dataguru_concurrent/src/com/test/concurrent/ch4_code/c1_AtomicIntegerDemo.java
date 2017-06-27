package com.test.concurrent.ch4_code;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试atomicInteger的原子加
 * Created by lyzzzz on 2017-05-16.
 */
public class c1_AtomicIntegerDemo {
    static AtomicInteger a = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int k = 0; k < 10; k++) {
                        a.incrementAndGet();
                    }
                }
            });
        }
        for(int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for(int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println(a.get());
    }
}

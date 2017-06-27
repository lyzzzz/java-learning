package com.mytest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 7.多线程之间共享数据
 *
 * 基本思路：传对象
 *
 * 面试题：设计4个线程，其中两个线程每次对j增加1，另外两个线程对j减少1
 *
 * Created by 37 on 2017/3/21.
 */
public class p7_MultiThreadShareData {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        for(int i = 0; i < 2; i++) {
            new Thread(new MyRunnable1(shareData)).start();
            new Thread(new MyRunnable2(shareData)).start();
        }
    }

    static class MyRunnable1 implements Runnable {
        private ShareData shareData;

        public MyRunnable1(ShareData shareData) {
            this.shareData = shareData;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++) {
                shareData.inc();
                System.out.println("inc " + shareData.count);

            }
        }
    }

    static class MyRunnable2 implements Runnable {
        private ShareData shareData;

        public MyRunnable2(ShareData shareData) {
            this.shareData = shareData;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++) {
                shareData.dec();
                System.out.println("dec " + shareData.count);

            }
        }
    }

    static class ShareData {
        private AtomicInteger count = new AtomicInteger(100);

        public synchronized void inc() {
            count.incrementAndGet();
            //count++;
        }

        public synchronized void dec() {
            count.decrementAndGet();
            //count--;
        }
    }
}

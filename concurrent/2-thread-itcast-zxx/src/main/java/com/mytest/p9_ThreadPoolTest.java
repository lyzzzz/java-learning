package com.mytest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 9.线程池
 *
 * fixed
 * cached
 * single
 * scheduled
 * Created by 37 on 2017/3/22.
 */
public class p9_ThreadPoolTest {
    public static void main(String[] args) {
        //固定线程池
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        //动态线程池
        //ExecutorService executorService = Executors.newCachedThreadPool();
        //单线程池
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //调度线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        for(int i = 1; i <= 10; i++) {
            final Integer task = i;
            executorService.submit(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for(int j = 1; j <= 10; j++) {
                                System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for task " + task);
                            }
                        }
                    }
            );
        }

        //调度线程池
        /*executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing...");
            }
        }, 2, TimeUnit.SECONDS);*/
        //executorService.shutdownNow();
        executorService.shutdown();
    }
}

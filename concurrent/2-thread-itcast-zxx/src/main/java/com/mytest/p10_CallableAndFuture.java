package com.mytest;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 10.callable和future 主要用于异步执行
 * Created by 37 on 2017/3/30.
 */
public class p10_CallableAndFuture {
    public static void main1(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 单个future和callable
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        Future<String> future = singleThreadExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1500);
                return "hello";
            }
        });
        System.out.println(future.get());
        singleThreadExecutor.shutdown();
    }

    /**
     * 运行10次，10次都是不同时间，依次获取执行完毕的结果
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);
        for(int i = 0; i < 10; i++) {
            final int finalI = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return finalI;
                }
            });
        }

        for(int i = 0; i < 10; i++) {
            Future<Integer> future = completionService.take();
            System.out.println(future.get());
        }

        executorService.shutdown();

    }
}

package com.mytest;

/**
 * 线程的同步-面试题，子线程执行10次，主线程执行100次，如此50轮
 * 主线程与子线程之间不能互相干扰-加互斥锁
 * 要保证顺序-线程之间要用到同步
 *
 * 有点类似生产者消费者模式
 * Created by 37 on 2017/3/21.
 */
public class p4_TraditionalThreadCommunication_2 {
    public static void main(String[] args) throws InterruptedException {
        final Business business = new Business();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i <= 50; i++) {
                            try {
                                business.sub();
                                business.main();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();


    }

    static class Business {
        private boolean shouldSub = true;

        public void main() {
            synchronized (Business.class) {
                while(shouldSub) {
                    this.notify();
                }
                for(int i = 1; i <= 100; i++) {
                    System.out.println("main " + i);
                }
                shouldSub = true;

            }

        }

        public void sub() throws InterruptedException {
            synchronized (Business.class) {
                while(!shouldSub) {     //因为有些时候会假唤醒。所以必须有一个东西去wait
                    this.wait();
                }
                for(int i = 1; i <= 10; i++) {
                    System.out.println("sub " + i);
                }
                shouldSub = false;

            }


        }
    }
}

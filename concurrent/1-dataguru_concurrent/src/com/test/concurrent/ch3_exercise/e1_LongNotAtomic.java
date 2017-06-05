package com.test.concurrent.ch3_exercise;

/**
 * 1. 写一个程序，证明在32位hotspot上，long不是原子操作，给出程序，以及运行的截图。
 */
public class e1_LongNotAtomic {

    public static long l = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    l = 1L;
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    l = -1L;
                }
            }
        });

        t1.setDaemon(true);
        t2.setDaemon(true);

        t1.start();
        t2.start();

        Thread.sleep(1000);

        while (true) {
            long temp = l;  //防止一次if读了两个值的情况
            if(temp != -1L && temp != 1L) {
                System.out.println("break");
                System.out.println(Long.toBinaryString(temp));
                System.out.println(temp);
                System.out.println("end.");
                break;
            }
        }



    }
}

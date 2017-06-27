package com.mytest;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 8.原子类
 * 原子类能保证++ --操作具有原子性
 * Created by 37 on 2017/3/22.
 */
public class p8_Atomic {
    public static void main(String[] args) {
        final AtomicInteger data = new AtomicInteger(100);
        //final int[] data = {100};

        for(int i = 0; i < 2; i++) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0; i < 50; i++) {
                                System.out.println("inc " + data.incrementAndGet());
                                //System.out.println("inc " + ++data[0]);

                            }
                        }
                    }
            ).start();

            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0; i < 50; i++) {
                                System.out.println("dec " + data.decrementAndGet());
                                //System.out.println("dec " + --data[0]);

                            }
                        }
                    }
            ).start();
        }

    }
}

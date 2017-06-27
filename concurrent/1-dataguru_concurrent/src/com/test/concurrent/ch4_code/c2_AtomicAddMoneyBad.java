package com.test.concurrent.ch4_code;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 用户默认有9块
 * 我们希望在余额小于20块的时候，充值20块，但只会充值一次，只有一次
 * <p/>
 * 用户会消费，每次消费10元
 *
 * AtomicReference没有记录状态，会重复充值钱
 * Created by lyzzzz on 2017-05-16.
 */
public class c2_AtomicAddMoneyBad {

    static AtomicReference<Integer> money = new AtomicReference<>(19);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {   //cas的while
                            Integer m = money.get();

                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20)) {
                                    System.out.println("充值成功 " + m + "+20," + (m + 20));
                                    break;
                                }
                            } else {
                                //System.out.println("不用充值");
                                break;
                            }
                        }
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        Integer m = money.get();
                        if (m > 10) {
                            money.compareAndSet(m, m - 10);
                            System.out.println("消费成功 " + m + "-10," + (m - 10));
                            break;
                        } else {

                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}


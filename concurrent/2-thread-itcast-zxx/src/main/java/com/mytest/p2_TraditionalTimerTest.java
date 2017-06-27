package com.mytest;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 2.传统的定时器模式
 * Created by 37 on 2017/3/21.
 */
public class p2_TraditionalTimerTest {
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        //第一种用法：定时触发
        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时触发！");
            }
        }, 2000);
        */

        //第二种用法：x秒后启动，然后每y秒启动
        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("先x秒后触发，再每y秒触发！");
            }
        }, 1000, 2000);*/

        //面试题：先2秒后4秒
        //方法一：新建一个类
        /*class MyTimerTask extends TimerTask {

            @Override
            public void run() {
                count = (count + 1) % 2;
                System.out.println("count=" + count);
                new Timer().schedule(
                        new MyTimerTask(), 2000 + 2000 * count
                );
            }
        }

        new Timer().schedule(new MyTimerTask(), 2000);*/


        //面试题 方法二：新建两个类
        new Timer().schedule(new MyTimerTask2(), 2000);

        while(true) {
            Thread.sleep(1000);
            System.out.println(new Date().getSeconds());
        }
    }

    //方法二：新建两个类
    static class MyTimerTask2 extends TimerTask {

        public void run() {
            System.out.println("类二");
            new Timer().schedule(
                    new MyTimerTask1(), 4000
            );
        }
    }

    static class MyTimerTask1 extends TimerTask {

        public void run() {
            System.out.println("类一");
            new Timer().schedule(
                    new MyTimerTask2(), 2000
            );
        }
    }
}

package com.test.concurrent.ch2_code;

/**
 * interrupted:实际上就是设置一个标志位.
 *
 * 以下三个方法要配合
 * interrupt()：设置中断标志位
 * isInterrupted()：查看中断位是否已经被设置
 * InterruptedException：如果被中断了，就会抛出这个异常，并清除标志位
 * Created by lyzzzz on 2017-05-14.
 */
public class c4_TestInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(Thread.currentThread().isInterrupted()) {
                        System.out.println("will exit!");
                        break;
                    }
                    Thread.yield();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("interruped!");
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}

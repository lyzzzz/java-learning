package com.test.concurrent.ch2_code;

/**
 * Created by lyzzzz on 2017-05-14.
 */
public class c1_CreateThread3 implements Runnable {
    @Override
    public void run() {
        System.out.println("oh, i am runnable");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new c1_CreateThread3());
        thread.start();
    }
}

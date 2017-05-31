package com.test.concurrent.ch2_code;

/**
 * 演示使用stop的不可预料性
 *
 * stop:停止线程，然后释放正在阻塞的所有锁
 *
 * 容易造成锁突然释放，从而造成数据不一致的情况
 * Created by lyzzzz on 2017-05-14.
 */
public class c2_StopThreadUnsafe {
    public static User u = new User();
    public static class User {
        private int id;
        private String name;

        public User() {
            this.id = 0;
            name = "0";
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ChangeObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.id = v;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.name = Integer.toString(v);
                }
                Thread.yield();
            }

        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while(true) {
                synchronized (u) {
                    if(u.id != Integer.parseInt(u.name)) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            ChangeObjectThread thread = new ChangeObjectThread();
            thread.start();
            Thread.sleep(150);
            thread.stop();
        }
    }
}

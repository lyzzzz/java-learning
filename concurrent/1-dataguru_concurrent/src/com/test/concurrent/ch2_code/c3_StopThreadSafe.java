package com.test.concurrent.ch2_code;

/**
 * 中断线程的正确做法：设置标志位并检测
 * Created by lyzzzz on 2017-05-14.
 */
public class c3_StopThreadSafe {
    private static User u = new User();
    public static class User {
        private int id;
        private String name;

        public User() {
            this.id = 0;
            this.name = "0";
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
        private boolean stop = false;
        public void stopMe() {
            stop = true;
        }

        @Override
        public void run() {
            while (true) {
                if(stop) {
                    System.out.println("exit");
                    break;
                }
                synchronized (u) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.id = v;

                    try {
                        Thread.sleep(1000);
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
            while (true) {
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
            thread.stopMe();
        }
    }
}

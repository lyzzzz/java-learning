package com.test.concurrent.ch2_exercise;

/**
 * 1.现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
 */
public class e1_ThreeThreadSync {
    static int shouldExecute = 1;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        while(shouldExecute != 1) {
                            lock.wait();
                        }
                        System.out.println("线程1执行");
                        shouldExecute = 2;
                        lock.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        while(shouldExecute != 2) {
                            lock.wait();
                        }
                        System.out.println("线程2执行");
                        shouldExecute = 3;
                        lock.notifyAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        while(shouldExecute != 3) {
                            lock.wait();
                        }
                        System.out.println("线程3执行");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        });
        t3.start();
        t2.start();
        t1.start();
    }


}

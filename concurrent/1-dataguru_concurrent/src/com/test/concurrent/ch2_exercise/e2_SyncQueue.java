package com.test.concurrent.ch2_exercise;

import java.util.Random;

/**
 * 2.使用 wait notify 实现一个队列，队列有2个方法，add 和 get 。
 * add方法往队列中添加元素，get方法往队列中获得元素。
 * 队列必须是线程安全的。
 *
 * 如果get执行时，队列为空，线程必须阻塞等待，直到有队列有数据。
 * 如果add时，队列已经满，则add线程要等待，直到队列有空闲空间。
 *
 * 实现这么一个队列，并写一个测试代码，使他工作在多线程的环境下，证明，它的工作是正确的。给出程序和运行的截图。
 */
public class e2_SyncQueue {
    public static void main(String[] args) {
        final E2SyncQueue q = new E2SyncQueue();
        final Random random = new Random();

        for(int i = 0; i < 2; i++) {
            final int finalI = i;
            new Thread(null, new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep(random.nextInt(5000));
                            int i1 = random.nextInt(100);
                            q.put(i1);
                            System.out.println("put-" + finalI + ":" + i1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }, "put-" + i).start();
        }

        for(int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(null, new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep(random.nextInt(5000));
                            int i1 = q.get();
                            System.out.println("get-" + finalI + ":" + i1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }, "get-" + i).start();
        }
    }
}

class E2SyncQueue {
    public static final int MAX_SIZE = 10;
    int[] arr = new int[MAX_SIZE];

    int head = 0;
    int tail = 0;
    int count = 0;
    Object lock = new Object();

    public int get() {
        synchronized (lock) {
            while(count == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " empty wait...");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

    /*
                return -1;
    */
            }

            int i = arr[head--];
            count--;

            if(head == -1) {
                head = 0;
            }

            lock.notifyAll();

            return i;
        }
    }

    public void put(int i) throws InterruptedException {
        synchronized (lock) {
            while(count == MAX_SIZE) {
                System.out.println(Thread.currentThread().getName() + " full wait...");
                lock.wait();
            }

            arr[tail++] = i;
            count++;

            if(tail == MAX_SIZE) {
                tail = 0;
            }

            lock.notifyAll();
        }
    }

    public void print() {
        for(int i = 0; i < MAX_SIZE; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

    }
}

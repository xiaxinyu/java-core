package com.xiaxinyu.java.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author summer
 * @date 2020.9.5
 */
public class ReentrantLockTest {
    static int count = 0;

    public static void testLock(Lock lock) {
        try {
            lock.lock();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(String.format("--------> Thread[%s] start looping ... ", Thread.currentThread()));
            for (int i = 0; i < 10000000; i++) {
                ++count;
            }
            System.out.println();
            System.out.println();
            System.out.println();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final Lock lock = new ReentrantLock();
        final CyclicBarrier cb = new CyclicBarrier(10, () -> {
            System.out.println("total=" + count);
        });

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                testLock(lock);
                // 这段代码是非lock比较使用
//                    for (int i = 0; i < 10000000; i++)
//                        count++;
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.format("Thread" + (i + 1))).start();
        }
    }
}

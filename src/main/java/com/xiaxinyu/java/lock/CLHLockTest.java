package com.xiaxinyu.java.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author summer
 * @date 2020.9.5
 */
public class CLHLockTest {
    static int count = 0;

    public static void testLock(CLHLock lock) {
        try {
            lock.lock();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(String.format("--------> Thread[%s] start looping ... ", Thread.currentThread()));
            for (int i = 0; i < 10000000; i++) {
                ++count;
            }
            System.out.println(String.format("--------> Thread[%s] finish looping ... count=%d", Thread.currentThread(), count));
            System.out.println();
            System.out.println();
            System.out.println();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final CLHLock clh = new CLHLock();
        final CyclicBarrier cb = new CyclicBarrier(10, () -> {
            System.out.println("total=" + count);
        });

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                testLock(clh);
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.format("Thread" + (i + 1))).start();
        }
    }
}

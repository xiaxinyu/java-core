package com.xiaxinyu.java.thread;

/**
 * @author XIAXINYU3
 * @date 2020/8/18
 */
public class ThreadJoinTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
                System.out.println("Hello, Happen before");
        });

        Thread t2 = new Thread(()->{
            System.out.println("===Start===");
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===End===");
        });

        t1.start();
        t2.start();
    }
}


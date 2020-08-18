package com.xiaxinyu.java.thread;

/**
 * @author XIAXINYU3
 * @date 2020/8/18
 */
public class ThreadStartTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Hello, Happen before");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("===Start===");
            t1.start();
            System.out.println("===End===");
        });
        t2.start();
    }
}


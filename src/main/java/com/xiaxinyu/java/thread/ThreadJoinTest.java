package com.xiaxinyu.java.thread;

/**
 * @author XIAXINYU3
 * @date 2020/8/18
 */
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("===Start===");

        //Happen-before 5、All actions in a thread happen before any other thread successfully returns from a join() on that thread.
        Thread t1 = new Thread(() -> {
            int counter = 1;
            while (counter++ < 10) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello, Happen before: [" + counter + "]");
            }
        });
        t1.start();
        t1.join();

        System.out.println("===End===");
    }
}


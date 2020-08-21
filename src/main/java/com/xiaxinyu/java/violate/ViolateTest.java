package com.xiaxinyu.java.violate;

/**
 * @author XIAXINYU3
 * @date 2020/8/21
 */
public class ViolateTest {
    public static void main(String[] args) throws InterruptedException {
        Run run = new Run();
        run.start();

        Thread.sleep(1000);
        run.setStop(true);
        System.out.println("!!!Game over!!!");
    }
}

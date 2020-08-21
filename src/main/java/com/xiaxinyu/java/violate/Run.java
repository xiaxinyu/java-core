package com.xiaxinyu.java.violate;

/**
 * @author XIAXINYU3
 * @date 2020/8/21
 */
public class Run extends Thread {
    //使用volatile修饰变量，在多线程中共享变量，来控制循环是否继续执行
    private volatile boolean stop = false;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        System.out.println("I am coming");
        while (!stop) {
        }
        System.out.println("!!!I am stopped!!!");
    }
}

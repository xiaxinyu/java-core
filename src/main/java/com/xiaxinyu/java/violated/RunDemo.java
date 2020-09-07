package com.xiaxinyu.java.violated;

/**
 * @author XIAXINYU3
 * @date 2020/8/21
 */
public class RunDemo {
    private volatile boolean stop = false;

    public void setStop(boolean stop) {
        this.stop = stop;
        System.out.println("******** stop=" + stop);
    }

    public static void main(String[] args) {
        RunDemo run = new RunDemo();
        run.setStop(true);
    }
}

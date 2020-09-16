package com.xiaxinyu.java.sync;

public class SyncTest {
    private static int counter = 0;

    public synchronized void count() {
        for (int i = 0; i < 1000; i++) {
            counter++;
        }
    }

    public static void main(String[] args) {
        new SyncTest().count();
    }
}

package com.xiaxinyu.java.thread.local;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author XIAXINYU3
 * @date 2020/9/29
 */
public class Index {
    private static AtomicInteger nextHashCode = new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    // 计算哈希
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    // 获取下标
    //int i = key.threadLocalHashCode & (len-1);

    public static void main(String[] args) {
        //int hashCode = 0;
        for (int i = 0; i < 16; i++) {
            //hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            int idx = nextHashCode() & 15;
            System.out.println("斐波那契散列：" + idx + " \t普通散列：" + (String.valueOf(i).hashCode() & 15));
        }
    }
}

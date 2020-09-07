package com.xiaxinyu.java.cacheline;

/**
 * 进入到JAVA8后，官方已经提供了对伪共享的解决办法，那就是sun.misc.Contended注解，有了这个注解解决伪共享就变得简单多了<br/>
 * 默认情况下此注解是无效的，需要在JVM启动时设置-XX:-RestrictContended
 *
 * @author XIAXINYU3
 * @date 2020/8/27
 */
public class Cacheline_fake_padding {
    @sun.misc.Contended
    public static class T {
        //8字节
        private volatile long x = 0L;
    }

    private static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                //volatile的缓存一致性协议MESI或者锁总线，会消耗时间
                arr[0].x = i;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });
        long startTime = System.nanoTime();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("总计消耗时间：" + (System.nanoTime() - startTime) / 100_000);
    }
}
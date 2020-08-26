package com.xiaxinyu.java.thread.pool;

import org.omg.CORBA.Current;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author XIAXINYU3
 * @date 2020/8/26
 */
public class RejectPolicy {
    public static void main(String[] args) {
        RejectedExecutionHandler policy = new ThreadPoolExecutor.CallerRunsPolicy();
        //RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
        //RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardOldestPolicy();
        //RejectedExecutionHandler policy = new ThreadPoolExecutor.AbortPolicy();

        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.HOURS,
                new ArrayBlockingQueue<Runnable>(5), Executors.defaultThreadFactory(), policy);

        for (int i = 0; i < 10; i++) {
            int index = i;
            executorService.submit(() -> {
                System.out.println(Thread.currentThread() + " say: I am ok [" + index + "]");
            });
        }
    }
}

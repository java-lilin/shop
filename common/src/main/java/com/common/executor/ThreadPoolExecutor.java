package com.common.executor;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/13  17:16
 */
public class ThreadPoolExecutor {

    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
    }


    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
    }


    /**
     * 创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待。
     */
    public static void fixedThreadPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        // 执行任务
        threadPool.execute(() -> {
            System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
        });
    }

    /**
     * 创建一个可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程。
     */
    public static void cachedThreadPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
            });
        }
    }

    /**
     * 创建单个线程数的线程池，它可以保证先进先出的执行顺序。
     */
    public static void singleThreadExecutor() {
        // 创建线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + ":任务被执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {

                }
            });
        }
    }

    /**
     * 创建一个可以执行延迟任务的线程池。
     */
    public static void scheduledThreadPool() {
        // 创建线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        // 添加定时执行任务(1s 后执行)
        System.out.println("添加任务,时间:" + new Date());
        threadPool.schedule(() -> {
            System.out.println("任务被执行,时间:" + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {

            }
        }, 1, TimeUnit.SECONDS);
    }

    /**
     * 创建一个单线程的可以执行延迟任务的线程池。
     */
    public static void SingleThreadScheduledExecutor() {
        // 创建线程池
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
        // 添加定时执行任务(2s 后执行)
        System.out.println("添加任务,时间:" + new Date());
        threadPool.schedule(() -> {
            System.out.println("任务被执行,时间:" + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }, 2, TimeUnit.SECONDS);
    }

    /**
     * 创建一个抢占式执行的线程池（任务执行顺序不确定），注意此方法只有在 JDK 1.8+ 版本中才能使用。
     */
    public static void workStealingPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newWorkStealingPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行,线程名:" + Thread.currentThread().getName());
            });
        }
        // 确保任务执行完成
        while (!threadPool.isTerminated()) {
            System.err.println();
        }
    }

//    /**
//     * 最原始的创建线程池的方式，它包含了 7 个参数可供设置。
//     */
//    public static void myThreadPoolExecutor() {
//        // 创建线程池
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
//        // 执行任务
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            threadPool.execute(() -> {
//                System.out.println(index + " 被执行,线程名:" + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//    }





}

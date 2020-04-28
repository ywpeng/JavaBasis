package com.pyw.threadpool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 创建只有一条线程的线程池，他可以在指定延迟后执行线程任务 
 */
public class P5SingleThreadScheduledExecutor {

	private static long start = new Date().getTime();
	 
    private static final ScheduledExecutorService excutor = Executors.newSingleThreadScheduledExecutor();
 
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            long end = new Date().getTime();
            System.out.println("time wait:" + (end - start) + ",this is 线程1");
        }, "线程1");
 
        Thread thread2 = new Thread(() -> {
            long end = new Date().getTime();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("time wait:" + (end - start) + ",this is 线程2");
        }, "线程2");
 
        Thread thread3 = new Thread(() -> {
            long end = new Date().getTime();
            System.out.println("time wait:" + (end - start) + ",this is 线程3");
        }, "线程3");
        
        //线程池executor调用scheduleWithFixedDelay方法，同时放入三个不同调度的线程。从结果中可以看出每个线程按照自己的调度互不干扰的运行。此时修改线程2加一个阻塞再看看运行结果。
        //Executors.newSingleThreadScheduledExecutor()来创建线程池同时放入多个线程时，每个线程都会按照自己的调度来执行，但是当其中一个线程被阻塞时，其它的线程都会受到影响被阻塞，不过依然都会按照自身调度来执行，但是会存在阻塞延迟。
        excutor.scheduleWithFixedDelay(thread1, 0, 1, TimeUnit.SECONDS);
        excutor.scheduleWithFixedDelay(thread2, 0, 2, TimeUnit.SECONDS);
        excutor.scheduleWithFixedDelay(thread3, 0, 3, TimeUnit.SECONDS);
    }

}

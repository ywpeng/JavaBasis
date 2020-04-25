package com.pyw.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 */
public class P2FixedThreadPool {

	public static void main(String[] args) {
		//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		//定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
		System.out.println("定长线程池的大小最好根据系统资源进行设置:" + Runtime.getRuntime().availableProcessors());
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 30; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                try {
                    System.out.println("线程" + index + "开始执行");
                    System.out.println("线程" + index + "睡2s");
                    Thread.sleep(2000);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
	}
}

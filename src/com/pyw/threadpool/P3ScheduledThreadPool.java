package com.pyw.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 */
public class P3ScheduledThreadPool {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
       //表示延迟3秒执行。
		scheduledThreadPool.schedule(() -> System.out.println("delay 3 seconds"), 3, TimeUnit.SECONDS);
		
		//表示延迟1秒后每3秒执行一次。
		//ScheduledExecutorService比Timer更安全，功能更强大。
        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("delay 1 seconds, and excute every 3 seconds"), 1, 3, TimeUnit.SECONDS);

	}
}

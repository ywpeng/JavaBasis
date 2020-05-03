package com.pyw.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  线程池：
 *  	注意 《阿里开发手册》不允许使用 Excecutors 创建线程池，以防内存刷爆，建议自定义线程池，可掌控细节
 * @author ASUS
 * 
 * 要点：
 * 	1.线程池创建的 7大参数的含义
 *  2.4种拒绝策略
 *  	
 *  3.参数的推荐设置：
 *
 */

public class CustomThreadDemo {
	public static void main(String[] args) {
		// 关于 maximumPoolSize 参数的设置有： 按当前电脑的 CPU数量和按 IO 判定两种情况
		// 情况一：按 服务器 CPU 核数  ，   maximumPoolSize= CPU核数 + 1
		System.out.println("当前 CPU 核数： " + Runtime.getRuntime().availableProcessors());
		
		
		// 自定义线程池
		ExecutorService threadPool =  new ThreadPoolExecutor(
				2, 				//corePoolSize:核心线程数据
				3,				//maximumPoolSize：最大线程数。
				2, 				//keepAliveTime:线程多长时间不用后回收
				TimeUnit.SECONDS, 	//unit：线程未使用时长的时间单位
				new LinkedBlockingDeque<>(3), 			//workQueue:阻塞队列(等候区)
				Executors.defaultThreadFactory(), 		//threadFactory:使用默认的线程池工厂
				new ThreadPoolExecutor.DiscardPolicy()); 	//handler:共有 4种拒绝策略
		/*
		 * AbortPolicy(默认)：直接抛出 RejectedException异常阻止系统正常运行
		 * 	超过了最大容纳数就报异常
		 * CallerRunsPolicy:“调用者运行”一种调节机制，该策略既不会跑起任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
		 * 	线程池会尽可能的执行，执行不了的会退给调用的线程去执行
		 * DiscardOldestPolicy:抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
		 * 	等待最久的任务先抛弃，然后线程池能执行多少就执行多少
		 * DiscardPolicy:该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。如果允许任务丢失，这是最好的一种策略。
		 * 	超过了最大容纳数，多余的就不做处理也不会报异常
		 */	
		//线程池的最大容纳数 =  maxPoolsize + workQueue
		
		
		for (int i = 1,j = 10;i <= j;i++) {
			final int tempi = i;
			threadPool.execute( () ->  {				
				System.out.println("当前线程" + Thread.currentThread().getName() + "正在为  " + tempi + " 办理业务");
			}); 
		}
		
	}

}

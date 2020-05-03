package com.pyw.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *  线程池：
 *  	注意 《阿里开发手册》不允许使用 Excecutors 创建线程池，以防内存刷爆，建议自定义线程池，可掌控细节
 * @author ASUS
 *
 */
public class MyThreadPoolDemo {

	public static void main(String[] args) {
		//线程池常用三种： 固定数目的、只有一个的、可扩容的
		//固定线程池
//		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		//单个线程
//		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		//可扩容的线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();
		// 模拟 10 个客户来银行办理业务，银行有 5个窗口
		try {
			for (int i = 1,j = 10;i <= j ; i++) {
//				try {
//					TimeUnit.MILLISECONDS.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				final int tempI = i;
				threadPool.execute( () -> {
					System.out.println(Thread.currentThread().getName() + "号窗口为 "+ tempI +"号客户办理业务");
				
//					try {
//						TimeUnit.MILLISECONDS.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				});
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
		
		
	}
}

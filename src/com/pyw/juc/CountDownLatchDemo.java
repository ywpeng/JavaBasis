package com.pyw.juc;

import java.util.concurrent.CountDownLatch;

/**
 * JUC 提供强大的的辅助类
 * 	CountDownLatch : 减少计数
 * @author ASUS
 *
 */
public class CountDownLatchDemo {

	/*
	 * 要求：教师里有5 个人，等其他人走后班长锁门
	 */
	public static void main(String[] args) {
		
		//1.错误的演示案例
//		for (int i = 0,j = 5; i < j ; i++) {
//			new Thread(() -> {
//				System.out.println(Thread.currentThread().getName() + " 同学离开了教室");
//			},String.valueOf(i)).start();
//		}
//		
//		System.out.println("班长锁门了");
		
		//2.使用 CountDownLatch的 示例
		CountDownLatch cdl = new CountDownLatch(5);
		for (int i = 0,j = 5; i < j ; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " 同学离开了教室");
				cdl.countDown();
			},String.valueOf(i)).start();
		}
		
		try {
			// 倒计数没玩就等，一直到执行完为止
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("班长锁门了");
	}
}

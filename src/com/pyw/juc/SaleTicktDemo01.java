package com.pyw.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class Ticket{
	private int number = 30;
	
	Lock lock = new ReentrantLock();
	public void sale() {
		lock.lock();
		try {
			if (number > 0) {
				System.out.println(Thread.currentThread().getName() + "\t 卖出第" + (number--)+"张票，还剩"+ number +"张票");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
}

public class SaleTicktDemo01 {

	/**
	 * 要求：三个售票员 	卖出 	30张票
	 * 使用多线程实现
	 * 多线程的实现讨论、模板
	 * 
	 * 1.在高内聚低耦合的前提下， 线程	操作	资源类
	 * 	1.1 先创建资源类
	 *  
	 * @param args
	 */
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		//方式三：使用 Lambada方式来简写
		new Thread(()->{for (int i = 1;i <= 30;i++) ticket.sale();},"A").start();
		new Thread(()->{for (int i = 1;i <= 30;i++) ticket.sale();},"B").start();
		new Thread(()->{for (int i = 1;i <= 30;i++) ticket.sale();},"C").start();
		
		//方式二：使用匿名内部类的方式实现
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				for (int i = 1;i <= 30;i++) {
//					ticket.sale();
//				}
//			}
//		},"A售票员").start();
//		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				for (int i = 1;i <= 30;i++) {
//					ticket.sale();
//				}
//			}
//		},"B售票员").start();
//		
//		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				for (int i = 1;i <= 30;i++) {
//					ticket.sale();
//				}
//			}
//		},"C售票员").start();
		
		//方式一：常规方式下新建对象，让对象实现Runnable接口，代码细节略过
//		Thread t1 = new Thread();
//		Thread t2 = new Thread();
//		Thread t3 = new Thread();
		
		
	}
	
}

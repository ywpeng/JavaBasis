package com.pyw.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式的示例
 * @author ASUS
 *要求：两个线程，可以操作初始值为0的一个变量
 *一个线程对其加，一个线程减
 *交替实现，共10轮
 *	1	高内聚低耦合，线程操作资源类
 *	2	判断/干活/通知
 *	3	防止虚假唤醒
 */

class Bun{
	private int num = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws Exception {
		lock.lock();
		try {
			while (num != 0) {
				System.out.println(Thread.currentThread().getName()+"发现有包子，先休息");
				condition.await(); //使用wait方法时，需要用 while作为判断条件，防止虚假唤醒
			}
			//2.干活
			num++;//制造包子
			System.out.println(Thread.currentThread().getName()+"制造了一个包子" + num);
			//3.通知
		}catch(Exception e) {
			
		}finally {
			//lock.unlock();
			condition.signalAll();
		}
			
	}
	
	public  void decrement() throws Exception {
		lock.lock();
		try {
			//1.判断
			if (num == 0) {
				//this.wait();
				condition.await();
			}
			//2.干活
			num--;//制造包子
			System.out.println(Thread.currentThread().getName()+"消费了一个包子" + num);
			//3.通知
			//this.notifyAll();
			condition.signalAll();
			
		}catch(Exception e) {
			
		}finally {
			lock.unlock();
		}
}
	
	/*
	//制造包子
	public synchronized void increment() throws Exception {
		System.out.println(Thread.currentThread().getName()+"进包子房了");
		//1.判断
		while (num != 0) {
//		if (num != 0) {
			System.out.println(Thread.currentThread().getName()+"发现有包子，先休息");
			this.wait();//使用wait方法时，需要用 while作为判断条件，防止虚假唤醒
		}
		//2.干活
		num++;//制造包子
		System.out.println(Thread.currentThread().getName()+"制造了一个包子" + num);
		//3.通知
		this.notifyAll();
	}
	
	//消费包子
	public synchronized void decrement() throws Exception {
			//1.判断
			while (num == 0) {
				this.wait();
			}
			//2.干活
			num--;//制造包子
			System.out.println(Thread.currentThread().getName()+"消费了一个包子" + num);
			//3.通知
			this.notifyAll();
	}
	*/
}
public class ProdConsumerDemo {

	public static void main(String[] args) {
		Bun b = new Bun();
		
		//制造的线程
		new Thread(() -> {
			for (int i = 0; i <= 10;i++) {
				try {
					Thread.sleep(300);
					b.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"A").start();
		
		//消费的线程
		new Thread(() -> {
			try {
				for (int i = 0; i <= 10;i++) {
					Thread.sleep(400);
					b.decrement();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"B").start();
		
		
		//再加一个  制造的线程
		new Thread(() -> {
			for (int i = 0; i <= 10;i++) {
				try {
					Thread.sleep(500);
					b.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"C").start();
	}
}

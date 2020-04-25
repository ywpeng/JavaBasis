package com.pyw.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDate{
	private int flag = 1;
	Lock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	Condition c3 = lock.newCondition();
	
	public void printByParm(int p) {
		lock.lock();
		try {
			
			
			
			if (p == 5) {
				//1.判断
				while(flag != 1) {
					c1.await();
				}
				//2.干活
				flag = 2;
			} else if (p == 10) {
				//1.判断
				while(flag != 2) {
					c2.await();
				}
				//2.干活
				flag = 3;
			} else if (p == 15) {
				//1.判断
				while(flag != 3) {
					c3.await();
				}
				//2.干活
				flag = 1;
			}
			
			for (int i = 1 ; i <= p ; i++) {
				System.out.println(Thread.currentThread().getName() + "--" + i);
			}
			
			//3.通知：唤醒下一个线程
			if ((flag-1) == 1) {
				c2.signal();
			} else if ((flag-1) == 2) {
				c3.signal();
			} else if ((flag-1) == 0) {
				c1.signal();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
}
/**
 * 多线程间的顺序调用，实现A->B->C
 * @author ASUS
 * 三个线程启动后，要求如下：
 * AA打印5次，BB打印10次，CC打印10次
 * 以上操作重复10轮
 *
 */
public class ConditionDemo {

	public static void main(String[] args) {
		ShareDate s = new ShareDate();
		new Thread(() -> {
			for (int i = 0,j = 10;i < j;i++) {
				s.printByParm(5);
			}
		},"A").start();
		
		new Thread(() -> {
			for (int i = 0,j = 10;i < j;i++) {
				s.printByParm(10);
			}
		},"B").start();
		
		new Thread(() -> {
			for (int i = 0,j = 10;i < j;i++) {
				s.printByParm(15);
			}
		},"C").start();
	}
	
}

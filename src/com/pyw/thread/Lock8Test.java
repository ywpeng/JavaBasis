package com.pyw.thread;

import java.util.concurrent.TimeUnit;

/**
 * 8种状态下的锁的相关用例
 * @author ASUS
 *1.标准执行： E 和 M 随机执行
 *2.E先暂停4s:
 *3.新增普通的方法：
 *4.两个手机对象：
 *5.两个静态同步方法打印一个手机：
 *6.两个静态同步方法打印2个手机：
 *7.一个静态同步方法，一个普通同步方法，打印同一个手机：
 *8.一个静态同步方法，一个普通同步方法，打印同2个手机：
 *
 *解析：
 *1,2：一个对象如果有多个synchronized方法，某一个时刻内线程去调用其中的一个synchronized方法了，
 *	   其他线程都只能等待。也就是在某一个时刻内，只能有一个线程可以去访问这些synchronized方法.
 *	 对象锁是对当前对象this，被锁定后，其他线程不能进入当当前对象的其他synchronized方法。
 *
 *3：普通方法跟同步锁无关
 *
 *4. 不同的对象不存在对象同步锁竞争的情况
 *
 *5，6：static方法归类所有，static 修饰的同步锁是类的全局锁。
 *		对于普通同步方法，锁的是当前对象的实例；
 *		对于静态同步方法，锁的是当前类的Class对象
 *7，8：一个是类的锁，一个是对象的锁，完全不冲突，各自按顺序执行
 *
 *
 */
class Phone{
	public static synchronized void sendEmail() throws Exception {
		TimeUnit.SECONDS.sleep(3);
		System.out.println("---- send email");
	}
	
	public  synchronized void sendMsg() throws Exception{
		System.out.println("---- send msg");
	}
	
	public  void sayHello() throws Exception{
		System.out.println("---- say hello");
	}
}

public class Lock8Test {

	public static void main(String[] args) {
		Phone phone = new Phone();
		Phone phone2 = new Phone();
		new Thread(()->{
			try {
				phone.sendEmail();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"A").start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(()->{
			try {
//				phone2.sendMsg();
//				phone2.sayHello();
				phone.sendMsg();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"B").start();
		
		
	}
}

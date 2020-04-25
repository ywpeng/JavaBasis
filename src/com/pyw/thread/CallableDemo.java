package com.pyw.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + "  do something...");
	}
}

class MyThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + "  do something...");
		return 1024;
	}
	
}

/**
 * 使用Callable 接口实现多线程
 * @author ASUS
 *
 */
public class CallableDemo {

	public static void main(String[] args) {
//		//1.JDK8 以前的实现方式
//		//使用实现 Runnable 接口的方式实现
//		MyThread1 myThread = new MyThread1();
//		Thread thread1 = new Thread(myThread,"A");
//		Thread thread2 = new Thread(myThread,"B");
//		thread1.start();
//		thread2.start();
//		
//		//2.使用 Lambada表达式的方式实现
//		new Thread(()->{
//			System.out.println(Thread.currentThread().getName() + "  do something...");
//		},"A").start();
//		
//		new Thread(()->{
//			System.out.println(Thread.currentThread().getName() + "  do something...");
//		},"B").start();
		
		//3.使用 Callable的实现方式
		// 该方式下可以获取到线程执行的返回值，是否执行完成等信息
		
		FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new MyThread());
		FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyThread());
		FutureTask<Integer> futureTask3 = new FutureTask<Integer>(new MyThread());
		
		new Thread(futureTask1,"A").start();
		new Thread(futureTask2,"B").start();
		new Thread(futureTask3,"C").start();
		
		try {
			
			Integer r1 = futureTask1.get();
			System.out.println("调用之后A的返回结果：" + r1 );
			Integer r2 = futureTask2.get();
			System.out.println("调用之后B的返回结果：" + r2 );
			Integer r3 = futureTask3.get();
			System.out.println("调用之后C的返回结果：" + r3 );
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

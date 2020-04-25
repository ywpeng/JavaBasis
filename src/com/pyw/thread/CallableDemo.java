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
 * ʹ��Callable �ӿ�ʵ�ֶ��߳�
 * @author ASUS
 *
 */
public class CallableDemo {

	public static void main(String[] args) {
//		//1.JDK8 ��ǰ��ʵ�ַ�ʽ
//		//ʹ��ʵ�� Runnable �ӿڵķ�ʽʵ��
//		MyThread1 myThread = new MyThread1();
//		Thread thread1 = new Thread(myThread,"A");
//		Thread thread2 = new Thread(myThread,"B");
//		thread1.start();
//		thread2.start();
//		
//		//2.ʹ�� Lambada���ʽ�ķ�ʽʵ��
//		new Thread(()->{
//			System.out.println(Thread.currentThread().getName() + "  do something...");
//		},"A").start();
//		
//		new Thread(()->{
//			System.out.println(Thread.currentThread().getName() + "  do something...");
//		},"B").start();
		
		//3.ʹ�� Callable��ʵ�ַ�ʽ
		// �÷�ʽ�¿��Ի�ȡ���߳�ִ�еķ���ֵ���Ƿ�ִ����ɵ���Ϣ
		
		FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new MyThread());
		FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyThread());
		FutureTask<Integer> futureTask3 = new FutureTask<Integer>(new MyThread());
		
		new Thread(futureTask1,"A").start();
		new Thread(futureTask2,"B").start();
		new Thread(futureTask3,"C").start();
		
		try {
			
			Integer r1 = futureTask1.get();
			System.out.println("����֮��A�ķ��ؽ����" + r1 );
			Integer r2 = futureTask2.get();
			System.out.println("����֮��B�ķ��ؽ����" + r2 );
			Integer r3 = futureTask3.get();
			System.out.println("����֮��C�ķ��ؽ����" + r3 );
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

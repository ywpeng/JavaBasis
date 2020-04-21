package com.pyw.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//��Դ��
class Ticket{
	private int number = 30;
	
	Lock lock = new ReentrantLock();
	public void sale() {
		lock.lock();
		try {
			if (number > 0) {
				System.out.println(Thread.currentThread().getName() + "\t ������" + (number--)+"��Ʊ����ʣ"+ number +"��Ʊ");
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
	 * Ҫ��������ƱԱ 	���� 	30��Ʊ
	 * ʹ�ö��߳�ʵ��
	 * ���̵߳�ʵ�����ۡ�ģ��
	 * 
	 * 1.�ڸ��ھ۵���ϵ�ǰ���£� �߳�	����	��Դ��
	 * 	1.1 �ȴ�����Դ��
	 *  
	 * @param args
	 */
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		//��ʽ����ʹ�� Lambada��ʽ����д
		new Thread(()->{for (int i = 1;i <= 30;i++) ticket.sale();},"A").start();
		new Thread(()->{for (int i = 1;i <= 30;i++) ticket.sale();},"B").start();
		new Thread(()->{for (int i = 1;i <= 30;i++) ticket.sale();},"C").start();
		
		//��ʽ����ʹ�������ڲ���ķ�ʽʵ��
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				for (int i = 1;i <= 30;i++) {
//					ticket.sale();
//				}
//			}
//		},"A��ƱԱ").start();
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
//		},"B��ƱԱ").start();
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
//		},"C��ƱԱ").start();
		
		//��ʽһ�����淽ʽ���½������ö���ʵ��Runnable�ӿڣ�����ϸ���Թ�
//		Thread t1 = new Thread();
//		Thread t2 = new Thread();
//		Thread t3 = new Thread();
		
		
	}
	
}

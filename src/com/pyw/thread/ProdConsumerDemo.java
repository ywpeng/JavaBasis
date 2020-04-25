package com.pyw.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ������������ģʽ��ʾ��
 * @author ASUS
 *Ҫ�������̣߳����Բ�����ʼֵΪ0��һ������
 *һ���̶߳���ӣ�һ���̼߳�
 *����ʵ�֣���10��
 *	1	���ھ۵���ϣ��̲߳�����Դ��
 *	2	�ж�/�ɻ�/֪ͨ
 *	3	��ֹ��ٻ���
 */

class Bun{
	private int num = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws Exception {
		lock.lock();
		try {
			while (num != 0) {
				System.out.println(Thread.currentThread().getName()+"�����а��ӣ�����Ϣ");
				condition.await(); //ʹ��wait����ʱ����Ҫ�� while��Ϊ�ж���������ֹ��ٻ���
			}
			//2.�ɻ�
			num++;//�������
			System.out.println(Thread.currentThread().getName()+"������һ������" + num);
			//3.֪ͨ
		}catch(Exception e) {
			
		}finally {
			//lock.unlock();
			condition.signalAll();
		}
			
	}
	
	public  void decrement() throws Exception {
		lock.lock();
		try {
			//1.�ж�
			if (num == 0) {
				//this.wait();
				condition.await();
			}
			//2.�ɻ�
			num--;//�������
			System.out.println(Thread.currentThread().getName()+"������һ������" + num);
			//3.֪ͨ
			//this.notifyAll();
			condition.signalAll();
			
		}catch(Exception e) {
			
		}finally {
			lock.unlock();
		}
}
	
	/*
	//�������
	public synchronized void increment() throws Exception {
		System.out.println(Thread.currentThread().getName()+"�����ӷ���");
		//1.�ж�
		while (num != 0) {
//		if (num != 0) {
			System.out.println(Thread.currentThread().getName()+"�����а��ӣ�����Ϣ");
			this.wait();//ʹ��wait����ʱ����Ҫ�� while��Ϊ�ж���������ֹ��ٻ���
		}
		//2.�ɻ�
		num++;//�������
		System.out.println(Thread.currentThread().getName()+"������һ������" + num);
		//3.֪ͨ
		this.notifyAll();
	}
	
	//���Ѱ���
	public synchronized void decrement() throws Exception {
			//1.�ж�
			while (num == 0) {
				this.wait();
			}
			//2.�ɻ�
			num--;//�������
			System.out.println(Thread.currentThread().getName()+"������һ������" + num);
			//3.֪ͨ
			this.notifyAll();
	}
	*/
}
public class ProdConsumerDemo {

	public static void main(String[] args) {
		Bun b = new Bun();
		
		//������߳�
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
		
		//���ѵ��߳�
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
		
		
		//�ټ�һ��  ������߳�
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

package com.pyw.juc;

class MyThread{
	//测试一
//	int num = 10;
	//测试二
	volatile int num = 10;
	
	public void addTo100(){
		this.num = 100;
	}
}

/**
 * voiatile 是Java虚拟机提供的轻量级同步机制
 * 	1.保证可见性（通知机制）
 *  2.不保证原子性
 *  3.禁止指令重排
 *  
 * @author ASUS
 *
 *JMM： Java内存模型
 *特点：可见性、原子性、有序性
 */
public class VolatileTest {

	public static void main(String[] args) {
		MyThread m = new MyThread();
		//测试一：创建一个线程，去修改number的函数，看 main线程中是否会有通知
		//测试二：将 
		new Thread( () -> {
			System.out.println("A is coming,and A will sleep 3s first");
			try {
				Thread.sleep(3000);
				m.addTo100();
				System.out.println(Thread.currentThread().getName() + "将值该到了100");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		},"A").start();
		
		while (m.num == 10) {
			
		}
		System.out.println("num已经被改了，main方法结束");
		
	}
}

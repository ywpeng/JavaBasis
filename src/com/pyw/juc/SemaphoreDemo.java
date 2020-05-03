package com.pyw.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * JUC 提供强大的的辅助类
 * 	Semaphore : 信号等。用于控制并发量，或者说是多线程多资源的场景
 * @author ASUS
 *
 */
public class SemaphoreDemo {

	public static void main(String[] args) {
		//10 个车，5个车位。先到先抢
		Semaphore s = new Semaphore(5);
		
		for (int i = 0, j = 10 ;i < j;i++) {
			new Thread( () -> {
				try {
					s.acquire();//1.先抢占资源
					//2.执行操作
					System.out.println(Thread.currentThread().getName() + "抢到了一个车位" );
					TimeUnit.SECONDS.sleep(3);//车会停3 s
					System.out.println(Thread.currentThread().getName() + "离开了车位" );
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//3.释放资源
					s.release();
				}
				
			},String.valueOf(i)).start();
		}
	}
}

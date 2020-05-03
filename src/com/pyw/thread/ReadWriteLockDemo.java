package com.pyw.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//构建一个自定义的缓存类
class MyCache{
	private volatile Map<String,Object> map = new  HashMap<String,Object>();
	private ReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public void put (String key ,Object value) {
		//对写入的部分加写锁
		rwLock.writeLock().lock();
		try {
			
			System.out.println(Thread.currentThread().getName() + "线程准备写入： " + key);
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "完成写入 " + key);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public void get (String key) {
		//对写入的部分加写锁
		rwLock.readLock().lock();
		try {
			
			System.out.println(Thread.currentThread().getName() + "线程准备读取： " + key);
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object val = map.get(key);
			System.out.println(Thread.currentThread().getName() + "完成读取 " + val);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			rwLock.readLock().unlock();
		}

	}
}
/**
 * 多个线程同时读一个资源类没有问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是，如果有一个线程想去写资源，就不应该有其他线程可以对该资源进行读或写
 * 总结：
 * 	读-读能共存
 * 	读-写不能共存
 * 	写-写不能共存
 * @author ASUS
 *
 */
public class ReadWriteLockDemo {

	public static void main(String[] args) {
		MyCache cache = new MyCache();
		for (int i = 1,j = 5;i <= j ; i++) {
			final int tempi = i;
			new Thread( () -> {
				cache.put(tempi + "", "" + tempi);
			},String.valueOf(i)).start();
		}
		
		for (int i = 1,j = 5;i <= j ; i++) {
			final int tempi = i;
			new Thread( () -> {
				cache.get(tempi + "");
			},String.valueOf(i)).start();
		}
	}
}

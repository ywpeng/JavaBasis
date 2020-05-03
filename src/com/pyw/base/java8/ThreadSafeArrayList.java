package com.pyw.base.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadSafeArrayList {

	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		list.add("a");
//		list.add("a");
//		list.add("a");
//		// 注意Java中的这种   :: 的写法
//		list.forEach(System.out::println);
		
		//ArrayList线程不安全的示例
			//会发现在线程里写了之后读的情况，每次打印都不一样
//		List<String> list = new ArrayList<String>();
//		for (int i = 0; i <= 3;i++) {
//			new Thread(()->{
//				list.add(UUID.randomUUID().toString().substring(0,8));
//				System.out.println(list);
//			},String.valueOf(i)).start(); 
//		}
		
//		//当大量并发的情况下的时候会报 并发的异常： java.util.ConcurrentModificationException
//		List<String> list = new ArrayList<String>();
//		for (int i = 0; i <= 30;i++) {
//			new Thread(()->{
//				list.add(UUID.randomUUID().toString().substring(0,8));
//				System.out.println(list);
//			},String.valueOf(i)).start(); 
//		}
		
		//解决方案一：使用Vector。Vector是线程安全的，当性能低一些；ArrayList是线程不安全的，性能高一些
			//缺点是 Vector 把整个 add方法都加锁了
//		List<String> list = new Vector<String>();
//		for (int i = 0; i <= 30;i++) {
//			new Thread(()->{
//				list.add(UUID.randomUUID().toString().substring(0,8));
//				System.out.println(list);
//			},String.valueOf(i)).start(); 
//		}
		
		//解决方案二：Collections.synchronizedList
//		List<String> list = Collections.synchronizedList(new ArrayList<>());
//		for (int i = 0; i <= 30;i++) {
//			new Thread(()->{
//				list.add(UUID.randomUUID().toString().substring(0,8));
//				System.out.println(list);
//			},String.valueOf(i)).start(); 
//		}
		
		//解决方案三：写时复制技术 
		List<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 0; i <= 30;i++) {
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			},String.valueOf(i)).start(); 
		}
		
		//HashSet的线程安全处理方法
		Set<String> set = new CopyOnWriteArraySet<String>();
		for (int i = 0; i <= 30;i++) {
			new Thread(()->{
				set.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(set);
			},String.valueOf(i)).start(); 
		}
		
		//HashMap的线程安全处理方案
		Map<String,String> map = new ConcurrentHashMap();
		for (int i = 0; i <= 30;i++) {
			new Thread(()->{
				map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
				System.out.println(map);
			},String.valueOf(i)).start(); 
		}
	}
}

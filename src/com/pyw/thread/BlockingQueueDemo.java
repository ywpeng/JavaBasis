package com.pyw.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列的演示
 * @author ASUS
 *	要点：
 *	方法类型	抛出异常		特殊值			阻塞		超时
 * 	插入		add(e)		offer(e)	put(e)	offer(e.time.unit)
 * 	移除		remove()	poll()		take()	poll(e.time.unit)
 * 	检查		element()	peek()		不可用		不可用
 * 
 * 	特点：
 * 		当队列是空的时，从队列获取元素的操作将会被阻塞；
 *   	当队列是满的，向队列添加元素的操作会被阻塞
 */
public class BlockingQueueDemo {

	public static void main(String[] args) {
		//构建一个大小为3的阻塞类型的队列
		BlockingQueue<String> blockingQuery  =  new ArrayBlockingQueue<>(3);
		
//		System.out.println(blockingQuery.add("a"));
//		System.out.println(blockingQuery.add("b"));
//		System.out.println(blockingQuery.add("c"));
//		System.out.println(blockingQuery.add("x"));
//		
//		System.out.println(blockingQuery.remove());
//		System.out.println(blockingQuery.remove());
//		System.out.println(blockingQuery.remove());
//		System.out.println(blockingQuery.remove());
		
//		System.out.println(blockingQuery.offer("a"));
//		System.out.println(blockingQuery.offer("b"));
//		System.out.println(blockingQuery.offer("c"));
//		System.out.println(blockingQuery.offer("x"));
		
//		System.out.println(blockingQuery.poll());
//		System.out.println(blockingQuery.poll());
//		System.out.println(blockingQuery.poll());
//		System.out.println(blockingQuery.poll());
		
		//过时不候
		System.out.println(blockingQuery.offer("a"));
		System.out.println(blockingQuery.offer("b"));
		System.out.println(blockingQuery.offer("c"));
		try {
			//3S  之后如果还插入不进去就不等了
			System.out.println(blockingQuery.offer("x",3,TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

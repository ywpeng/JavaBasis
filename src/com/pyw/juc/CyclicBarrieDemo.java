package com.pyw.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * JUC 提供强大的的辅助类
 * 	CyclicBarrie : 增加计数，达到指定值后执行相应方法
 * @author ASUS
 *
 */
public class CyclicBarrieDemo {

	public static void main(String[] args) {
		//要求：集齐7颗龙珠后，召唤神龙
		
		CyclicBarrier cb = new CyclicBarrier(7, () ->  {
			System.out.println("7颗龙珠已经集齐，开始召唤神龙");
		});
		
		for (int i = 0,j = 7; i < j;i++) {
			final int tempi = i;
			new Thread( () -> {
				System.out.println("收集到了第"+ tempi +"颗龙珠");
				try {
					cb.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
		
	}
}

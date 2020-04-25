package com.pyw.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * newScheduledThreadPool ����һ�������̳߳أ�֧�ֶ�ʱ������������ִ�С�
 */
public class P3ScheduledThreadPool {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
       //��ʾ�ӳ�3��ִ�С�
		scheduledThreadPool.schedule(() -> System.out.println("delay 3 seconds"), 3, TimeUnit.SECONDS);
		
		//��ʾ�ӳ�1���ÿ3��ִ��һ�Ρ�
		//ScheduledExecutorService��Timer����ȫ�����ܸ�ǿ��
        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("delay 1 seconds, and excute every 3 seconds"), 1, 3, TimeUnit.SECONDS);

	}
}

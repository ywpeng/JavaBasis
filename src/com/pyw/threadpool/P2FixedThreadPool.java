package com.pyw.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * newFixedThreadPool ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
 */
public class P2FixedThreadPool {

	public static void main(String[] args) {
		//����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
		//�����̳߳صĴ�С��ø���ϵͳ��Դ�������á���Runtime.getRuntime().availableProcessors()
		System.out.println("�����̳߳صĴ�С��ø���ϵͳ��Դ��������:" + Runtime.getRuntime().availableProcessors());
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 30; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                try {
                    System.out.println("�߳�" + index + "��ʼִ��");
                    System.out.println("�߳�" + index + "˯2s");
                    Thread.sleep(2000);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
	}
}

package com.pyw.threadpool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * ����ֻ��һ���̵߳��̳߳أ���������ָ���ӳٺ�ִ���߳����� 
 */
public class P5SingleThreadScheduledExecutor {

	private static long start = new Date().getTime();
	 
    private static final ScheduledExecutorService excutor = Executors.newSingleThreadScheduledExecutor();
 
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            long end = new Date().getTime();
            System.out.println("time wait:" + (end - start) + ",this is �߳�1");
        }, "�߳�1");
 
        Thread thread2 = new Thread(() -> {
            long end = new Date().getTime();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("time wait:" + (end - start) + ",this is �߳�2");
        }, "�߳�2");
 
        Thread thread3 = new Thread(() -> {
            long end = new Date().getTime();
            System.out.println("time wait:" + (end - start) + ",this is �߳�3");
        }, "�߳�3");
        
        //�̳߳�executor����scheduleWithFixedDelay������ͬʱ����������ͬ���ȵ��̡߳��ӽ���п��Կ���ÿ���̰߳����Լ��ĵ��Ȼ������ŵ����С���ʱ�޸��߳�2��һ�������ٿ������н����
        //Executors.newSingleThreadScheduledExecutor()�������̳߳�ͬʱ�������߳�ʱ��ÿ���̶߳��ᰴ���Լ��ĵ�����ִ�У����ǵ�����һ���̱߳�����ʱ���������̶߳����ܵ�Ӱ�챻������������Ȼ���ᰴ�����������ִ�У����ǻ���������ӳ١�
        excutor.scheduleWithFixedDelay(thread1, 0, 1, TimeUnit.SECONDS);
        excutor.scheduleWithFixedDelay(thread2, 0, 2, TimeUnit.SECONDS);
        excutor.scheduleWithFixedDelay(thread3, 0, 3, TimeUnit.SECONDS);
    }

}

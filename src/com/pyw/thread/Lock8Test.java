package com.pyw.thread;

import java.util.concurrent.TimeUnit;

/**
 * 8��״̬�µ������������
 * @author ASUS
 *1.��׼ִ�У� E �� M ���ִ��
 *2.E����ͣ4s:
 *3.������ͨ�ķ�����
 *4.�����ֻ�����
 *5.������̬ͬ��������ӡһ���ֻ���
 *6.������̬ͬ��������ӡ2���ֻ���
 *7.һ����̬ͬ��������һ����ͨͬ����������ӡͬһ���ֻ���
 *8.һ����̬ͬ��������һ����ͨͬ����������ӡͬ2���ֻ���
 *
 *������
 *1,2��һ����������ж��synchronized������ĳһ��ʱ�����߳�ȥ�������е�һ��synchronized�����ˣ�
 *	   �����̶߳�ֻ�ܵȴ���Ҳ������ĳһ��ʱ���ڣ�ֻ����һ���߳̿���ȥ������Щsynchronized����.
 *	 �������ǶԵ�ǰ����this���������������̲߳��ܽ��뵱��ǰ���������synchronized������
 *
 *3����ͨ������ͬ�����޹�
 *
 *4. ��ͬ�Ķ��󲻴��ڶ���ͬ�������������
 *
 *5��6��static�����������У�static ���ε�ͬ���������ȫ������
 *		������ͨͬ�������������ǵ�ǰ�����ʵ����
 *		���ھ�̬ͬ�������������ǵ�ǰ���Class����
 *7��8��һ�����������һ���Ƕ����������ȫ����ͻ�����԰�˳��ִ��
 *
 *
 */
class Phone{
	public static synchronized void sendEmail() throws Exception {
		TimeUnit.SECONDS.sleep(3);
		System.out.println("---- send email");
	}
	
	public  synchronized void sendMsg() throws Exception{
		System.out.println("---- send msg");
	}
	
	public  void sayHello() throws Exception{
		System.out.println("---- say hello");
	}
}

public class Lock8Test {

	public static void main(String[] args) {
		Phone phone = new Phone();
		Phone phone2 = new Phone();
		new Thread(()->{
			try {
				phone.sendEmail();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"A").start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(()->{
			try {
//				phone2.sendMsg();
//				phone2.sayHello();
				phone.sendMsg();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"B").start();
		
		
	}
}

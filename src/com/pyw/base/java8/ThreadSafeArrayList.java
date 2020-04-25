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
//		// ע��Java�е�����   :: ��д��
//		list.forEach(System.out::println);
		
		//ArrayList�̲߳���ȫ��ʾ��
			//�ᷢ�����߳���д��֮����������ÿ�δ�ӡ����һ��
//		List<String> list = new ArrayList<String>();
//		for (int i = 0; i <= 3;i++) {
//			new Thread(()->{
//				list.add(UUID.randomUUID().toString().substring(0,8));
//				System.out.println(list);
//			},String.valueOf(i)).start(); 
//		}
		
//		//����������������µ�ʱ��ᱨ �������쳣�� java.util.ConcurrentModificationException
//		List<String> list = new ArrayList<String>();
//		for (int i = 0; i <= 30;i++) {
//			new Thread(()->{
//				list.add(UUID.randomUUID().toString().substring(0,8));
//				System.out.println(list);
//			},String.valueOf(i)).start(); 
//		}
		
		//�������һ��ʹ��Vector��Vector���̰߳�ȫ�ģ������ܵ�һЩ��ArrayList���̲߳���ȫ�ģ����ܸ�һЩ
			//ȱ���� Vector ������ add������������
//		List<String> list = new Vector<String>();
//		for (int i = 0; i <= 30;i++) {
//			new Thread(()->{
//				list.add(UUID.randomUUID().toString().substring(0,8));
//				System.out.println(list);
//			},String.valueOf(i)).start(); 
//		}
		
		//�����������Collections.synchronizedList
//		List<String> list = Collections.synchronizedList(new ArrayList<>());
//		for (int i = 0; i <= 30;i++) {
//			new Thread(()->{
//				list.add(UUID.randomUUID().toString().substring(0,8));
//				System.out.println(list);
//			},String.valueOf(i)).start(); 
//		}
		
		//�����������дʱ���Ƽ��� 
		List<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 0; i <= 30;i++) {
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			},String.valueOf(i)).start(); 
		}
		
		//HashSet���̰߳�ȫ������
		Set<String> set = new CopyOnWriteArraySet<String>();
		for (int i = 0; i <= 30;i++) {
			new Thread(()->{
				set.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(set);
			},String.valueOf(i)).start(); 
		}
		
		//HashMap���̰߳�ȫ������
		Map<String,String> map = new ConcurrentHashMap();
		for (int i = 0; i <= 30;i++) {
			new Thread(()->{
				map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
				System.out.println(map);
			},String.valueOf(i)).start(); 
		}
	}
}

package com.pyw.base.java8;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;

import com.pyw.base.java8.temp.Utils;

/**
 * �򵥵�ʹ����һ�£��˽���һ�³������﷨��
 * @author ASUS
 *
 */
/*
 * 1.����ʽ��̣���������ǽ�һ��������Ҳ��Ϊ����Ϊ������Ϊһ���������д��ݡ�ͨ�������ἰ�ø��������������̣�����������Ƕ����ݵĳ��󣨸��ָ�����POJO�ࣩ��������ʽ������Ƕ���Ϊ�ĳ��󣨽���Ϊ��Ϊһ���������д��ݣ�����JavaScript�����Ǻܳ�����һ���﷨���ԣ�����Java�н�һ��������Ϊ����������ȴ�в�ͨ������JDK8�ĳ��ִ�����Java����һ���ơ�
 * 2.lambda�������﷨��ʽ��
 * 	(parameters)  ->  expression
    (parameters)  -> {statements;}
	    �﷨�����
		(parameters)��lambda���ʽ�Ĳ����б��䶨�巽��ΪJAVA��ͨ�ķ�����ͬ������(Object a, Object b)��
		-> ��ͷ���ǲ����б���lambda���ʽ���ⲿ�ֵķָ����š�
		expression �����ʽ
		{statements; } ��䡣
 */

public class LambdaTest {

	public static void main(String[] args) {
		LambdaTest t = new LambdaTest();
		//t.testAnonInnerClassNoParm();
		t.testStaticMethod();
	}
	
	public void testAnonInnerClassNoParm() {
		// jdk 7 ֮ǰ�İ汾д����
//		new Thread(new Runnable() {
//		    @Override
//		    public void run() {
//		        System.out.println("Hello");
//		        System.out.println("Jimmy");
//		    }
//		}).start();
		
		new Thread(() ->  {
			System.out.println("Hello");
			System.out.println("World");
		}).start();
		
		//���ֻ��һ�����ʱ�����ԶԴ������м�д
		new Thread(() ->System.out.println("Hello world")).start();
		
	}
	
	public   void testStaticMethod() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(111);
		list.add(12);
		Collections.sort(list,(o1,o2) -> Utils.compare(o1,o2));		
		for (Integer i : list ) {
			System.out.println(i);
		}
	}
	
}

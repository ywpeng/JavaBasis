package com.pyw.base.java8;

//@FunctionalInterface  //����ʽ�ӿڵ�������ע�⡣��������˸�ע�⣬�ӿ���ķ�����ֻ����һ��������ᱨ��
interface Foo{
	//����ʽ����нӿ�����ֻ�ܶ���һ������
	//public void sayHello();
	public int add(int i,int j);
	
	public default int mul(int i,int j) {
		return i * j;
	}
	
	public default int mul2(int i) {
		return i * i;
	}
	
	public static int div(int i,int j) {
		return i/j;
	}
}

/**
 * 
 * @author ASUS
 *1.����ʽ���
 *  ����������
 *  д���Ҽ�ͷ
 *  ��ش�����
 *  
 *  2.@FunctionalInterface
 *  
 *  3.jdk8 �������� default ��ʹ��:�ӿ��п�����default�ķ����壬���ҿ����ж��
 *  
 *  4.jdk8 �������� static ��ʹ��:�ӿ��п����� static �ķ����壬���ҿ����ж��
 */
public class LambadaDemo {

	public static void main(String[] args) {
			
		
		//��ʽһ�������ڲ���
//		Foo foo = new Foo() {			
//			@Override
//			public void sayHello() {
//				// TODO Auto-generated method stub
//				System.out.println("Hello ");
//			}
//		};
		
		//��ʽ����Lambada�ķ�ʽ��д����������ڲ���������������
//		Foo foo = () -> {
//			System.out.println("Hello ");
//		};
//		foo.sayHello();
		
		Foo foo = (int x,int y) -> {
			System.out.println("Come to add ");
			return x + y;
		};
		foo.add(3,5);
		
		foo.mul(3, 5);
		
		Foo.div(15, 3);
	}
}

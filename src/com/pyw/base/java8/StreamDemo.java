package com.pyw.base.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 掌握 4 种函数式编程的写法
 * 掌握流式编程的写法
 * @author ASUS
 *
 */

/*
 * 流（Stream）：是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。
 * 	集合讲的数据数据，流讲的是计算
 * 1.Stream不会改变原对象，它会返回一个持有结果的新Stream
 * 2.Stream操作是延迟执行的，他会等到需要结果的时候才执行
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
	private  Integer id;
	private String userName;
	private int age;
}

public class StreamDemo {

	/* 要求：
	 * 	找出 偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序
	 *  只输出一个用户名字
	 * 
	 */
	public static void main(String[] args) {
		User u1 = new User(11,"a",23);
		User u2 = new User(12,"b",24);
		User u3 = new User(13,"c",22);
		User u4 = new User(14,"d",28);
		User u5 = new User(16,"e",26);
		
		List<User> list = Arrays.asList(u1,u2,u3,u4,u5);
		
		/*
		 * Java 内置核心四大函数式接口
		 * 接口名称		参数类型	返回类型	用途
		 * ---------------------------------------
		 * Consumer<T>	T		void	对类型为T的对象应用操作，包含方法 :
		 * 	消费型接口						void accept(T t)
		 * Supplier<T>	无		T		返回类型为T的对象，包含方法：
		 * 	供给型接口						T get();
		 * Function<T,R>T		R		对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：
		 * 函数型接口						R apply(T t)
		 * Predicate<T>	T		boolean	确定类型为T的对象是否满足某约束，并返回Boolean值。包含反复：
		 * 断定型接口						boolean test(T t)
		 */
		
		//示例一：函数式接口
//		Function<String,Integer> function = new Function<String,Integer>() {
//			@Override
//			public Integer apply(String s) {
//				// TODO Auto-generated method stub
//				return 1024;
//			}
//		};
//		function.apply("a");
		
//		Function<String,Integer> functionL = s -> {
//			return s.length();
//		};
//		System.out.println(functionL.apply("a"));
		
		//示例2：判定型接口
//		Predicate<String> predicate = new Predicate<String>() {
//			@Override
//			public boolean test(String t) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//		};
//		predicate.test("a");
		
//		Predicate<String> predicateL=  s -> {
//			return false;
//		};
//		System.out.println(predicateL.test("a"));
		
		//示例3：消费型接口
//		Consumer<String> consumer = new Consumer<String>() {
//			@Override
//			public void accept(String t) {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//		consumer.accept("a");
		
//		Consumer<String> consumerL = s -> {
//			System.out.println(s);
//		};
//		consumerL.accept("a");
//		
//		//示例4：供给型接口			
//		Supplier<String> supplier = () -> {
//			return "abc";
//		};
//		supplier.get();
		
		//================================================== 流式编程============
	
		System.out.println("--------- 使用流式编程获得所需的结果------------");
		list.stream().filter(u -> {//1.从Stream中使用filter 过滤 age  为偶数的条件
			return u.getAge() % 2 == 0;
		}).filter(u -> {           //2.从Stream中使用filter 过滤  age>24 的条件
			return u.getAge() > 24;
		}).map(s -> {				//3.通过 map映射 处理字符小写转大写
			return s.getUserName().toUpperCase();
		} ).sorted((o1,o2) -> {		//4.按字母的倒序排序
			return o2.compareTo(o1);
		} ).limit(1)				//5.控制数据条数
		.forEach(System.out::println);
		
		
		
	}
}

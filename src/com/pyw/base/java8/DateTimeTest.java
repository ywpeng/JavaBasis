package com.pyw.base.java8;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class DateTimeTest {
	public static void main(String[] args) {
		DateTimeTest t = new DateTimeTest();
		//t.getLocalDate();
//		t.compareDate();
		t.getFormateDate();
	}

	/*背景
	 * Java对日期、日历及时间的处理一直以来都饱受诟病，比如java.util.Date和java.util.Calendar类易用性差，不支持时区，非线程安全；还有用于格式化日期的类DateFormat也是非线程安全的等问题。
Java8引入的新的一系列API，对时间日期的处理提供了更好的支持，清楚的定义了时间日期的一些概念，比如说，瞬时时间（Instant）,持续时间（duration），日期（date）,时间（time），时区（time-zone）以及时间段（Period）。
同时，借鉴了Joda库的一些优点，比如将人和机器对时间日期的理解区分开的。
	 */
	
	/*
	 * 简介
新的时间日期API核心位于java.time内，另外也在java.time.chrono，java.time.format，java.time.temporal和java.time.zone有相关的API，但使用频次较少。
Java8常用的日期和时间类包含LocalDate、LocalTime、Instant、Duration、Period、LocalDateTime以及ZonedDateTime等。
LocalDate：不包含时间的日期，比如2019-10-14。可以用来存储生日，周年纪念日，入职日期等。
LocalTime：与LocalDate想对照，它是不包含日期的时间。
LocalDateTime：包含了日期及时间，没有偏移信息（时区）。
ZonedDateTime：包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
Instant：时间戳，与System.currentTimeMillis()类似。
Duration：表示一个时间段。
Period：用来表示以年月日来衡量一个时间段。
另外，还有新的日期解析格式化类DateTimeFormatter。
	 */
	
	/**
	 * 获取日期
	 */
	public void getLocalDate() {
		LocalDate today = LocalDate.now();
		System.out.println(today);

		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();

		System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);
		
		// 月份中的第几天
		int dayOfMonth = today.getDayOfMonth();
		// 一周的第几天
		DayOfWeek dayOfWeek = today.getDayOfWeek();
		// 月份的天数
		int length = today.lengthOfMonth();
		// 是否为闰年
		boolean leapYear = today.isLeapYear();

		//创建任意日期
		LocalDate oneDay = LocalDate.of(2019,10,1);
		System.out.println(oneDay);

		LocalDate parseDay = LocalDate.parse("2019-10-01");
		System.out.println(parseDay);
	}
	
	/**
	 * 日期比较
	 */
	public void compareDate() {
		//LocalDate重写了equals方法，让日期的比较也变得简单了。
		//同时，针对日期还可延伸出MonthDay或YearMonth类，顾名思义，只包含月天或年月。同样适用于equals方法来比较。
		// 定义任意日期
		LocalDate oneDay = LocalDate.of(2019, 10, 1);
		System.out.println(oneDay);

		// 定义任意比较
		LocalDate anyDay = LocalDate.of(2019, 10, 1);
		System.out.println(oneDay.equals(anyDay));
		
		//另外使用before和after可以比较两个日期前后时间。
		boolean notBefore = LocalDate.parse("2019-10-01").isBefore(LocalDate.parse("2019-10-02"));
		boolean isAfter = LocalDate.parse("2019-10-01").isAfter(LocalDate.parse("2019-10-02"));
			
	}
	
	/**
	 * 日期的前推、后推 操作
	 */
	public void getABDate() {
		//对日期进行前一天后一天或前一个月的加减也变得十分方便。
		LocalDate tomorrowDay = LocalDate.now().plusDays(1);
		LocalDate nextMonth =  LocalDate.now().plusMonths(1);
		
		//要获取某一天的开始时间和当天所在月的第一天等。
		LocalDate.now().atStartOfDay();
		LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
	}
	
	/**
	 * 获取时间
	 */
	public void getTime() {
		//LocalTime和LocalDate类似，区别在于LocalDate不包含具体时间，而LocalTime包含具体时间。同样可以使用now或of方法来获得对象。
		LocalTime localTime = LocalTime.now();

		LocalTime oneTime = LocalTime.of(10,10,10);
		//LocalDate类似它也拥有parse、isBefore、获取时间单元等方法，就不再赘述。
		//需要注意的是，LocalTime获得的时间格式为：11:41:58.904。也就是，HH:mm:ss.nnn，这里nnn是纳秒。
	}
	
	/**
	 * 获取时间日期的组合
	 */
	public void getDateTime() {
		//LocalDateTime表示日期和时间组合。可以通过of()方法直接创建，也可以调用LocalDate的atTime()方法或LocalTime的atDate()方法将LocalDate或LocalTime合并成一个LocalDateTime。
		//创建时间示例：
		LocalDateTime now = LocalDateTime.now();

		LocalDateTime oneTime = LocalDateTime.of(2019,10,14,10,12,12);

		// 拼接日期
		LocalTime.now().atDate(LocalDate.now());
		//LocalDateTime与LocalDate和LocalTime之间可以相互转化。其他日期增减等操作与上面的类似。
	}
	
	/**
	 * 获取时间戳
	 */
	public void getInstant() {
		//Instant用于一个时间戳，与System.currentTimeMillis()类似，但Instant可以精确到纳秒（Nano-Second）。
		//Instant除了可以使用now()方法创建，还可以通过ofEpochSecond方法创建。
		Instant now = Instant.now();

		Instant.ofEpochSecond(365 * 24 * 60, 100);
		//其中ofEpochSecond第一个参数表示秒，第二个参数表示纳秒。整体表示：从1970-01-01 00:00:00开始后的365天100纳秒的时间点。
	}
	
	/**
	 * 获取时间段
	 */
	public void getDuration() {
		//Duration的内部实现与Instant类似，但Duration表示时间段，通过between方法创建。
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		Duration duration = Duration.between(from, to);

		// 区间统计换算
		// 总天数
		long days = duration.toDays();
		// 小时数
		long hours = duration.toHours();
		// 分钟数
		long minutes = duration.toMinutes();
		// 秒数
		long seconds = duration.getSeconds();
		// 毫秒数
		long milliSeconds = duration.toMillis();
		// 纳秒数
		long nanoSeconds = duration.toNanos();
		//Duration对象还可以通过of()方法创建，该方法参数为时间段长度和时间单位。
		// 7天
		Duration duration1 = Duration.of(7, ChronoUnit.DAYS);
		// 60秒
		Duration duration2 = Duration.of(60, ChronoUnit.SECONDS);
	}
	
	/**
	 * 获取日期段
	 */
	public void getPeriod() {
		//Period与Duration类似，获取一个时间段，只不过单位为年月日，也可以通过of方法和between方法创建，between方法接收的参数为LocalDate。
		Period period = Period.of(1, 10, 25);
		Period period1 = Period.between(LocalDate.now(), LocalDate.now().plusYears(1));
	}
	
	/**
	 * 创建时区时间
	 */
	public void getZonedDateTime() {
		//onedDateTime类，用于处理带时区的日期和时间。ZoneId表示不同的时区。大约有40不同的时区。
		//获取所有时区集合：
		Set allZoneIds = ZoneId.getAvailableZoneIds();
		//创建时区：
		ZoneId zoneId = ZoneId.of("Asia/Shanghai");
		//把LocalDateTime转换成特定的时区：
		ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zoneId);

		//另外和时区一起使用的类是OffsetDateTime类，OffsetDateTime是不变的，表示date-time偏移，存储所有日期和时间字段，精确至纳秒，从UTC/Greenwich计算偏移。
	}
	
	/**
	 * 时间日期格式化
	 */
	public void getFormateDate(){
//		Java8对日期的格式化操作非常简单，首先看到上面的类大多都提供了parse方法，可以直接通过解析字符串得到对应的对象。
//		而日期和时间的格式化可通过LocalDateTime的format方法进行格式化。
		LocalDateTime dateTime = LocalDateTime.now();
		String str = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println(str);
		str = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(str);
//		可以使用DateTimeFormatter预置的格式，也可以通过DateTimeFormatter.ofPattern方法来指定格式。
		
	}
	
	
	
	
}

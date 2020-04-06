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

	/*����
	 * Java�����ڡ�������ʱ��Ĵ���һֱ����������ڸ��������java.util.Date��java.util.Calendar�������Բ��֧��ʱ�������̰߳�ȫ���������ڸ�ʽ�����ڵ���DateFormatҲ�Ƿ��̰߳�ȫ�ĵ����⡣
Java8������µ�һϵ��API����ʱ�����ڵĴ����ṩ�˸��õ�֧�֣�����Ķ�����ʱ�����ڵ�һЩ�������˵��˲ʱʱ�䣨Instant��,����ʱ�䣨duration�������ڣ�date��,ʱ�䣨time����ʱ����time-zone���Լ�ʱ��Σ�Period����
ͬʱ�������Joda���һЩ�ŵ㣬���罫�˺ͻ�����ʱ�����ڵ�������ֿ��ġ�
	 */
	
	/*
	 * ���
�µ�ʱ������API����λ��java.time�ڣ�����Ҳ��java.time.chrono��java.time.format��java.time.temporal��java.time.zone����ص�API����ʹ��Ƶ�ν��١�
Java8���õ����ں�ʱ�������LocalDate��LocalTime��Instant��Duration��Period��LocalDateTime�Լ�ZonedDateTime�ȡ�
LocalDate��������ʱ������ڣ�����2019-10-14�����������洢���գ���������գ���ְ���ڵȡ�
LocalTime����LocalDate����գ����ǲ��������ڵ�ʱ�䡣
LocalDateTime�����������ڼ�ʱ�䣬û��ƫ����Ϣ��ʱ������
ZonedDateTime������ʱ��������������ʱ�䣬ƫ��������UTC/��������ʱ��Ϊ��׼�ġ�
Instant��ʱ�������System.currentTimeMillis()���ơ�
Duration����ʾһ��ʱ��Ρ�
Period��������ʾ��������������һ��ʱ��Ρ�
���⣬�����µ����ڽ�����ʽ����DateTimeFormatter��
	 */
	
	/**
	 * ��ȡ����
	 */
	public void getLocalDate() {
		LocalDate today = LocalDate.now();
		System.out.println(today);

		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();

		System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);
		
		// �·��еĵڼ���
		int dayOfMonth = today.getDayOfMonth();
		// һ�ܵĵڼ���
		DayOfWeek dayOfWeek = today.getDayOfWeek();
		// �·ݵ�����
		int length = today.lengthOfMonth();
		// �Ƿ�Ϊ����
		boolean leapYear = today.isLeapYear();

		//������������
		LocalDate oneDay = LocalDate.of(2019,10,1);
		System.out.println(oneDay);

		LocalDate parseDay = LocalDate.parse("2019-10-01");
		System.out.println(parseDay);
	}
	
	/**
	 * ���ڱȽ�
	 */
	public void compareDate() {
		//LocalDate��д��equals�����������ڵıȽ�Ҳ��ü��ˡ�
		//ͬʱ��������ڻ��������MonthDay��YearMonth�࣬����˼�壬ֻ������������¡�ͬ��������equals�������Ƚϡ�
		// ������������
		LocalDate oneDay = LocalDate.of(2019, 10, 1);
		System.out.println(oneDay);

		// ��������Ƚ�
		LocalDate anyDay = LocalDate.of(2019, 10, 1);
		System.out.println(oneDay.equals(anyDay));
		
		//����ʹ��before��after���ԱȽ���������ǰ��ʱ�䡣
		boolean notBefore = LocalDate.parse("2019-10-01").isBefore(LocalDate.parse("2019-10-02"));
		boolean isAfter = LocalDate.parse("2019-10-01").isAfter(LocalDate.parse("2019-10-02"));
			
	}
	
	/**
	 * ���ڵ�ǰ�ơ����� ����
	 */
	public void getABDate() {
		//�����ڽ���ǰһ���һ���ǰһ���µļӼ�Ҳ���ʮ�ַ��㡣
		LocalDate tomorrowDay = LocalDate.now().plusDays(1);
		LocalDate nextMonth =  LocalDate.now().plusMonths(1);
		
		//Ҫ��ȡĳһ��Ŀ�ʼʱ��͵��������µĵ�һ��ȡ�
		LocalDate.now().atStartOfDay();
		LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
	}
	
	/**
	 * ��ȡʱ��
	 */
	public void getTime() {
		//LocalTime��LocalDate���ƣ���������LocalDate����������ʱ�䣬��LocalTime��������ʱ�䡣ͬ������ʹ��now��of��������ö���
		LocalTime localTime = LocalTime.now();

		LocalTime oneTime = LocalTime.of(10,10,10);
		//LocalDate������Ҳӵ��parse��isBefore����ȡʱ�䵥Ԫ�ȷ������Ͳ���׸����
		//��Ҫע����ǣ�LocalTime��õ�ʱ���ʽΪ��11:41:58.904��Ҳ���ǣ�HH:mm:ss.nnn������nnn�����롣
	}
	
	/**
	 * ��ȡʱ�����ڵ����
	 */
	public void getDateTime() {
		//LocalDateTime��ʾ���ں�ʱ����ϡ�����ͨ��of()����ֱ�Ӵ�����Ҳ���Ե���LocalDate��atTime()������LocalTime��atDate()������LocalDate��LocalTime�ϲ���һ��LocalDateTime��
		//����ʱ��ʾ����
		LocalDateTime now = LocalDateTime.now();

		LocalDateTime oneTime = LocalDateTime.of(2019,10,14,10,12,12);

		// ƴ������
		LocalTime.now().atDate(LocalDate.now());
		//LocalDateTime��LocalDate��LocalTime֮������໥ת�����������������Ȳ�������������ơ�
	}
	
	/**
	 * ��ȡʱ���
	 */
	public void getInstant() {
		//Instant����һ��ʱ�������System.currentTimeMillis()���ƣ���Instant���Ծ�ȷ�����루Nano-Second����
		//Instant���˿���ʹ��now()����������������ͨ��ofEpochSecond����������
		Instant now = Instant.now();

		Instant.ofEpochSecond(365 * 24 * 60, 100);
		//����ofEpochSecond��һ��������ʾ�룬�ڶ���������ʾ���롣�����ʾ����1970-01-01 00:00:00��ʼ���365��100�����ʱ��㡣
	}
	
	/**
	 * ��ȡʱ���
	 */
	public void getDuration() {
		//Duration���ڲ�ʵ����Instant���ƣ���Duration��ʾʱ��Σ�ͨ��between����������
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		Duration duration = Duration.between(from, to);

		// ����ͳ�ƻ���
		// ������
		long days = duration.toDays();
		// Сʱ��
		long hours = duration.toHours();
		// ������
		long minutes = duration.toMinutes();
		// ����
		long seconds = duration.getSeconds();
		// ������
		long milliSeconds = duration.toMillis();
		// ������
		long nanoSeconds = duration.toNanos();
		//Duration���󻹿���ͨ��of()�����������÷�������Ϊʱ��γ��Ⱥ�ʱ�䵥λ��
		// 7��
		Duration duration1 = Duration.of(7, ChronoUnit.DAYS);
		// 60��
		Duration duration2 = Duration.of(60, ChronoUnit.SECONDS);
	}
	
	/**
	 * ��ȡ���ڶ�
	 */
	public void getPeriod() {
		//Period��Duration���ƣ���ȡһ��ʱ��Σ�ֻ������λΪ�����գ�Ҳ����ͨ��of������between����������between�������յĲ���ΪLocalDate��
		Period period = Period.of(1, 10, 25);
		Period period1 = Period.between(LocalDate.now(), LocalDate.now().plusYears(1));
	}
	
	/**
	 * ����ʱ��ʱ��
	 */
	public void getZonedDateTime() {
		//onedDateTime�࣬���ڴ����ʱ�������ں�ʱ�䡣ZoneId��ʾ��ͬ��ʱ������Լ��40��ͬ��ʱ����
		//��ȡ����ʱ�����ϣ�
		Set allZoneIds = ZoneId.getAvailableZoneIds();
		//����ʱ����
		ZoneId zoneId = ZoneId.of("Asia/Shanghai");
		//��LocalDateTimeת�����ض���ʱ����
		ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zoneId);

		//�����ʱ��һ��ʹ�õ�����OffsetDateTime�࣬OffsetDateTime�ǲ���ģ���ʾdate-timeƫ�ƣ��洢�������ں�ʱ���ֶΣ���ȷ�����룬��UTC/Greenwich����ƫ�ơ�
	}
	
	/**
	 * ʱ�����ڸ�ʽ��
	 */
	public void getFormateDate(){
//		Java8�����ڵĸ�ʽ�������ǳ��򵥣����ȿ�����������඼�ṩ��parse����������ֱ��ͨ�������ַ����õ���Ӧ�Ķ���
//		�����ں�ʱ��ĸ�ʽ����ͨ��LocalDateTime��format�������и�ʽ����
		LocalDateTime dateTime = LocalDateTime.now();
		String str = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println(str);
		str = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(str);
//		����ʹ��DateTimeFormatterԤ�õĸ�ʽ��Ҳ����ͨ��DateTimeFormatter.ofPattern������ָ����ʽ��
		
	}
	
	
	
	
}

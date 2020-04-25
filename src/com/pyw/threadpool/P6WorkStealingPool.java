package com.pyw.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * newWorkStealingPool���������jdk1.8�����ģ����������Ĳ��в������̬�����͹ر��̡߳���ͬ������ͼ����������еĴ�С��
 * ���ԱȽ����ڸ߸��صĻ�����ͬ��Ҳ�Ƚ������ڵ�ִ�е�����ᴴ������������ݹ������ʺ�ʹ���ںܺ�ʱ�Ĳ�����
 * ����newWorkStealingPool����ThreadPoolExecutor����չ�������µ��̳߳���ForkJoinPool����չ��
 * ���Ƕ�����ͳһ��һ��Executors����ʵ�֣������ܹ������ʹ��CPU���ж�������������в������������ʺ�ʹ���ںܺ�ʱ��������
 * @author ASUS
 *
 */
public class P6WorkStealingPool {
	// �߳���
    private static final int threads = 10;
    // ���ڼ����߳��Ƿ�ִ�����
    CountDownLatch countDownLatch = new CountDownLatch(threads);
 
   // @Test
    public void test1() throws InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }
 
 

    public void test2() throws InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//            Callable ������ֵ
            executorService.submit(new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }));
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }
 
    //@Test
    public void test3() throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//          Runnable ������ֵ
            FutureTask<?> futureTask = new FutureTask<>(new Callable<String>() {
                /**
                 * call
                 * @return currentThreadName
                 */
                @Override
                public String call() {
                    return Thread.currentThread().getName();
                }
            });
            executorService.submit(new Thread(futureTask));
            System.out.println(futureTask.get());
        }
        System.out.println("---- end ----");
    }

}

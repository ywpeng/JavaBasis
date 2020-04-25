package com.pyw.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * newWorkStealingPool（这个是在jdk1.8出来的）会更加所需的并行层次来动态创建和关闭线程。它同样会试图减少任务队列的大小，
 * 所以比较适于高负载的环境。同样也比较适用于当执行的任务会创建更多任务，如递归任务。适合使用在很耗时的操作，
 * 但是newWorkStealingPool不是ThreadPoolExecutor的扩展，它是新的线程池类ForkJoinPool的扩展，
 * 但是都是在统一的一个Executors类中实现，由于能够合理的使用CPU进行对任务操作（并行操作），所以适合使用在很耗时的任务中
 * @author ASUS
 *
 */
public class P6WorkStealingPool {
	// 线程数
    private static final int threads = 10;
    // 用于计数线程是否执行完成
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
//            Callable 带返回值
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
//          Runnable 带返回值
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

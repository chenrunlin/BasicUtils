package com.test.Thread.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableTest {
	/**
	 * 此类异步计算的好处:
	 * 尽可能多的占用系统资源，提供快速运算
	 * 可以监控线程的执行情况，比如是否执行完毕、是否有返回值、是否有异常等。
	 * 可以为用户提供更好的支持，比如例子中的运算进度等。
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// Executors是一个静态工具类，提供了异步执行器的创建能力，是异步计算的入口类。
        ExecutorService es = Executors.newSingleThreadExecutor();
        // 线程执行后的期望值
        Future<Integer> future = es.submit(new TaxCalculator(100));
        while (!future.isDone()) {
            // 还没有运算完成，等待200毫秒
            TimeUnit.MICROSECONDS.sleep(200);
            // 输出进度符号
            System.out.print("*");
        }
        System.out.println("\n计算完成，税金是：" + future.get() + "  元 ");
        es.shutdown();
	}
}


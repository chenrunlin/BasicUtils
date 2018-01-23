package com.test.Thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 税款计算器
 * 这里模拟了一个复杂运算：税款计算器，该运算可能要花费10秒钟的时间，此时不能让用户一直等着吧，需要给用户输出点什么，
 * 让用户知道系统还在运行，这也是系统友好性的体现：用户输入即有输出，若耗时较长，则显示运算进度。
 * 如果我们直接计算，就只有一个main线程，是不可能有友好提示的，如果税金不计算完毕，也不会执行后续动作，
 * 所以此时最好的办法就是重启一个线程来运算，让main线程做进度提示
 */
public class TaxCalculator implements Callable<Integer>{
	
	// 本金
    private int seedMoney;

    // 接收主线程提供的参数
    public TaxCalculator(int _seedMoney) {
        seedMoney = _seedMoney;
    }

	public Integer call() throws Exception {
		// 复杂计算,运行一次需要2秒
        TimeUnit.MILLISECONDS.sleep(10000);
        return seedMoney / 10;
	}
}

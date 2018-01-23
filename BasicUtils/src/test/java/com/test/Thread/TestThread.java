package com.test.Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 下面代码将输出最大线程数，可供测试参考
 */
public class TestThread extends Thread {
    private static final AtomicInteger count = new AtomicInteger();
 
    public static void main(String[] args) {
        while (true)
            (new TestThread()).start();
    }
 
    @Override
    public void run() {
        System.out.println(count.incrementAndGet());
 
        while (true)
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                break;
            }
    }
}

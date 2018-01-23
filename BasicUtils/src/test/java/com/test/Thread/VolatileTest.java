package com.test.Thread;

public class VolatileTest {
	/**
	 * 运行逻辑：
	 * 1、启动100个线程，修改共享资源count的值
	 * 2、暂停15秒，观察活动线程数是否为1(即只剩下主线程再运行)，若不为1，则再等待15秒。
	 * 3、判断共享资源是否是不安全的，即实际值与理想值是否相同，若不相同，则发现目标，此时count的值为脏数据。
	 * 4、如果没有找到，继续循环，直到达到最大循环为止。
	 * 运行结果如下：
	 * 		循环到：40 遍，出现线程不安全的情况
	 * 		此时，count= 999
	 * 结论：这只是一种可能的结果，每次执行都有可能产生不同的结果。这也说明我们的count变量没有实现数据同步，
	 * 		在多个线程修改的情况下，count的实际值与理论值产生了偏差，直接说明了volatile关键字并不能保证线程的安全。
	 */
	public static void main(String[] args) throws InterruptedException{
		// 理想值，并作为最大循环次数
	    int value = 1000;
	    // 循环次数,防止造成无限循环或者死循环
	    int loops = 0;
	    // 主线程组,用于估计活动线程数
	    ThreadGroup tg = Thread.currentThread().getThreadGroup();
	    while (loops++ < value) {
	        // 共享资源清零
	        UnsafeThread ut = new UnsafeThread();
	        for (int i = 0; i < value; i++) {
	            new Thread(ut).start();
	        }
	        // 先等15毫秒，等待活动线程为1
	        do {
	            Thread.sleep(15);
	        } while (tg.activeCount() != 1);//观察活动线程数是否为1(即只剩下主线程再运行)
	        // 检查实际值与理论值是否一致
	        if (ut.getCount() != value) {
	            // 出现线程不安全的情况
	            System.out.println("循环到：" + loops + " 遍，出现线程不安全的情况");
	            System.out.println("此时，count= " + ut.getCount());
	            System.exit(0);
	        }
	    }
	}
}

class UnsafeThread implements Runnable {
    // 共享资源
    private volatile int count = 0;

    public void run() {
        // 增加CPU的繁忙程度,不必关心其逻辑含义
        for (int i = 0; i < 1000; i++) {
            Math.hypot(Math.pow(92456789, i), Math.cos(i));
        }
        count++;
    }

    public int getCount() {
        return count;
    }
}

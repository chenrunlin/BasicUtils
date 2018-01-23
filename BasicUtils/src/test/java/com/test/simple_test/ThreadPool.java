package com.test.simple_test;

import java.util.LinkedList;

public class ThreadPool extends ThreadGroup {
	private boolean isClosed = false;
	
	private LinkedList<Runnable> workQueue; // 表示工作队列
	private static int threadPoolID; //表示线程池ID
	private int threadID; //表示工作线程ID
	
	public ThreadPool(int poolSize) {
		super("ThreadPool-"+(threadPoolID));
		
		setDaemon(true);
		//创建工作队列，
		workQueue = new LinkedList<Runnable>();
		//启动工作线程
		for(int i = 0; i < poolSize; i++) {
			new WorkThread().start();
		}
		
	}
	
	public synchronized void execute(Runnable task) {
		
		if(isClosed) {
			//线程池被关闭则抛出IllegalStateException
			throw new IllegalStateException();
		}
		
		if(task != null) {
			workQueue.add(task);
			//唤醒getTask()方法中正在等待任务的工作队列
			notify();
		}
		
	}	
	
	protected synchronized Runnable getTask() throws InterruptedException {
		while(workQueue.size() == 0) {
			if(isClosed)
				return null;
			//如果工作队列中没有任务，就等待任务
			wait();
		}
		
		return workQueue.removeFirst();
	}
	
	/**
	 *关闭线程池
	 */
	public synchronized void close() {
		if(!isClosed) {
			isClosed = true;
			workQueue.clear(); //清空工作队列
			interrupt(); //中断所有的工作线程，该方法继承于ThreadGroup类
		}
	}
	
	/**
	 * 等待工作线程把所有任务执行完
	 */
	public void join() {
		synchronized(this) {
			isClosed = true;
			notifyAll();
		}
		
		Thread[] threads = new Thread[activeCount()];
		//获得线程组中当前所有或者的工作线程，继承于ThreadGroup
		int count = enumerate(threads);
		
		for(int i=0; i < count; i++){
			try{
				threads[i].join();
			}catch(InterruptedException ex) {
				
			}
		}		
	}
	
	
	private class WorkThread extends Thread{
		public WorkThread() {
			//加入当前ThreadPool线程组中
			super(ThreadPool.this, "WorkThread-" + threadID);
		}
		
		public void run() {
			//isInterrupted()方法集成自Thread类，判断线程是否被中断
			while(!isInterrupted()) {
				Runnable task = null;
				try {
					task = getTask(); //取出任务
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				
				//如果getTask()为null,或者线程执行getTask()时被中断，则结束此线程
				if(task == null) {
					return;
				}
				
				//运行任务，在while中catch所有东西
				try {
					task.run();
				}catch(Throwable t) {
					t.printStackTrace();
				}
			}
		}
		
	}
	
}

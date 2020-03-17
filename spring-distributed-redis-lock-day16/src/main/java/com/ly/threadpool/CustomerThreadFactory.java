package com.ly.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerThreadFactory implements ThreadFactory{

	//线程名称前缀
	private String namePrefix;
	//线程组
	private final ThreadGroup group;
	//线程数目
	private final AtomicInteger threadNumber = new AtomicInteger(0);
	
	public CustomerThreadFactory(String namePrefix) {
		SecurityManager a = System.getSecurityManager();
		group=(a != null) ? a.getThreadGroup():Thread.currentThread().getThreadGroup();
		this.namePrefix = namePrefix;
	}
	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		//真正创建线程的地方，设置了线程的线程组及线程名
		Thread t = new Thread(group, r, namePrefix+threadNumber.getAndIncrement(),0);
		//设置守护线程
		if(t.isDaemon()){
			t.setDaemon(false);
		}
		//设置线程优先级
		if(t.getPriority() != Thread.NORM_PRIORITY){
			t.setPriority(Thread.NORM_PRIORITY);
		}
		return t;
	}

	
}

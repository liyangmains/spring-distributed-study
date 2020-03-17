package com.ly.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * https://www.jianshu.com/p/9beab78a3afe
 * 
 * FixedThreadPool适用于为了满足资源管理的需求，
 * 	而需要限制当前线程数量的应用场景，它适用于负载比较重的服务器
 * SingleThreadExecutor适用于需要保证顺序地执行各个任务；
 * 	并且在任意时间点，不会有多个线程是活动的应用场景
 * CachedThreadPool是大小无界的线程池，适用于执行很多的短
 * 	期异步任务的小程序，或者是负载较轻的服务器
 * newFixedThreadPool(int nThreads)
 * newFixedThreadPool(int nThreads, ThreadFactory  threadFactoty)
 * @author Administrator
 *
 */
public class MyExecutorService {

	public static void main(String[] args) {
//		fixed();
//		single();
		cached();
	}
	/**
	 * 固定数量线程池
	 */
	public static void fixed(){
		ExecutorService exe = Executors.newFixedThreadPool(10,new CustomerThreadFactory("Thread-"));
		for(int i=0;i<=99;i++){
//			Thread thread = Thread.currentThread();
//			exe.execute(thread);
			exe.execute(()->{
				Thread thread = Thread.currentThread();
				System.out.println("当前线程名称："+thread.getName()+"，优先级："+thread.getPriority());
			});
		}
		exe.shutdown();
	}
	/**
	 * 单例线程池
	 */
	public static void single(){
		ExecutorService sing = Executors.newSingleThreadExecutor(new CustomerThreadFactory("Thread-"));
		for(int i=0;i<=99;i++){
			sing.execute(()->{
				Thread thread = Thread.currentThread();
				System.out.println("当前线程名称："+thread.getName()+"，优先级："+thread.getPriority());
			});
		}
		sing.shutdown();
	}
	/**
	 * 缓存线程池
	 */
	public static void cached(){
		ExecutorService cache = Executors.newCachedThreadPool(new CustomerThreadFactory("Thread-"));
		for(int i=0;i<=99;i++){
			cache.execute(()->{
				Thread thread = Thread.currentThread();
				System.out.println("当前线程名称："+thread.getName()+"，优先级："+thread.getPriority());
			});
		}
		cache.shutdown();
	}
}

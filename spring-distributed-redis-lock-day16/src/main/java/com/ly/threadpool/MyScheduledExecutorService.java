package com.ly.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.SingleSelectionModel;

/**
 * https://www.jianshu.com/p/9beab78a3afe
 * 
 * ScheduledThreadPoolExecutor适用于需要多个后台线程执行周期任务，
 * 	同时为了满足资源管理的需求而需要限制后台线程的数量的应用场景
 * SingleThreadScheduledExecutor适用于需要单个后台线程执行周期任务，
 * 	同时需要保证顺序地执行各个任务的应用场景
 * @author Administrator
 *
 */
public class MyScheduledExecutorService {

	public static void main(String[] args) {
//		scheduled();
		single();
	}
	/**
	 * 定时线程池
	 * 	多次延时执行scheduleWithFixedDelay(Runnable command,long initialDelay,long delay,TimeUnit unit);
		command：提交Runnable任务
		initialDelay：初始延迟时间
		delay:表示延迟时间 第一个任务结束到第二个任务开始的时间间隔
		unit:时间级别
		一次延时执行scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);
		command：提交Runnable任务
		initialDelay：初始延迟时间
		period：表示连个任务连续执行的时间周期，第一个任务开始到第二个任务的开始，包含了任务的执行时间
		unit：时间级别
		该方法在initialDelay时间后开始周期性的按period时间间隔执行任务
	 */
	public static void scheduled(){
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10,new CustomerThreadFactory("Thread-"));
		Runnable able = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				System.out.println(sim.format(new Date()));
			}
		};
		ses.scheduleWithFixedDelay(able, 10, 5, TimeUnit.SECONDS);
	}
	/**
	 * 单例延时线程池
	 */
	public static void single(){
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor(new CustomerThreadFactory("Thread-"));
		Runnable able = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				System.out.println(sim.format(new Date()));
			}
		};
		//由于是单例线程池，所以核心线程为1，线程池中只有一个重用线程
		ses.scheduleWithFixedDelay(able, 10, 5, TimeUnit.SECONDS);
	}
}

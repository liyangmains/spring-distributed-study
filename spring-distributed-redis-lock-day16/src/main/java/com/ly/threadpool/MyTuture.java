package com.ly.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 获取线程池结果集
 * https://www.jianshu.com/p/b530c891be61
 * @author Administrator
 *
 */
public class MyTuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		threadFuture();
//		futureTask();
	}
	/**
	 * callable提交给线程池获得结果
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void threadFuture() throws InterruptedException, ExecutionException{
		LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<Runnable>();
		//连接数，最大连接数，空闲时间，时间策略，无界队列
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, queue);
		Future<String> future = tpe.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				TimeUnit.SECONDS.sleep(5);
				return "Future";
			}
		});
			System.out.println(future.get());
	}
	/**
	 * callable提交给futureTask获得结果
	 * 当FutureTask处于未启动或已启动状态时，执行FutureTask.get()方法将导致调用线程阻塞；
	        当FutureTask处于已完成状态时，执行FutureTask.get()方法将导致调用线程立即返回结果或抛出异常。
	        当FutureTask处于未启动状态时，执行FutureTask.cancel()方法将导致此任务永远不会被执行；
                    当FutureTask处于已启动状态时，执行FutureTask.cancel（true）方法将以中断执行此任务线程的方式来试图停止任务；
                    当FutureTask处于已启动状态时，执行FutureTask.cancel（false）方法将不会对正在执行此任务的线程产生影响（让正在执行的任务运行完成）；当FutureTask处于已完成状态时，执行FutureTask.cancel（…）方法将返回false。
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void futureTask() throws InterruptedException, ExecutionException{
		FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>(){
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				TimeUnit.SECONDS.sleep(5);
				return "Future";
			}});
		futureTask.run();
		System.out.println(futureTask.get());
	}
}

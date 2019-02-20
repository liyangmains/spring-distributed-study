package com.ly.zookeeper.curator.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;

import com.ly.zookeeper.curator.CuratorApiOperatorDemo;

public class CuratorApiOperatorLockDemo {

	private static CuratorFramework curatorFramework;
	private static CountDownLatch countDownLatch = new CountDownLatch(10);
	
	public static void main(String [] args){
		curatorFramework = CuratorApiOperatorDemo.getInstance();
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println("----------------共享锁打开---------------");
		for(int i=0 ; i<=9 ; i++){
			service.submit(new CuratorLockUtil("用户"+(i+1), curatorFramework, countDownLatch, "/LOCKS"));
		}
		service.shutdown();
		try {
			countDownLatch.await();
			System.out.println("----------------处理完毕---------------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		curatorFramework.close();
		System.out.println("----------------共享锁关闭---------------");
	}
	
}

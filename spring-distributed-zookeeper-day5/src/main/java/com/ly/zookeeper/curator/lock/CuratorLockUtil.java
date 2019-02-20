package com.ly.zookeeper.curator.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

public class CuratorLockUtil implements Runnable{

	private String clientName;
	private CuratorFramework curatorFramework;
	private CountDownLatch countDownLatch;
	private String path;
	
	public CuratorLockUtil(String clientName, CuratorFramework curatorFramework, CountDownLatch countDownLatch,String path) {
		super();
		this.clientName = clientName;
		this.curatorFramework = curatorFramework;
		this.countDownLatch = countDownLatch;
		this.path = path;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		InterProcessMutex lock = new InterProcessMutex(curatorFramework, path);
		try {
			if(lock.acquire(120, TimeUnit.SECONDS)){
				System.out.println(clientName+"正在获得锁");
				System.out.println(clientName+"正在处理资源");
				TimeUnit.SECONDS.sleep(2);
				System.out.println(clientName+"资源处理完毕");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(lock != null){
				try {
					lock.release();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(clientName+"释放锁");
				countDownLatch.countDown();
			}
		}
	}

}

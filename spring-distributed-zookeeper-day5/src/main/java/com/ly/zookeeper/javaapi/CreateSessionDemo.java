package com.ly.zookeeper.javaapi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class CreateSessionDemo {
	
	public final static String CONNECTSTRING = "192.168.20.188:2181,192.168.190:2181";
	public static CountDownLatch countDownLatch = new CountDownLatch(1);

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zookeeper = new ZooKeeper(CONNECTSTRING, 5000, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				// TODO Auto-generated method stub
				if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
					countDownLatch.countDown();
				}
			}
		});
		countDownLatch.await();
		System.out.println(zookeeper.getState());
		String info = zookeeper.create("/bbb","张飞".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		System.out.println(info);
	}
}

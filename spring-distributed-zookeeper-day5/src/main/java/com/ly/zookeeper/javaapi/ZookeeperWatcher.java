package com.ly.zookeeper.javaapi;


import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperWatcher implements Watcher{

//	public CountDownLatch countDownLatch = new CountDownLatch(1);
	
	public ZooKeeper zookeeper;
	public CountDownLatch countDownLatch;
	public Stat stat;
	
	public ZookeeperWatcher(ZooKeeper zookeeper,Stat stat,CountDownLatch countDownLatch) {
		// TODO Auto-generated constructor stub
		this.zookeeper = zookeeper;
		this.stat = stat;
		this.countDownLatch = countDownLatch;
	}
	@Override
	public void process(WatchedEvent watchedEvent) {
		// TODO Auto-generated method stub
		if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
			if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
				countDownLatch.countDown();
				System.out.println(watchedEvent.getState() + "-->" + watchedEvent.getType());
			}else if(Event.EventType.NodeDataChanged == watchedEvent.getType()){//修改节点监听
				try {
					System.out.println("修改节点路径：" + watchedEvent.getPath() + "-->数据:" + zookeeper.getData(watchedEvent.getPath(), true, stat));
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(Event.EventType.NodeChildrenChanged == watchedEvent.getType()){//创建子节点，修改子节点，删除子节点监听
				try {
					System.out.println("修改子节点路径：" + watchedEvent.getPath() + "-->数据:" + zookeeper.getData(watchedEvent.getPath(), true, stat));
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(Event.EventType.NodeCreated == watchedEvent.getType()){//创建节点监听
				try {
					System.out.println("创建节点路径：" + watchedEvent.getPath() + "-->数据:" + zookeeper.getData(watchedEvent.getPath(), true, stat));
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(Event.EventType.NodeDeleted == watchedEvent.getType()){//删除节点监听
				System.out.println("删除节点路径：" + watchedEvent.getPath());
			}
		}
	}

}

package com.ly.zookeeper.javaapi;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

public class ApiOperatorDemo{

	public final static String CONNECTSTRING = "192.168.20.188:2181,192.168.190:2181";
	public static CountDownLatch countDownLatch = new CountDownLatch(1);
	public static ZooKeeper zookeeper = null;
	public static Stat stat = new Stat();
	
	public static ZooKeeper getConnect(){
		try {
			zookeeper = new ZooKeeper(CONNECTSTRING,5000,new ZookeeperWatcher(zookeeper, stat,countDownLatch));
			countDownLatch.await();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return zookeeper;
	}
	public static String CreateDataChanged(ZooKeeper zookeeper,String path,byte[] data,List<ACL> acl,CreateMode createMode){//添加节点
		String result = null;
		try {
			result = zookeeper.create(path, data, acl, createMode);
			TimeUnit.SECONDS.sleep(2);//需要线程等待两秒，等待watche监听响应
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static Stat UpdateDataChanged(ZooKeeper zookeeper,String path,byte[] data,Integer version){
		Stat stat = null;
		try {
			stat = zookeeper.setData(path, data, version);
			TimeUnit.SECONDS.sleep(2);//需要线程等待两秒，等待watche监听响应
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stat;
	}
	public static void DeleteDataChanged(ZooKeeper zookeeper,String path,Integer version){
		try {
			zookeeper.delete(path, version);
			TimeUnit.SECONDS.sleep(1);//需要线程等待两秒，等待watche监听响应
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.ly.zookeeper.curator;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorApiOperatorDemo {

//	private final static String CONNECTSTRING = "192.168.20.188:2181,192.168.190:2181";
	private final static String CONNECTSTRING = "192.168.20.188:2181";
	
	public static CuratorFramework getInstance(){
		//第一种连接方式
		CuratorFramework curatorFramework = 
				CuratorFrameworkFactory.newClient(CONNECTSTRING, 5000, 5000,new ExponentialBackoffRetry(1000, 3));
		curatorFramework.start();
		//第二种连接方式 (namespace方法默认创建的节点都在当前指定的节点下)
		/*CuratorFramework curatorFramework1 = 
				CuratorFrameworkFactory.builder().connectString(CONNECTSTRING).connectionTimeoutMs(5000).sessionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3)).namespace("/curator").build();
		curatorFramework1.start();
		*/
		return curatorFramework;
	}
	public static void main(String[] args) {
		
		CuratorFramework curatorFramework = getInstance();
		/**
		 * 创建节点
		 * withMode(CreateMode.PERSISTENT)创建持久节点
		 */
		/*try {
			curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/curator/curator1", "吕布".getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/**
		 * 查询节点数据
		 */
		/*
		Stat stat = new Stat();
		try {
			byte[] data = curatorFramework.getData().storingStatIn(stat).forPath("/curator/curator1");
			System.out.println("节点数据："+new String(data)+"-->节点状态："+stat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/**
		 * 修改节点数据
		 */
		/*
		Stat stat = new Stat();
		try {
			curatorFramework.setData().forPath("/curator/curator1", "貂蝉".getBytes());
			byte[] data = curatorFramework.getData().storingStatIn(stat).forPath("/curator/curator1");
			System.out.println("节点数据："+new String(data)+"-->节点状态："+stat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/**
		 * 删除节点
		 */
		/*try {
			curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/**
		 * 异步创建节点请求
		 */
		/*
		ExecutorService service = Executors.newFixedThreadPool(1);//创建线程池
		CountDownLatch countDownLatch = new CountDownLatch(1);
		try {
			curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
				
				@Override
				public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
					// TODO Auto-generated method stub
					countDownLatch.countDown();
					System.out.println("创建节点成功-->线程名称："+Thread.currentThread().getName()+"事件返回编码："+event.getResultCode()+"事件返回名称："+event.getName()+",事件返回类型："+event.getType());
				}
			},service).forPath("/curator/curator1", "张飞".getBytes());
			System.out.println("请求成功，等待回调");
			countDownLatch.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.shutdown();
		*/
		/**
		 * curator独有事物操作
		 */
		/*
		try {
			Collection<CuratorTransactionResult> curatorTransactionResult = curatorFramework.inTransaction().create().forPath("/curator", "貂蝉".getBytes())
			.and().setData().forPath("/curator", "张飞".getBytes()).and().commit();
			for(CuratorTransactionResult result : curatorTransactionResult){
				System.out.println(result.getForPath()+"-->"+result.getType());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/**
		 * curator事件监听event
		 * NodeCache 监听节点的创建删除修改
		 * PathChildrenCache 监听子节点的创建删除修改
		 * TreeCache 监听节点与子节点的创建删除修改
		 * 缓存路径下所有的子节点的数据
		 */
		/*NodeCache cache = new NodeCache(curatorFramework, "/curator", false);
		try {
			cache.start(true);
			cache.getListenable().addListener(()-> System.out.println("节点发生变化,结果"+":"+new String(cache.getCurrentData().getData())));
			curatorFramework.setData().forPath("/curator", "赵云6".getBytes());
			System.in.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,"/curator",true);
			pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
			pathChildrenCache.getListenable()
			.addListener((curatorFramework1,pathChildrenCacheEvent)-> {
							switch(pathChildrenCacheEvent.getType()){
									case CHILD_ADDED:
										System.out.println("子节点添加");
										break;
									case CHILD_REMOVED:
										System.out.println("子节点删除");
										break;
									case CHILD_UPDATED:
										System.out.println("子节点更新");
										break;
									default:break;
									}
								}
							);
			curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/curator/curator1","小张".getBytes());
			TimeUnit.SECONDS.sleep(2);
			curatorFramework.setData().forPath("/curator/curator1", "赵小云".getBytes());
			TimeUnit.SECONDS.sleep(2);
			curatorFramework.delete().forPath("/curator/curator1");
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

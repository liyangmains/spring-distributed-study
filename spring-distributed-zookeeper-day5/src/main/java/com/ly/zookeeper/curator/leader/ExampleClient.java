package com.ly.zookeeper.curator.leader;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;


public class ExampleClient extends LeaderSelectorListenerAdapter implements Closeable{

	private LeaderSelector leaderSelector;
	private AtomicInteger atomicInteger = new AtomicInteger();
	private ClientUser user;
	public ExampleClient(CuratorFramework client,String path,ClientUser user) {
		// TODO Auto-generated constructor stub
		this.user = user;
		leaderSelector = new LeaderSelector(client, path, this);
		leaderSelector.autoRequeue();
	}
	@Override
	public void takeLeadership(CuratorFramework client) throws Exception {
		user.setClientName("我已经是leader了，我是老大");
		System.out.println(user);
		TimeUnit.SECONDS.sleep(2);
	}
	public void start() throws IOException{
	    leaderSelector.start();
	}
	public void close() throws IOException{
        leaderSelector.close();
	}

}

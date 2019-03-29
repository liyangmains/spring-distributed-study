package com.ly.zookeeper.curator.leader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.utils.CloseableUtils;

import com.ly.zookeeper.curator.CuratorApiOperatorDemo;

public class CuratorApiOperatorLeaderDemo {

//	private static List<CuratorFramework> clients= new ArrayList<>();
//	private static List<ExampleClient> examples= new ArrayList<>();
	
//	public static void main(String[] args) {
//		try {
//			for (int i = 0; i < 10 ; i++) {
//				CuratorFramework curatorFramework = CuratorApiOperatorDemo.getInstance();
//				ClientUser user = new ClientUser();
//				user.setClientId(i+1);
//				ExampleClient example = new ExampleClient(curatorFramework, "/LEADERS", user);
//				clients.add(curatorFramework);
//				examples.add(example);
//				example.start();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			for(ExampleClient example : examples){
//				CloseableUtils.closeQuietly(example);
//			}
//			for(CuratorFramework client : clients){
//				CloseableUtils.closeQuietly(client);
//			}
//		}
//	}
	private static String path = "/LEADERS";
	public static void main(String[] args) {
//		 leaderLatch();
		leaderSelector();
	 }
	/**
	 * Leader Latch用法
	 * 随机从候选着中选出一台作为leader，选中之后除非调用close()释放leadship，
	 * 否则其他的后选择无法成为leader。其中spark使用的就是这种方法
	 */
	public static void leaderLatch(){
		List<LeaderLatch> latchList = new ArrayList<>();
        List<CuratorFramework> clients = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                CuratorFramework client = CuratorApiOperatorDemo.getInstance();
                clients.add(client);

                final LeaderLatch leaderLatch = new LeaderLatch(client, path, "client#" + i);
                leaderLatch.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {
                        System.out.println(leaderLatch.getId() +  ":I am leader. I am doing jobs!");
                    }

                    @Override
                    public void notLeader() {
                        System.out.println(leaderLatch.getId() +  ":I am not leader. I will do nothing!");
                    }
                });
                latchList.add(leaderLatch);
                leaderLatch.start();
            }
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for(CuratorFramework client : clients){
                CloseableUtils.closeQuietly(client);
            }

            for(LeaderLatch leaderLatch : latchList){
                CloseableUtils.closeQuietly(leaderLatch);
            }
        }
	}
	/**
	 * Leader Selector
	 * 通过LeaderSelectorListener可以对领导权进行控制， 在适当的时候释放领导权，这样每个节点都有可能获得领导权.
	 * 而LeaderLatch则一直持有leadership， 除非调用close方法，否则它不会释放领导权
	 */
	public static void leaderSelector(){
		List<LeaderSelector> selectors = new ArrayList<>();
        List<CuratorFramework> clients = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                CuratorFramework client = CuratorApiOperatorDemo.getInstance();
                clients.add(client);

                final String name = "client#" + i;
                LeaderSelector leaderSelector = new LeaderSelector(client, path, new LeaderSelectorListener() {
                    @Override
                    public void takeLeadership(CuratorFramework client) throws Exception {

                        System.out.println(name + ":I am leader.");
                        Thread.sleep(2000);
                    }
                    @Override
                    public void stateChanged(CuratorFramework client, ConnectionState newState) {

                    }
                });

                leaderSelector.autoRequeue();
                leaderSelector.start();
                selectors.add(leaderSelector);

            }
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for(CuratorFramework client : clients){
                CloseableUtils.closeQuietly(client);
            }

            for(LeaderSelector selector : selectors){
                CloseableUtils.closeQuietly(selector);
            }

        }
	}
}

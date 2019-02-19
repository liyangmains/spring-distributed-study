package com.ly.zookeeper.zkclient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ZkclientApiOperatorDemo {
	
//	private final static String CONNECTSTRING = "192.168.20.188:2181,192.168.190:2181";
	private final static String CONNECTSTRING = "192.168.20.188:2181";
	
	public static ZkClient getInstace(){
		return new ZkClient(CONNECTSTRING,5000);
	}

	public static void main(String[] args){

		ZkClient zkClient = getInstace();
		System.out.println(zkClient);
		//获取/hello子节点
		List<String> childrens = zkClient.getChildren("/hello");
		System.out.println(childrens);
		//createPersistent可实现递归创建节点
		zkClient.createPersistent("/eee/ggg/fff", true);
		//修改节点value
		zkClient.writeData("/eee/ggg/fff", "吕布");
		zkClient.writeData("/eee/ggg/fff", "貂蝉");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取节点数据
		String data = zkClient.readData("/eee/ggg/fff");
		System.out.println(data);
		
		//监听子节点状态的变化
		zkClient.subscribeChildChanges("/eee/ggg/fff", new IZkChildListener() {
            public void handleChildChange(String parentPath, List<String> list) throws Exception {
                String s="";
                for(String str:list){
                    s+=str+",";
                }
                System.out.println("子节点变化："+s);
            }
        });
		//监听节点数据变化
		zkClient.subscribeDataChanges("/eee/ggg/fff", new IZkDataListener() {
		    public void handleDataChange(String dataPath, Object data) throws Exception {
		                System.out.println("节点数据变化："+dataPath+"-数据："+data.toString());
		          }

		    public void handleDataDeleted(String dataPath) throws Exception {
		                System.out.println("节点数据删除："+dataPath);
		          }
		});
		//监听节点的连接和状态变化
		zkClient.subscribeStateChanges(new IZkStateListener() {

			@Override
			public void handleStateChanged(KeeperState state) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("节点连接及状态变化："+state.name());
			}

			@Override
			public void handleNewSession() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("节点Session变化。。。");
			}

			@Override
			public void handleSessionEstablishmentError(Throwable error) throws Exception {
				// TODO Auto-generated method stub
				
			}
		});
		//递归删除节点
		zkClient.deleteRecursive("/eee");
	}
}

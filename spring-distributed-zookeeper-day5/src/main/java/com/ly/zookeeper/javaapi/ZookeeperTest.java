package com.ly.zookeeper.javaapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

public class ZookeeperTest {
	public static void main(String[] args) {
		/*ZooKeeper zookeeper = ApiOperatorDemo.getConnect();
		System.out.println(zookeeper);
		String path = "/fff";
		String a = ApiOperatorDemo.CreateDataChanged(zookeeper,path, "张飞".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println(a);
		ApiOperatorDemo.UpdateDataChanged(zookeeper,path, "貂蝉2".getBytes(), -1);
		ApiOperatorDemo.DeleteDataChanged(zookeeper, "/ddd", -1);*/
		
		/*权限配置scheme权限包含 
			ip:指定ip访问
			digest:用户名密码访问
			world:所有用户都可访问
			super:admin超级用户访问，可以对zookeeper节点进行操作
		*/
		//zookeeper节点单个权限配置
		/*ZooKeeper zookeeper = ApiOperatorDemo.getConnect();
		zookeeper.addAuthInfo("digest", "root:123".getBytes());
		String path = "/fff";
		String a = ApiOperatorDemo.CreateDataChanged(zookeeper,path, "张飞".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
		ZooKeeper zookeeper2 = ApiOperatorDemo.getConnect();
		ApiOperatorDemo.DeleteDataChanged(zookeeper2, path, -1);*/
		//zookeeper节点多个权限配置
		ZooKeeper zookeeper = ApiOperatorDemo.getConnect();
		List<ACL> acls = new ArrayList<ACL>();
		ACL acl = new ACL(ZooDefs.Perms.CREATE,new Id("digest", "root:123"));
		ACL acl2 = new ACL(ZooDefs.Perms.CREATE,new Id("ip", "192.168.20.188"));
		acls.add(acl);
		acls.add(acl2);
		String path = "/fff";
		String a = ApiOperatorDemo.CreateDataChanged(zookeeper,path, "张飞".getBytes(), acls, CreateMode.EPHEMERAL);
	}
}

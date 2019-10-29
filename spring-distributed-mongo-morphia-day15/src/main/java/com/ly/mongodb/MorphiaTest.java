package com.ly.mongodb;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.ly.mongodb.entity.User;
import com.ly.mongodb.entity.test;
import com.mongodb.MongoClient;

public class MorphiaTest {
	public static void main(String[] args) {
		Morphia morphia = new Morphia();
		
		Datastore ds = morphia.createDatastore(new MongoClient("192.168.16.191", 27017), "test");
		
		test t = new test();
		t.setName("貂蝉");
		t.setSite("222222");
		
//		添加一个对象信息
		/*Key<test> key = ds.save(t);
		System.out.println(key.toString());*/
//		查询信息
		/*Query<test> query = ds.createQuery(test.class);
		
		
		List<test> list = query.asList();
		if(list != null && list.size() > 0){
			for(test test:list){
				System.out.println(test);
			}
		}*/
		User user = new User("吕布",18,"男");
		ds.save(user);
		Query<User> query = ds.createQuery(User.class);
		List<User> list = query.asList();
		if(list != null && list.size() > 0){
			for(User u:list){
				System.out.println(u);
			}
		}
		
	}
}

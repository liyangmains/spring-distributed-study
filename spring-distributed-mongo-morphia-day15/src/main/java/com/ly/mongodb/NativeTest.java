package com.ly.mongodb;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class NativeTest {
	
	public static void main(String[] args) {
		
		Mongo mongo = new Mongo("192.168.16.191",27017);
//		建立一个数据连接
		DB db = new DB(mongo,"test");
//		获取数据collection
		System.out.println(db.getCollectionNames());
//		游标
		DBCursor cursor = db.getCollection("test").find();
		for(DBObject dbObject : cursor){
			System.out.println(dbObject);
		}
	}

}

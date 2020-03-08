package com.ly.jedis;

import java.util.List;
import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class JedisLock {
	
	/**
	 * 
	 * @param key redis中的key值
	 * @param timeOut 超时时间
	 * @return
	 */
	public String getLock(String key, int timeOut){
		Jedis jedis = null;
		try {
			jedis = JedisManager.getJedis();
			String value = UUID.randomUUID().toString();
			long end = System.currentTimeMillis()+timeOut;
			//使线程阻塞，但是添加超时时间防止客户端没有抢到锁无法中断
			while(System.currentTimeMillis() < end){
				//如果setnx()数据添加成功,表示占用成功
				if(jedis.setnx(key, value) == 1){
					//设置key在redis中的时效
					jedis.expire(key, timeOut);
					//锁设置成功返回占用线程值，也可以是线程id
					return value;
				}
				if(jedis.ttl(key) == -1){//检查过期时间如果失效重新设置
					jedis.expire(key, timeOut);
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		
		return null;
	}
	/**
	 * 
	 * @param key redis中的key值
	 * @param value 占用锁时返回的id值
	 * @return
	 */
	public boolean releaseLock(String key,String value){
		Jedis jedis = null;
		try {
			jedis = JedisManager.getJedis();
			while(true){
				//监听key对应值的变化,如果发生变化将不会执行下列的multi开启事物,del删除,exec提交事物
				jedis.watch(key);
				if(value.equals(jedis.get(key))){
					Transaction transaction = jedis.multi();
					transaction.del(key);
					List<Object> list = transaction.exec();
					if(list == null){
						//重新进入循环
						continue;
					}
					return true;
				}
				//取消监听
				jedis.unwatch();
				//跳出循环
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return false;
	}
}

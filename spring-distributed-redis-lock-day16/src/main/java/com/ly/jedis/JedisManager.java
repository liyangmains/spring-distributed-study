package com.ly.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisManager {
	private static JedisPool jedisPool;
	
	static{
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(20);
		jedisPoolConfig.setMaxIdle(10);
		jedisPool = new JedisPool(jedisPoolConfig, "192.168.43.241", 6379);
	}
	public static Jedis getJedis() throws Exception{
		if(null != jedisPool){
			return jedisPool.getResource();
		}
		throw new Exception("没有获取到连接");
	}
}

package com.ly.jedis;

public class JedisLockTest {

	public static void main(String[] args) {
		
		String key = "Lock_test";
		int timeOut = 10000;
		for(int i = 0 ; i <= 9 ; i++){
			JedisLock jedisLock = new JedisLock();
			String value = jedisLock.getLock(key, timeOut);
			System.out.println("线程："+value);
			jedisLock.releaseLock(key, value);
		}
	}
}

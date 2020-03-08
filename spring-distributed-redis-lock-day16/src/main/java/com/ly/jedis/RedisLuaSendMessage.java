package com.ly.jedis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisLuaSendMessage {

	public static void main(String[] args) throws Exception {
		Jedis jedis = JedisManager.getJedis();
		//KEYS[1]表示被传入的手机号
		//ARGV[1]表示设置数据保存时间（手机在当前时间内，单位 秒）
		//ARGV[2]表示在设置的时间内规定次数（规定时间内手机发送短信的最大次数）
		String lua = "local num=redis.call('incr',KEYS[1])\n"
				+ "if tonumber(num)==1 then\n"
				+ "  redis.call('expire',KEYS[1],ARGV[1])\n"
				+ "  return 1\n"
				+ "elseif tonumber(num)>tonumber(ARGV[2]) then\n"
				+ "  return 0\n"
				+ "else\n"
				+ "  return 1\n"
				+ "end";
		List<String> keys = new ArrayList();
		keys.add("16675325711");
		List<String> argvs = new ArrayList();
		argvs.add("600");
		argvs.add("10");
		String sha = jedis.scriptLoad(lua);
		for(int i = 0 ; i <= 10 ; i++){
			Object obj = jedis.evalsha(sha,keys,argvs);
			if("1".equals(obj.toString())){
				System.out.println("短信发送成功");
			}else if ("0".equals(obj.toString())){
				System.out.println("短信发送失败,请十分钟之后发送");
			}
		}
	}
}

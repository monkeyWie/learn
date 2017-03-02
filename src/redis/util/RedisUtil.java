package redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {
	private static final JedisPool pool;
	
	static{
		pool = new JedisPool("192.168.252.129",6379);
	}
	
	public static Jedis getClient(){
		return pool.getResource();
	}
}

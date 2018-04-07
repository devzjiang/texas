package com.tayee.game.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TayeeRedis extends JedisPool{
		
	public TayeeRedis(JedisPoolConfig config ,String ip, int port ,int timeout,String passwd){  
		super(config, ip, port,timeout,passwd);
    } 
	
	public TayeeRedis(String ip,int port){  
		super(ip, port);
    } 
	
	/**获取一个JEDIS*/
	public Jedis getJedis(){  
        return getResource();  
	}
}
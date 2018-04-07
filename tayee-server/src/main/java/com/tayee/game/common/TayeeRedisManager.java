package com.tayee.game.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public final class TayeeRedisManager {
	
	private TayeeRedis _dbredis;
	
	public void init(){
		String ip = TayeeServerConfig.dbHost;
		int port = TayeeServerConfig.dbPort;
		String auth = TayeeServerConfig.dbAuth;
		if(""==auth){
			_dbredis =new TayeeRedis(ip, port);
		}else{
			_dbredis =new TayeeRedis(new JedisPoolConfig(), ip, port,2000,auth);
		}
		System.out.println("init success,get keys:"+getJedis().keys("*").size());	
	}
	
	public Jedis getJedis(){
		if(null==_dbredis){
			return null;
		}
		return _dbredis.getResource();
	}
		
	private TayeeRedisManager(){}
	
	private static final class Manager{
		static final TayeeRedisManager manager =new TayeeRedisManager();
	}
	
	public static final TayeeRedisManager getInstance(){
		return Manager.manager;
	}
}

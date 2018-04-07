package com.tayee.game.common;

import redis.clients.jedis.Jedis;

public interface TayeeSyncTask extends Runnable{
	
	Jedis getJedis();
	
	void setJedis();
	
	String getTaskName(); 
	
	void syncHandler();
	
}

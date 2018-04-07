package com.tayee.game.common;

import redis.clients.jedis.Jedis;

public final class TayeeNoticeSender {
	
	public static void sendChannel(String channel,String message)
	{
		Jedis jedis = TayeeRedisManager.getInstance().getJedis();
		if(null==jedis){
			System.err.println("TayeeRedisManager get jedis is null!");
			return;
		}
		jedis.publish(channel,message);
//		TayeeRedisManager.getInstance().returnJedis(jedis);
	}
	
	public static void sendChannels(String message,String...channels){
		for (String channel : channels) {
			sendChannel(channel,message);
		}
	}
	
}

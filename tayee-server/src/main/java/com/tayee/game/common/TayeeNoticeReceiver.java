package com.tayee.game.common;

import redis.clients.jedis.Jedis;

public class TayeeNoticeReceiver extends TayeePubSub{

	public void subscribe(String...channels){
		
		if(null==channels){
			System.err.println("channels is null.");
			return;
		}		
		
		Jedis jedis = TayeeRedisManager.getInstance().getJedis();
		jedis.subscribe(this,channels);
	}
	
	@Override
	protected void pubsubEvent(String channel, int number) {
		// TODO Auto-generated method stub
        System.out.println("pubsubEvent:"+channel+" number:"+number);  
	}

	@Override
	protected void messageHandler(String channel, String msg) {
		// TODO Auto-generated method stub
        System.out.println("messageHandler:"+channel+" msg:"+msg);  
	}

}

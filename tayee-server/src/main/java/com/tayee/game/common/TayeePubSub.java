package com.tayee.game.common;

import redis.clients.jedis.JedisPubSub;

public abstract class TayeePubSub extends JedisPubSub {
	
	@Override 
    public void onSubscribe(String channel, int number) { 
        pubsubEvent(channel, number);
    }  
	
	@Override 
    public void onMessage(String channel, String msg) {
		messageHandler(channel, msg);
    } 
	
	protected abstract void pubsubEvent(String channel,int number);
		 
	protected abstract void messageHandler(String channel,String msg);
}

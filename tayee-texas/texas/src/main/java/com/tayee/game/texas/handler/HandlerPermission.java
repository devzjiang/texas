package com.tayee.game.texas.handler;

import com.tayee.game.common.TayeeHandler;
import com.tayee.game.common.TayeeRedisManager;
import com.tayee.game.common.TayeeRequest;

import io.netty.channel.ChannelHandlerContext;
import redis.clients.jedis.Jedis;

public abstract class HandlerPermission implements TayeeHandler {

	public boolean permission(TayeeRequest request){
		String token =request.getToken();
		Jedis jedis =TayeeRedisManager.getInstance().getJedis();
		jedis.get(token);
		//check token
		//....
		jedis.close();
		return true;
	}

	public abstract void execute(TayeeRequest request);

	public void error(TayeeRequest request) {
		// TODO Auto-generated method stub
		ChannelHandlerContext ctx =request.getSession();
		String address =ctx.channel().remoteAddress().toString();
		ctx.close();
		System.err.println(address+" no permission.");
	}

}

package com.tayee.game.common;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public abstract class TayeeHandlerAdapter extends ChannelHandlerAdapter {
	
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		closeSession(ctx);
	}
	
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		closeSession(ctx);
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		closeSession(ctx);
	}
	
	public abstract void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception;
	
	public abstract void closeSession(ChannelHandlerContext ctx) ;

}

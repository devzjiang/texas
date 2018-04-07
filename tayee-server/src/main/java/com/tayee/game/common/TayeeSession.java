package com.tayee.game.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class TayeeSession {

	public final static AttributeKey<Integer> PID = AttributeKey.valueOf("PID");
	public final static AttributeKey<String>  ZONE = AttributeKey.valueOf("ZONE");
	public final static AttributeKey<String>  GROUP = AttributeKey.valueOf("GROUP");
	public final static AttributeKey<String>  MATCH = AttributeKey.valueOf("MATCH");
 	
	public final static void setSessionPid(ChannelHandlerContext ctx, int playerId){
		ctx.attr(PID).set(playerId);
	}
	
	public final static int getSessionPid(ChannelHandlerContext ctx){
		return ctx.attr(PID).get();
	}

	public final static void setPZone(ChannelHandlerContext ctx,String zone){
		ctx.attr(ZONE).set(zone);
	}
	
	public final static String getPZone(ChannelHandlerContext ctx){
		return ctx.attr(ZONE).get();
	}
	
	public final static void setPGroup(ChannelHandlerContext ctx,String group){
		ctx.attr(GROUP).set(group);
	}
	
	public final static String getPGroup(ChannelHandlerContext ctx){
		return ctx.attr(GROUP).get();
	}

	public final static void setPMatch(ChannelHandlerContext ctx,String match){
		ctx.attr(MATCH).set(match);
	}
	
	public final static String getPMatch(ChannelHandlerContext ctx){
		return ctx.attr(MATCH).get();
	}
	
	
	private ChannelHandlerContext  _channel;
	
	public TayeeSession(ChannelHandlerContext channel){
		_channel = channel;
	}
	
	public ChannelHandlerContext getChannel(){
		return this._channel;
	}
	
	public void send(Object msg){
		_channel.writeAndFlush(msg);
	}
	
	public void sendAndClose(Object msg){
	    _channel.writeAndFlush(msg);
		_channel.close();
	}
	
	public void close(){
		if(null!=_channel){
			_channel.close();
		}
	}
	
}

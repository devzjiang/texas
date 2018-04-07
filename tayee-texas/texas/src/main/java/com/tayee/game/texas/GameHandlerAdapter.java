package com.tayee.game.texas;

import com.tayee.game.common.TayeeHandlerAdapter;
import com.tayee.game.common.TayeeRequest;
import com.tayee.game.common.TayeeWorkerManager;
import com.tayee.game.texas.GameProtoBuf.MsgCmd;
import com.tayee.game.texas.GameProtoBuf.MsgMk;
import com.tayee.game.texas.GameProtoBuf.MsgRequest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class GameHandlerAdapter extends TayeeHandlerAdapter {
	
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("server channelInactive "+ctx.hashCode());
		closeSession(ctx);
	}
	
	public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("serverchannelRead " + ctx.hashCode());
		MsgRequest msg = (MsgRequest) obj;
		if(isHeartBeat(msg)){
			System.out.println("heart beat. session code:"+ctx.hashCode());
		}else{
			TayeeWorkerManager.getInstance().addGameRequest(
				new TayeeRequest(ctx,msg.getToken(),msg.getMk(),msg.getCmd(),msg.getParam()));
		}
	}
	
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("server close "+ctx.hashCode());
		closeSession(ctx);
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("exceptionCaught "+ctx.hashCode());	
		cause.printStackTrace();
		closeSession(ctx);
	}
	
	//是否心跳请求
	public boolean isHeartBeat(MsgRequest msg){
		return (msg.getMk()==MsgMk.MK_PLAYER_VALUE && msg.getCmd()==MsgCmd.CMD_HEART_BEAT_VALUE);
	}
	
	//断开连接关闭
	public void closeSession(ChannelHandlerContext ctx){

//		int playerId =TayeeSession.getSessionPid(ctx);
//		System.out.println("close player id:"+playerId);
//		String zone =TayeeSession.getPZone(ctx);
//		System.out.println("close player zone:"+zone);
//		String group =TayeeSession.getPGroup(ctx);
//		System.out.println("close player gourp:"+group);
//		GameZoneManager.getInstance().exitGame(zone,group,"",playerId);
		
	}

}

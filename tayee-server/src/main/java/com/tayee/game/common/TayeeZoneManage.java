package com.tayee.game.common;

import java.util.Collection;

import io.netty.channel.ChannelHandlerContext;

public interface TayeeZoneManage {

	void load();

	Collection<TayeeZone> getAllGameZone();
	
	TayeeZone getGameZone(String zoneName);
	
	TayeeGroup getGameGroup(String zoneName,String groupName);
	
	TayeeMatch getGameMatch(String zoneName,String groupName,String matchName);
	
	TayeePlayer getGamePlayer(String zoneName,String groupName,String matchName,int playerId);
	
	TayeePlayer getGamePlayer(int playerId);
	
	TayeePlayer addGamePlayer(TayeePlayer player);
	
	TayeePlayer enterGame(ChannelHandlerContext session,int playerId);
	
	void exitGame(int playerId);
	
	void exitGame(String zoneName,String groupName,String matchName,int playerId);

}

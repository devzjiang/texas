package com.tayee.game.common;

import java.util.Collection;

/**比赛数据管理接口
 * @param <T>*/
public interface TayeeMatch extends Runnable{
	
	  <T extends TayeePlayer> Collection<T> getAllPlayer();
	
	  <T extends TayeePlayer> T getPlayer(int playerId);

	  <T extends TayeePlayer> T delPlayer(int playerId);
	
	  <T extends TayeePlayer> TayeeMatch setPlayer(T player);
		
	 TayeeMatch sendMatch(Object msg);
	
	 String getZoneName();
	
	 String getGroupName();
	  
	 String getMatchName();
}

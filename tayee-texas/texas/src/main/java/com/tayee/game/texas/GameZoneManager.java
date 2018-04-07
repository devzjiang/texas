package com.tayee.game.texas;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.tayee.game.common.TayeeGroup;
import com.tayee.game.common.TayeeMatch;
import com.tayee.game.common.TayeePlayer;
import com.tayee.game.common.TayeeZone;
import com.tayee.game.common.TayeeZoneManage;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author zjiang
 * 
 *
 */
public class GameZoneManager extends ScheduledThreadPoolExecutor implements TayeeZoneManage {
	
	/**大厅游戏玩家*/
	private ConcurrentHashMap<Integer,TayeePlayer> _guests;
	/**进入房间或者比赛玩家*/
	private ConcurrentHashMap<String,TayeeZone> _zones; 
	
	public void load(){
		_guests = new ConcurrentHashMap<Integer,TayeePlayer>();
		_zones = new ConcurrentHashMap<String,TayeeZone>();
		_zones.put("gameZone1",new GameZoneInfo("gameZone1",1));
		
		//比赛场次进入线程执行逻辑
		Collection<TayeeZone> zones = getAllGameZone();
		for (TayeeZone tayeeZone : zones) {
			Collection<TayeeGroup> groups = tayeeZone.getAllGroup();
			for (TayeeGroup tayeeGroup : groups) {
				Collection<TayeeMatch> matchs = tayeeGroup.getAllMatch();
				for (TayeeMatch match : matchs) {
					 execute(match);
				}
			}
		}
		
	}
	
	
	public Collection<TayeeZone> getAllGameZone(){
		return _zones.values();
	}
	
	public TayeeZone getGameZone(String zoneName){
		if(""==zoneName || null == _zones){
			return null;
		}
		return _zones.get(zoneName);
	}
	
	public TayeeGroup getGameGroup(String zoneName,String groupName){
		TayeeZone zone = getGameZone(zoneName);
		if(""==groupName || null ==zone){
			return null;
		}
		return zone.getGroup(groupName);
	}
	
	public TayeeMatch getGameMatch(String zoneName, String groupName, String matchName) {
		return getGameGroup(zoneName, groupName).getMatch(matchName);
	}
	
	public TayeePlayer getGamePlayer(String zoneName, String groupName, String matchName, int playerId) {
		TayeeMatch match = getGameGroup(zoneName, groupName).getMatch(matchName);
		if(null == match || 0==playerId){
			return null;
		}
		return match.getPlayer(playerId);
	}
	
	public TayeePlayer getGamePlayer(int playerId) {
		return _guests.get(playerId);
	}
	
	public TayeePlayer delGamePlayer(int playerId){
		return _guests.remove(playerId);
	}
	
	public TayeePlayer addGamePlayer(TayeePlayer player){
		return _guests.put(player.getPlayerId(), player);
	}
	
	/**正常进入游戏*/
	public TayeePlayer enterGame(ChannelHandlerContext session,int playerId){
		return addGamePlayer(new GamePlayer(session,playerId).loadData());
	}
	
	/**玩家退出游戏*/
	public void exitGame(int playerId) {
		delGamePlayer(playerId).saveData().closeSession();
	}
	
	/** 
	 * 玩家退出游戏
 	 * 1.将玩家踢出用户组(保留位置信息)
	 * 2.保存玩家数据到数据库
	 * 3.关闭玩家会话通信连接
	 */
	public void exitGame(String zoneName,String groupName,String matchName,int playerId){
		getGameMatch(zoneName,groupName,matchName).delPlayer(playerId).saveData().closeSession();
	}
	
	/**进入游戏并创建游戏角色*/
	public TayeePlayer newPlayerEnterGame(ChannelHandlerContext session,String attrName,int playerId){
		return addGamePlayer(new GamePlayer(session, playerId).setPlayerAttr("attrName",attrName).saveData());
	}
	
	
	private GameZoneManager(){
		super(10);
	}
	
	private static final class Manager{
		private static final GameZoneManager manager =new GameZoneManager();
	}
	
	public static final GameZoneManager getInstance(){
		return Manager.manager;
	}
	
}

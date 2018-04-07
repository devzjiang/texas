//package com.tayee.game.common;
//
//import java.util.Collection;
//
//import com.tayee.game.common.example.GameZoneManager;
//
//public final class TayeeMessageManager {
//	
//	public void init(){
//		
//	}
//	
//	public void playerMsg(Object msg,String zoneName,String groupName,int playerId){
//		GameZoneManager.getInstance().getGamePlayer(zoneName, groupName, playerId).send(msg);
//	}
//	
//	public void groupMsg(Object msg,String zoneName,String groupName){
//		GameZoneManager.getInstance().getGameGroup(zoneName, groupName).sendGroup(msg);
//	}
//	
//	public void zoneMsg(Object msg,String zoneName){
//		GameZoneManager.getInstance().getGameZone(zoneName).sendZone(msg);
//	}
//	
//	public void worldMsg(Object msg){
//		Collection<TayeeZone> zones = GameZoneManager.getInstance().getAllGameZone();
//		for (TayeeZone gameZone : zones) {
//			gameZone.sendZone(msg);
//		}
//	}
//	
//	private TayeeMessageManager(){}
//	
//	private static final class Manager{
//		private static final TayeeMessageManager manager = new TayeeMessageManager();
//	}
//	
//	public static final TayeeMessageManager getInstance(){
//		return Manager.manager;
//	}
//}

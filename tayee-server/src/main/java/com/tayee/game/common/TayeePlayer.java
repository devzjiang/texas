package com.tayee.game.common;

public interface TayeePlayer { 
	
	/**获取玩家基础属性*/
	public <T> T getPlayerAttr(String attr);
	
	/**设置玩家基础属性*/
	public <T> TayeePlayer setPlayerAttr(String attr,T value);
	
	/**获取玩家buff状态*/
	public <T> T getPlayerBuffer(String attr);
	
	/**设置玩家buff状态*/
	public <T> TayeePlayer setPlayerBuffer(String attr,T value);
	
	/**获取玩家功能模块*/
	public <T> T getPlayerModule(String attr);
	
	/**设置玩家功能模块*/
	public <T> TayeePlayer setPlayerModule(String attr,T value);
	
	/**设置玩家位置属性*/
	public TayeePlayer setPlayserPos(String attr,String value);
	
	/**获取玩家位置属性*/
	public String getPlayerPos(String attr);
	
	/**保存玩家系统设置*/
	public <T> TayeePlayer setPlayerSetting(String attr, T value);
	
	/**获取玩家游戏设置*/
	public <T> T getPlayerSetting(String attr);
	
	/**根据玩家编号读取玩家数据*/
	public TayeePlayer loadData();
	
	/**保存玩家数据到数据库*/
	public TayeePlayer saveData();
	
	/**玩家发送数据*/
	public TayeePlayer sendData(Object data);
	
	/**玩家断开连接*/
	public void closeSession();
	
	/**获取玩家ID*/
	public int getPlayerId();


}

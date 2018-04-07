package com.tayee.game.texas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tayee.game.common.TayeePlayer;
import com.tayee.game.common.TayeePlayerConfig;
import com.tayee.game.common.TayeeRedisManager;
import com.tayee.game.common.TayeeSession;
import com.tayee.game.texas.GameProtoBuf.MsgResponse;

import io.netty.channel.ChannelHandlerContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

@SuppressWarnings("unchecked")
public class GamePlayer extends TayeeSession implements TayeePlayer,GamePlayerAction{ 
	
//	private byte [] lock = new byte[0];
	
	private final int _playerId;
	//玩家基础属性
	private final Map<String,Object> _attribute;
	//玩家buff状态
	private final Map<String,Object> _buffer;
	//玩家世界位置
	private final Map<String,Object> _position;
	//玩家功能模块
	private final Map<String,Object> _module;
	//玩家系统设置
	private final Map<String,Object> _setting;
	
	//玩家当前比赛
	private GameMatchInfo _currmatch;
	
	public GamePlayer(ChannelHandlerContext channel,int playerId){
		super(channel);
		_playerId = playerId;
		_attribute = new HashMap<String, Object>(TayeePlayerConfig.AttrMap);
		_buffer = new HashMap<String, Object>(TayeePlayerConfig.BuffMap);
		_position = new HashMap<String, Object>(TayeePlayerConfig.PosMap);
		_module = new HashMap<String, Object>(TayeePlayerConfig.ModuleMap);
		_setting = new HashMap<String, Object>(TayeePlayerConfig.SettingMap);
	}
	
	public GameMatchInfo newGameMatch(GameMatchInfo match) {
		_currmatch = match;
		return _currmatch;
	}

	public void enterMatch(GameMatchInfo match) {
		synchronized(match){
			match.setPlayer(this);
			_currmatch = match;
		}
	}

	public GameMatchInfo getGameMatch() {
		return _currmatch;
	}

	public void mandate() {
//		_currmatch.m
	}

	public void abandon() {
		_currmatch.recover();
	}

	public void follow() {
		
	}

	public void filling(int count) {
		
	}

	public void allin() {
		// TODO Auto-generated method stub
		
	}

	public void await() {
		// TODO Auto-generated method stub
		
	}

	/**获取玩家基础属性*/
	public <T> T getPlayerAttr(String attr){
		if(_attribute.containsKey(attr)){
			return (T) _attribute.get(attr);
		}
		return null;
	}
	
	/**设置玩家基础属性*/
	public <T> GamePlayer setPlayerAttr(String attr,T value){
		_attribute.put(attr, value);
		return this;
	}
	
	/**获取玩家buff状态*/
	public <T> T getPlayerBuffer(String attr){
		if(_buffer.containsKey(attr)){
			return (T) _buffer.get(attr);
		}
		return null;
	}
	
	/**设置玩家buff状态*/
	public <T> GamePlayer setPlayerBuffer(String attr,T value){
		_buffer.put(attr, value);
		return this;
	}
	
	/**获取玩家功能模块*/
	public <T> T getPlayerModule(String attr){
		if(_module.containsKey(attr)){
			return (T) _module.get(attr);
		}
		return null;
	}
	
	/**设置玩家功能模块*/
	public <T> GamePlayer setPlayerModule(String attr,T value){
		_module.put(attr, value);
		return this;
	}
	
	/**设置玩家位置属性*/
	public GamePlayer setPlayserPos(String attr,String value){
		_position.put(attr, value);
		return this;
	}
	
	/**获取玩家位置属性*/
	public String getPlayerPos(String attr){
		if(_position.containsKey(attr)){
			return _position.get(attr).toString();
		}
		return null;
	}
	
	/**保存玩家系统设置*/
	public <T> GamePlayer setPlayerSetting(String attr, T value){
		_setting.put(attr, value);
		return this;
	}
	
	/**获取玩家游戏设置*/
	public <T> T getPlayerSetting(String attr){
		if(_setting.containsKey(attr)){
			return (T) _setting.get(attr);
		}
		return null;
	}
	
	/**根据玩家编号读取玩家数据*/
	public GamePlayer loadData(){
		Jedis jedis = TayeeRedisManager.getInstance().getJedis();
		Transaction tx = jedis.multi();
		//读取玩家基础属性
		for (String key : _attribute.keySet()) {
			_attribute.put(key,tx.get(getKey(key)));
		}
		//读取玩家状态buff
		for (String key : _buffer.keySet()) {
			_buffer.put(key,tx.get(getKey(key)));
		}
		//读取玩家功能模块
		for (String key : _module.keySet()) {
			_module.put(key,tx.get(getKey(key)));
		}		
		//读取玩家位置信息
		for (String key : _position.keySet()) {
			_position.put(key,tx.get(getKey(key)));
		}
		//读取玩家系统设置
		for (String key : _setting.keySet()) {
			_setting.put(key,tx.get(getKey(key)));
		}
		
		List<Object> rs  = tx.exec(); 
		jedis.close();
		System.out.println("save record size:"+rs);
		
		return this;
	}
	
	/**保存玩家数据到数据库*/
	public GamePlayer saveData(){
		Jedis jedis = TayeeRedisManager.getInstance().getJedis();
		Transaction tx = jedis.multi();
		//保存玩家基础属性
		for (String key : _attribute.keySet()) {
			tx.set(getKey(key),_attribute.get(key).toString());
		}
		//保存玩家状态buff
		for (String key : _buffer.keySet()) {
			tx.set(getKey(key),_buffer.get(key).toString());
		}
		//保存玩家功能模块
		for (String key : _module.keySet()) {
			tx.set(getKey(key),_module.get(key).toString());
		}		
		//保存玩家位置信息
		for (String key : _position.keySet()) {
			tx.set(getKey(key),_position.get(key).toString());
		}
		//保存玩家系统设置
		for (String key : _setting.keySet()) {
			tx.set(getKey(key),_setting.get(key).toString());
		}
		
		List<Object> rs  = tx.exec(); 
		jedis.close();
		System.out.println("save record size:"+rs);
		
		return this;
	}
	
	/**玩家发送数据*/
	public GamePlayer sendData(Object data){
		if(!(data instanceof MsgResponse.Builder)){
//			System.out.println("消息类型不正确..");
			MsgResponse.Builder rsp = MsgResponse.newBuilder();
			rsp.setCode(200);
			rsp.setData(data.toString());
			send(rsp);
		}else{
			send(data);
		}
		return this;
	}
	
	/**玩家断开连接*/
	public void closeSession(){
		close();
	}
	
	/**获取玩家ID*/
	public int getPlayerId(){
		return _playerId;
	}
	
	//获取一个属性字段
	private String getKey(String attr){
		return "PlayerInfo-"+_playerId+"-"+attr;
	}

}

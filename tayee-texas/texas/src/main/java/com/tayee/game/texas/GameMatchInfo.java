package com.tayee.game.texas;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.tayee.game.common.TayeeMatch;
import com.tayee.game.common.TayeeMatchStatus;
import com.tayee.game.common.TayeePlayer;

public class GameMatchInfo implements TayeeMatch,GameMatchAction{
	
	private final String _zoneName;
	private final String _groupName;
	private final String _matchName;
	
	private final byte[] _lock =new byte[0];
	
	/**参数玩家集合*/
	private ConcurrentHashMap<Integer,GamePlayer> _players;
	/**玩家行动顺序*/
	private ConcurrentLinkedQueue<Integer> _exequeue;
	/**比赛状态*/
	private TayeeMatchStatus _matchStatus;
	/**当前匹配准备超时*/
	private long _currReadyTimeout;
	/**当前玩家执行权超时*/
	private long _currAuthorizeTimeout;
	/**当前行动玩家*/
	private int _currPlayerId;
	
	public GameMatchInfo(String zoneName, String groupName, String matchName) {
		_players = new ConcurrentHashMap<Integer, GamePlayer>();
		_exequeue = new ConcurrentLinkedQueue<Integer>();
		_currReadyTimeout = System.currentTimeMillis() + 30000;
		_matchStatus = TayeeMatchStatus.Ready;
		_zoneName = zoneName;
		_groupName = groupName;
		_matchName = matchName;
	}

	public void run() {
		do {
			switch (_matchStatus) {
				case Ready:
					ready();
				case Playing:
					playing();
				case Cleaning:
					clearing();
				default:
					_matchStatus = TayeeMatchStatus.Over;
			}
		} while (_matchStatus != TayeeMatchStatus.Over);

	}


	public void authorize() {
		synchronized (_lock) {
			_currPlayerId = _exequeue.poll();
			_currAuthorizeTimeout =  System.currentTimeMillis() + 10000;
		}
	}

	public void recover() {
		synchronized (_lock) {
			_currPlayerId = 0;
			_currAuthorizeTimeout = Long.MAX_VALUE;
		}
	}
	
	public void ready() {
		synchronized (_players){
			if(_players.size() == 3){
				sendMatch("准备完毕,比赛开始!当前比赛人数:"+_players.size());
				_matchStatus = TayeeMatchStatus.Playing;
			}else{
				if(System.currentTimeMillis()>_currReadyTimeout){
					_matchStatus = TayeeMatchStatus.Over;
					System.out.println(_matchName +" 房间匹配玩家超时...");
				}else{
					System.out.println(_matchName +" 房间正在等待游戏玩家进入...");
				}
			}
		}
	}

	public void playing() {
		if(_currPlayerId!=0){
			if(System.currentTimeMillis() >_currAuthorizeTimeout){
				//玩家自动托管或者踢出玩家
				_players.get(_currPlayerId);
				recover();
			}
		}else{
			
		}
	}

	public void clearing() {

	}

	@SuppressWarnings("unchecked")
	public Collection<GamePlayer> getAllPlayer() {
		return _players.values();
	}

	@SuppressWarnings("unchecked")
	public GamePlayer getPlayer(int playerId) {
		if (0 == playerId || null == _players) {
			return null;
		}
		return _players.get(playerId);
	}

	@SuppressWarnings("unchecked")
	public GamePlayer delPlayer(int playerId) {
		synchronized (_players) {
			return _players.remove(playerId);
		}
	}

	public <T extends TayeePlayer> TayeeMatch setPlayer(T player) {
		synchronized (_players) {
			GamePlayer p = (GamePlayer) player;
			_players.put(p.getPlayerId(),p);
		}
		return this; 
	}
	
	public TayeeMatch sendMatch(Object msg) {
		Collection<GamePlayer> players = getAllPlayer();
		for (TayeePlayer gamePlayer : players) {
			gamePlayer.sendData(msg);
		}
		return this;
	}

	public String getZoneName() {
		return _zoneName;
	}

	public String getGroupName() {
		return _groupName;
	}

	public String getMatchName() {
		return _matchName;
	}

}

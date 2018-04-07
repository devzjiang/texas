package com.tayee.game.texas;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.tayee.game.common.TayeeGroup;
import com.tayee.game.common.TayeeMatch;

public class GameGroupInfo implements TayeeGroup {

	private final String _zoneName;
	private final String _groupName;
	private final ConcurrentHashMap<String,TayeeMatch> _matchs;
	
	public GameGroupInfo(String zoneName, String groupName, int matchSize) {
		// TODO Auto-generated constructor stub
		_matchs = new ConcurrentHashMap<String,TayeeMatch>();
		_zoneName = zoneName;
		_groupName = groupName;
		for (int i = 1; i <= matchSize; i++) {
			String matchName = "testMatch"+i;
			_matchs.put(matchName,new GameMatchInfo(zoneName,groupName,matchName));	
		}
	}

	public Collection<TayeeMatch> getAllMatch() {
		// TODO Auto-generated method stub
		return _matchs.values();
	}

	public TayeeGroup sendGroup(Object msg) {
		// TODO Auto-generated method stub
		for (TayeeMatch match : _matchs.values()) {
			match.sendMatch(msg);
		}
		return this;
	}

	public String getZoneName() {
		// TODO Auto-generated method stub
		return _zoneName;
	}

	public String getGroupName() {
		// TODO Auto-generated method stub
		return _groupName;
	}

	public TayeeMatch getMatch(String matchName) {
		// TODO Auto-generated method stub
		return _matchs.get(matchName);
	}

	public TayeeMatch setMatch(TayeeMatch match) {
		// TODO Auto-generated method stub
		return null;
	}

}

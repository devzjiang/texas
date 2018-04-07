package com.tayee.game.texas;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.tayee.game.common.TayeeGroup;
import com.tayee.game.common.TayeeZone;

public class GameZoneInfo implements TayeeZone{
		
		private String _zoneName;
		
		private ConcurrentHashMap<String,TayeeGroup> _groups;
		
		public GameZoneInfo(String zoneName,int groupSize){
			_zoneName = zoneName;
			_groups =new ConcurrentHashMap<String,TayeeGroup>();
			_groups.put("gameGroup1",new GameGroupInfo(_zoneName,"gameGroup1",2));
		}
		
		public Collection<TayeeGroup> getAllGroup() {
			return _groups.values();
		}

		public TayeeGroup getGroup(String groupName) {
			if(""==groupName || null == _groups){
				return null;
			}
			return _groups.get(groupName);
		}

		public void sendZone(Object msg) {
			for (TayeeGroup group : _groups.values()) {
				group.sendGroup(msg);
			}
		}
		
		public String getZoneName(){
			return _zoneName;
		}
}

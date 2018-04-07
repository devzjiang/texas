package com.tayee.game.common;

import java.util.Collection;

public interface TayeeZone {

	 Collection<TayeeGroup> getAllGroup();
	 
	 TayeeGroup getGroup(String groupName);

	 void sendZone(Object msg);
	
	 String getZoneName();
}

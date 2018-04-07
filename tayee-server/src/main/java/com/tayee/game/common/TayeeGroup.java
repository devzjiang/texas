package com.tayee.game.common;

import java.util.Collection;

public interface TayeeGroup {

	 Collection <TayeeMatch> getAllMatch();
			
	 TayeeGroup sendGroup(Object msg);
	 
	 TayeeMatch getMatch(String matchName);
	 
	 TayeeMatch setMatch(TayeeMatch match);
	 
	 String getZoneName();
	
	 String getGroupName();
	 

}

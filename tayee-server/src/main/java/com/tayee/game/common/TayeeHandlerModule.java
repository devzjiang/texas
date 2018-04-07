package com.tayee.game.common;

import java.util.HashMap;
import java.util.Map;

public class TayeeHandlerModule {

	private final int _mkId;
	
	private final Map<Integer,TayeeHandler> _handlerGroup;
	
	public TayeeHandlerModule(int mkId){
		_mkId =mkId;
		_handlerGroup = new HashMap<Integer, TayeeHandler>();
	}
	
	public TayeeHandlerModule add(int cmd,TayeeHandler script){
		_handlerGroup.put(cmd, script);
		return this;
	}
	
	public TayeeHandler get(int cmd){
		if(_handlerGroup.containsKey(cmd)){
			return _handlerGroup.get(cmd);
		}
		return null;
	}
	
	public int getMkId(){
		return _mkId;
	}
}

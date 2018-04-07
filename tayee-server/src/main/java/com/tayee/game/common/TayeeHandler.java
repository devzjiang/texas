package com.tayee.game.common;

public interface TayeeHandler {
	
	/**检测执行权限*/
	boolean permission(TayeeRequest request);
	
	void execute(TayeeRequest request);
	
	void error(TayeeRequest request);
}

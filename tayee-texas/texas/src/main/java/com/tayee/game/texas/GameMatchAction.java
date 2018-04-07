package com.tayee.game.texas;

/**
 * 比赛动作接口
 * @author zjiang
 */
public interface GameMatchAction {

	 /**比赛准备*/
	void ready();	
	
	 /**比赛开始*/
	void playing();
	 
	 /**比赛结算*/
	void clearing();
	
	/**行动权下放*/
	void authorize();
	
	/**行动权回收*/
	void recover();
	
}

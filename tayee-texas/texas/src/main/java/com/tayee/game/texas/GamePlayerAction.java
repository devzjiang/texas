package com.tayee.game.texas;

/**玩家游戏动作接口*/
public interface GamePlayerAction {

	/**创建比赛*/
	GameMatchInfo newGameMatch(GameMatchInfo match);

	/**获取比赛*/
	GameMatchInfo getGameMatch();
	
	/**进入比赛*/
	void enterMatch(GameMatchInfo match);
	
	/** 跟注 */
	void follow();

	/** 加注 */
	void filling(int count);

	/** 全押 */
	void allin();

	/** 让牌 */
	void await();

	/** 系统托管 */
	void mandate();

	/** 放弃比赛 */
	void abandon();
}

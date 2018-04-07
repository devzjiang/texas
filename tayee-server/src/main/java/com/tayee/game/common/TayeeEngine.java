package com.tayee.game.common;

public interface TayeeEngine {

	TayeeEngine initConfig();
	
	TayeeEngine initZone();
	
	TayeeEngine initWorker();
	
	TayeeEngine initDataBase();

	void runServer();
}

package com.tayee.game.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class TayeeSyncTaskManager extends ThreadPoolExecutor{
	
	public void commitSyncTask(TayeeSyncTask task){
		execute(task);
	}
	
	private TayeeSyncTaskManager(){
		super(2,5,30,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1000));
	}
	
	private static final class Manager{
		static final TayeeSyncTaskManager manager = new TayeeSyncTaskManager();
	}
	
	public static final TayeeSyncTaskManager getInstance(){
		return Manager.manager;
	}
}

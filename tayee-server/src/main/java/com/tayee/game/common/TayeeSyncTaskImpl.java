package com.tayee.game.common;

import redis.clients.jedis.Jedis;

public abstract class TayeeSyncTaskImpl implements TayeeSyncTask {
	
	protected String taskName;
	protected Jedis redis;
	
	
	public String getTaskName(){
		return taskName;
	}

	
	public Jedis getJedis() {
		return redis;
	}
	
	
	public void setJedis() {
		redis.close();
	}
	
	
	public void run() {
			// TODO Auto-generated method stub
			Jedis jedis =getJedis();
			TayeeSyncLock lock = new TayeeSyncLock(jedis,getTaskName(),3000, 60000);
			try {
				if (lock.acquire()) {//如果锁上了
				     try {
				 		syncHandler();
				     } 
				     catch (Exception e) {
				         e.printStackTrace();
				     } finally {
				         lock.release();//则解锁
				         setJedis();
				     }
				 }
			} catch (InterruptedException e) {
		}
	}
}

package com.tayee.game.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class TayeeWorkerManager extends ScheduledThreadPoolExecutor{
	
	private Map<Integer,TayeeWorker>  _workerGroup;
	
	private AtomicInteger counter =new AtomicInteger(0);
	
	private TayeeWorkerManager(){
		super(4);
	}
	
	public void bind(TayeeHandlerModule...modules){
		_workerGroup =new ConcurrentHashMap<Integer,TayeeWorker>();
		for (TayeeHandlerModule module : modules) {
			TayeeWorker worker=new TayeeWorker(module);
			_workerGroup.put(module.getMkId(),worker);
			execute(worker);
		}
		
	}
	
	public void addGameRequest(TayeeRequest request){	
		_workerGroup.get(request.getMk()).addGameReqeust(request);
		System.err.println("==>> handler request size:"+ counter.incrementAndGet());
	}	
	
	public TayeeWorker getGameWorker(String name){
		return _workerGroup.get(name);
	}
		
	public static final TayeeWorkerManager getInstance(){
		return Manager.obj;
	}
	
	private static final class Manager{
		private static final TayeeWorkerManager obj =new TayeeWorkerManager();
	}
	
}

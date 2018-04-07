package com.tayee.game.common;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TayeeWorker implements Runnable {

	private volatile boolean isRunning = true;

	private Queue<TayeeRequest> _requests = new ConcurrentLinkedQueue<TayeeRequest>();
	
	private TayeeHandlerModule _handler;
	
	public TayeeWorker(TayeeHandlerModule handler){
		_handler = handler;
	}
	
	public void addGameReqeust(TayeeRequest request){
		if(isRunning){
			synchronized (_requests) {
				_requests.add(request);
				_requests.notifyAll();
            }
		}
	}

	public void run() {		
		// TODO Auto-generated method stub
		while(true){
			
			synchronized(_requests) {				
				if(_requests.isEmpty()){
					try {
						_requests.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						if(!isRunning){
							return;
						}
					}
				}
			}
						
			TayeeRequest request = null;
			
			while(null!=(request =_requests.poll())){
				TayeeHandler _script =_handler.get(request.getCmd());
				synchronized (_script) {
					if(_script.permission(request)){
						_script.execute(request);
					}else{
						_script.error(request);
					}
				}
			}
			
		}
	}	
	
}

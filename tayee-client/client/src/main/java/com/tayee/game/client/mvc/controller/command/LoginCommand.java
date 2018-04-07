package com.tayee.game.client.mvc.controller.command;

import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import com.tayee.game.client.mvc.NotiName;
import com.tayee.game.client.mvc.model.User;
import com.tayee.game.client.mvc.model.proxy.LoginProxy;

public class LoginCommand extends SimpleCommand implements ICommand {
	
	public void execute(INotification noti) {
	   if (noti.getName().equals(NotiName.NOTI_LOGIN) == true) {
	    	User user = (User)noti.getBody();
	    	
	    	LoginProxy lp = (LoginProxy) getFacade().retrieveProxy(LoginProxy.NAME);
	    	
	    	if(checkLogin(user))	
	    		lp.login(user);
	    	else
	    		sendNotification((String) NotiName.LOGIN_FAILED,"User name should not be empty!",null);
	   }
	}
	
	private boolean checkLogin(User u){
		//username should not be empty
		if(u.getUsername() == null )return false;
		
		return true;
	}
}

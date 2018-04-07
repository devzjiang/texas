package com.tayee.game.client.mvc.model.proxy;

import org.puremvc.java.multicore.patterns.proxy.Proxy;

import com.tayee.game.client.mvc.NotiName;
import com.tayee.game.client.mvc.model.User;


public class LoginProxy extends Proxy {
	
	public static final String NAME = "LoginProxy";
	
	public LoginProxy() {
        super(NAME, null);
        System.out.println("LoginProxy.LoginProxy()");
    }

	public void login(User user) {
		
		if(user.getUsername()!="")
			sendNotification(NotiName.LOGIN_SUCCESS,"login success!",null);
		else 
			sendNotification(NotiName.LOGIN_FAILED,"login error ,wrong username",null);
	}

}

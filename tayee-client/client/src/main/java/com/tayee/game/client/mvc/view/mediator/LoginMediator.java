package com.tayee.game.client.mvc.view.mediator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

import com.tayee.game.client.mvc.NotiName;
import com.tayee.game.client.mvc.model.User;
import com.tayee.game.client.mvc.view.LoginWindow;

public class LoginMediator extends Mediator{

	private static final String NAME="LoginMediator";
	
	private LoginWindow view ;
	public LoginMediator(LoginWindow v) {
		super(NAME, null);
		this.setViewComponent(v);
		view = v;
		v.setVisible(true);
		
		v.btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
	}
	
	private void login(){
		@SuppressWarnings("deprecation")
		User user = new User(
						view.txtName.getText(),
						view.pwdPwd.getText()
							);
		sendNotification(NotiName.NOTI_LOGIN,user,null);
	}
	
	
	
	@Override
    public String[] listNotificationInterests() {
        String[] list = {
            NotiName.LOGIN_SUCCESS, NotiName.LOGIN_FAILED
        };
        return list;
    }
	
	@Override
    public void handleNotification(INotification noti) {
		
		 if (noti.getName().equals(NotiName.LOGIN_SUCCESS)){
			 System.out.println("login success");
		 }
		 
		 if (noti.getName().equals(NotiName.LOGIN_FAILED)){
			 System.out.println("login failed " + noti.getBody().toString());
		 }
    }

}

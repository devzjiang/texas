package com.tayee.game.client.mvc.controller;



import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import com.tayee.game.client.mvc.view.LoginWindow;
import com.tayee.game.client.mvc.view.mediator.LoginMediator;

public class PrepViewCommand extends SimpleCommand implements ICommand{
	
    public void execute(INotification noti){
    	
        System.out.println("PrepViewCommand.execute("+ noti.getName() + ")");
  
        getFacade().registerMediator(new LoginMediator(new LoginWindow()));
  
    }
}

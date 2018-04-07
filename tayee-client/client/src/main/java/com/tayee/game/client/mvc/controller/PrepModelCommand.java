package com.tayee.game.client.mvc.controller;

import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import com.tayee.game.client.mvc.model.proxy.LoginProxy;


public class PrepModelCommand extends SimpleCommand implements ICommand{
	
    public void execute(INotification noti){
        System.out.println("PrepModelCommand.execute()");
        getFacade().registerProxy(new LoginProxy());
    }
}

package com.tayee.game.client.mvc;

import org.puremvc.java.multicore.patterns.facade.Facade;

import com.tayee.game.client.mvc.controller.StartupCommand;


public class AppFacade extends Facade {

    protected AppFacade(String key) {
		super(key);
	}

    private static AppFacade instance = null;

    public static AppFacade getInstance(){
        if( instance == null) instance = new AppFacade("GGGGG");
        return instance ;
    }

    protected void initializeController() {
         System.out.println("AppFacade.initController()");
         super.initializeController();
         registerCommand(NotiName.NOTI_START, new StartupCommand());
    }

    public void startup() {
     System.out.println("AppFacade.startup");
	 this.sendNotification(NotiName.NOTI_START, null, null);
    }
}

package com.tayee.game.client.mvc.controller;

import org.puremvc.java.multicore.patterns.command.MacroCommand;

public class StartupCommand extends MacroCommand{
	
    protected void initializeMacroCommand() {
    	
        System.out.println("PrepStartUpCommand.initializeMacroCommand()");
        addSubCommand(new PrepModelCommand());
        addSubCommand(new PrepControllerCommand());
        addSubCommand(new PrepViewCommand());
        
    }
}

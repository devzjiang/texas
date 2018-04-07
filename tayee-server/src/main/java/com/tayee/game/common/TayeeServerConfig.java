package com.tayee.game.common;

import java.util.Properties;

public class TayeeServerConfig {
	
	public static  String serverId ="game_demo_001";
	
	public static  String serverName ="testServer";
		
	public static  String netHost = "0.0.0.0";
	
	public static  int netPort = 9812;
	
	public static  String dbHost ="localhost";
	
	public static  int dbPort = 6379;
	
	public static  String dbAuth = "";
	
	public static final void loadConfig(String filePath){
		Properties prop =TayeeUtils.getProperites(filePath);
		if(null!=prop) {
			serverName = prop.getProperty("tayee.server.serverName");
			serverId = prop.getProperty("tayee.server.serverId");
			netHost = prop.getProperty("tayee.server.netHost");
			netPort = Integer.parseInt(prop.getProperty("tayee.server.netPort"));
			dbHost = prop.getProperty("tayee.server.dbHost");
			dbPort = Integer.parseInt(prop.getProperty("tayee.server.dbPort"));
		}
	}
	
}

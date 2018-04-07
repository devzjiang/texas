package com.tayee.game.common;

import io.netty.channel.ChannelHandlerContext;

public class TayeeRequest{
	
	private ChannelHandlerContext session;
	
	private String token;
	
	private int mk;
	
	private int cmd;
	
	private String param;
	
	public TayeeRequest(){}
	
	public TayeeRequest(ChannelHandlerContext session,String token,int mk,int cmd,String param){
		this.session = session;
		this.token = token;
		this.mk = mk;
		this.cmd = cmd;
		this.param = param;
	}

	public ChannelHandlerContext getSession() {
		return session;
	}

	public void setSession(ChannelHandlerContext session) {
		this.session = session;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getMk() {
		return mk;
	}

	public void setMk(int mk) {
		this.mk = mk;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
		
}

package com.tayee.game.common;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public abstract class TayeeServer {
	
	public void run() {
		String host =TayeeServerConfig.netHost;
		int port =TayeeServerConfig.netPort;
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap bootstrap=new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(getChannelHandlerAdapter());

			System.out.println("Server start ...");
			System.out.println("Bind host:"+host);
			System.out.println("Listener Port:"+port);
			
			ChannelFuture f = bootstrap.bind(new InetSocketAddress(host,port)).sync();
			f.channel().closeFuture().sync();			
		}catch(Exception e){
			
		}finally {
			 workerGroup.shutdownGracefully();
             bossGroup.shutdownGracefully();
		}

	}

	public abstract ChannelHandlerAdapter getChannelHandlerAdapter();
	

}

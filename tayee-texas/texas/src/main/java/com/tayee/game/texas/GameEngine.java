package com.tayee.game.texas;

import com.tayee.game.common.TayeeEngine;
import com.tayee.game.common.TayeeHandlerModule;
import com.tayee.game.common.TayeePlayerConfig;
import com.tayee.game.common.TayeeRedisManager;
import com.tayee.game.common.TayeeServer;
import com.tayee.game.common.TayeeServerConfig;
import com.tayee.game.common.TayeeWorkerManager;
import com.tayee.game.common.TayeeZoneConfig;
import com.tayee.game.texas.GameProtoBuf.MsgCmd;
import com.tayee.game.texas.GameProtoBuf.MsgMk;
import com.tayee.game.texas.handler.HandlerBuildPlayer;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class GameEngine extends TayeeServer implements TayeeEngine {

	public TayeeEngine initConfig() {
		// TODO Auto-generated method stub

		//加载系统运行参数配置
		TayeeServerConfig.loadConfig("");
		//加载游戏运行参数配置
		TayeeZoneConfig.loadConfig("");
		//加载玩家数据结构配置
		TayeePlayerConfig.loadConfig(System.getProperty("user.dir") + "//src//main//res//conf//player");

		return this;
	}

	public TayeeEngine initZone() {
		// TODO Auto-generated method stub	
		//读取游戏世界数据
		GameZoneManager.getInstance().load();
		return this;
	}


	//绑定逻辑处理线程脚本
	public TayeeEngine initWorker() {
		
		TayeeWorkerManager.getInstance().bind(
			new TayeeHandlerModule(MsgMk.MK_PLAYER_VALUE)
				.add(MsgCmd.CMD_BUILD_PLAYER_VALUE,new HandlerBuildPlayer())
			,
				
			new TayeeHandlerModule(MsgMk.MK_MATCH_VALUE)
//				.add(MsgCmd.CMD_MATCH_ENTER_ROOM_VALUE,new ServerEnterRoomHandler())
//				.add(MsgCmd.CMD_MATCH_PLAYER_ACTION_VALUE,new ServerRoomActionHandler())
			);	
		
		return this;
	}

	//初始化游戏数据库
	public TayeeEngine initDataBase() {
		TayeeRedisManager.getInstance().init();	
		return this;
	}

	//运行启动网络监听
	public void runServer() {
		run();
	}
	
	public ChannelHandlerAdapter getChannelHandlerAdapter(){
		return new ChannelInitializer<SocketChannel>(){
			protected void initChannel(SocketChannel ch) throws Exception {
				// TODO Auto-generated method stub
				ChannelPipeline cp = ch.pipeline();
				cp.addLast(new ProtobufVarint32FrameDecoder());
				cp.addLast(new ProtobufDecoder(GameProtoBuf.MsgRequest.getDefaultInstance()));
				cp.addLast(new ProtobufVarint32LengthFieldPrepender());
				cp.addLast(new ProtobufEncoder());
				cp.addLast(new GameHandlerAdapter());
			}
		};
	}
	
	public static void main(String[] args) {
			
		new GameEngine().initConfig().initZone().initWorker().initDataBase().runServer();
		
	}


}

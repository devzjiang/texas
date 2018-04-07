package com.tayee.game.texas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;
import com.tayee.game.texas.GameProtoBuf.MsgCmd;
import com.tayee.game.texas.GameProtoBuf.MsgMk;
import com.tayee.game.texas.GameProtoBuf.MsgRequest;
import com.tayee.game.texas.GameProtoBuf.MsgResponse;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class GameClient {
	
	public static void main(String[] args) {
//		for (int i = 1; i <= 10; i++) {
			Client client =new Client(10002,"赵云");
			new Thread(client).start();
//		}
	}

}

class Client implements Runnable{
	
	private int playerid;
	private String name;
	
	private final byte[] lock =new byte[0];
	
	public Client(int pid,String name){
		this.playerid =pid;
		this.name =name;
	}
	
	public Client(){}
	
	public void run() {
		newClient();
	}
	
	public void newClient(){
		try{
			
	        EventLoopGroup workerGroup=new NioEventLoopGroup();
	        try{
	            Bootstrap b=new Bootstrap();
	            b.group(workerGroup);
	            b.channel(NioSocketChannel.class);
	            b.option(ChannelOption.SO_KEEPALIVE, true);
	            b.handler(new ChannelInitializer<SocketChannel>() {
	                @Override
	                protected void initChannel(SocketChannel ch) throws Exception {
	                    //decoded
	                	ChannelPipeline cp = ch.pipeline();
	    				cp.addLast(new ProtobufVarint32FrameDecoder());
	    				cp.addLast(new ProtobufDecoder(GameProtoBuf.MsgResponse.getDefaultInstance()));
	    				cp.addLast(new ProtobufVarint32LengthFieldPrepender());
	    				cp.addLast(new ProtobufEncoder());
	    				cp.addLast(new ChannelHandlerAdapter(){
	    					@Override
	    					public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	    						MsgResponse response = (MsgResponse) msg;
//	    						if(response.get)
	    						String data = response.getData();
//	    						System.out.println("receive code:"+response.getCode());
	    						System.out.println("\n");
	    						System.out.println(ctx.hashCode()+"rece:"+data);
	    						System.out.println("\n");
	    						
//	    						lock.notify();
	    					}
	    				});
	                }
	            });
	          
	            	
	            ChannelFuture f= b.connect("localhost", 9812).sync();
	            
	            //进入游戏请求
//	            EnterGameRequest req =new EnterGameRequest();
//	            req.setPlayerId(this.playerid);
//	            req.setPlayerName(this.name);
//	            MsgRequest.Builder msg =MsgRequest.newBuilder();
//				msg.setMk(MsgMk.MK_SERVER_VALUE).setCmd(MsgCmd.CMD_SERVER_ENTER_GAME_VALUE)
//				.setToken("hahahahahaha").setParam(JSON.toJSONString(req)).build();
//				f.channel().writeAndFlush(msg);
//				
//				Thread.sleep(2000);
//				
//				//进入房间请求
//				EnterRoomRequest req1 =new EnterRoomRequest();
//				req1.setPlayerId(this.playerid);
//				req1.setZoneName("gameZone1");
//				req1.setGroupName("gameGroup1");
//				req1.setMatchName("testMatch1"); 
//				MsgRequest.Builder msg1 =MsgRequest.newBuilder();
//				msg1.setMk(MsgMk.MK_MATCH_VALUE).setCmd(MsgCmd.CMD_MATCH_ENTER_ROOM_VALUE)
//				.setToken("hahahahahaha").setParam(JSON.toJSONString(req1)).build();
//				f.channel().writeAndFlush(msg1);

				//随机发送房间消息
//				Random random = new Random();
				while(true){
//					BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));  
//			        System.out.println(req.getPlayerName()+"  请输入数字(1~1000):");  
//			        int action = Integer.parseInt(strin.readLine());  
//					RoomActionRequest req2 = new RoomActionRequest();
//					req2.setPlayerId(this.playerid);
//					req2.setZoneName("gameZone1");
//					req2.setGroupName("gameGroup1");
//					req2.setMatchName("testMatch1"); 
//					req2.setAction(getAction(action));
//					MsgRequest.Builder msg2 =MsgRequest.newBuilder();
//					msg2.setMk(MsgMk.MK_MATCH_VALUE).setCmd(MsgCmd.CMD_MATCH_PLAYER_ACTION_VALUE)
//					.setToken("hahahahahaha").setParam(JSON.toJSONString(req2)).build();
//					f.channel().writeAndFlush(msg2);
//					Thread.sleep(random.nextInt(2000));
				}
//				f.channel().closeFuture().sync();
	        }finally{
	            workerGroup.shutdownGracefully();
	        }
		

			
		}catch(Exception e){
			
		}
	}
	
	private String getAction(int rand){
		String val ="";
		if(rand<100){
			val ="同志们好!";
		}else if(rand>100 &&rand <300){
			val ="你们是哪个地方的朋友哇?";
		}else if(rand>300 && rand<600){
			val ="我是个像谜一样的男子!";
		}else if(rand>600 && rand<800){
			val="喔唷，屌爆了...";
		}else{
			val ="然而这并没有什么卵用@@！";
		}
		
		return val;
	}

}


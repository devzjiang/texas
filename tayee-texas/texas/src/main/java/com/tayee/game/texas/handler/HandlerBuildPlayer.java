package com.tayee.game.texas.handler;

import com.alibaba.fastjson.JSON;
import com.tayee.game.common.TayeeRequest;
import com.tayee.game.texas.GamePlayer;
import com.tayee.game.texas.GameProtoBuf.MsgCode;
import com.tayee.game.texas.GameProtoBuf.MsgResponse;
import com.tayee.game.texas.GameZoneManager;
import com.tayee.game.texas.request.RequestBuildPlayer;

public class HandlerBuildPlayer extends HandlerPermission {

	public void execute(TayeeRequest request) {

		RequestBuildPlayer param = JSON.toJavaObject(
				JSON.parseObject(request.getParam()),RequestBuildPlayer.class);
		
		GamePlayer player =(GamePlayer) GameZoneManager.getInstance().newPlayerEnterGame(
				request.getSession(),param.getPlayerName(),param.getPlayerId());
		
		MsgResponse.Builder response = MsgResponse.newBuilder();
		response.setCode(MsgCode.CODE_OK_VALUE);
		response.setData("");
		
		player.send(response);
	}

}

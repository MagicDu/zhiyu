
package com.zhiyu.framework.config.websocket;

import java.security.Principal;
import java.util.Collections;


import cn.hutool.core.date.DateUtil;
import com.zhiyu.common.core.entity.ActiveWebSocketUser;
import com.zhiyu.framework.security.TokenService;
import com.zhiyu.system.entity.LoginUser;
import com.zhiyu.system.service.ActiveWebSocketUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.socket.messaging.SessionConnectEvent;

public class WebSocketConnectHandler<S> implements ApplicationListener<SessionConnectEvent> {

	private ActiveWebSocketUserService repository;

	private SimpMessageSendingOperations messagingTemplate;

	private TokenService tokenService;

	public WebSocketConnectHandler(SimpMessageSendingOperations messagingTemplate,
								   ActiveWebSocketUserService repository,TokenService tokenService) {
		super();
		this.messagingTemplate = messagingTemplate;
		this.repository = repository;
		this.tokenService=tokenService;
	}

	@Override
	public void onApplicationEvent(SessionConnectEvent event) {
		MessageHeaders headers = event.getMessage().getHeaders();
		String token=SimpMessageHeaderAccessor.wrap(event.getMessage()).getFirstNativeHeader("Authorization");
		if (token == null) {
			return;
		}else{
			// 获取用户信息
			LoginUser loginUser = tokenService.getLoginUser(token);
			String id = SimpMessageHeaderAccessor.getSessionId(headers);
			ActiveWebSocketUser activeWebSocketUser = new ActiveWebSocketUser();
			activeWebSocketUser.setId(id);
			activeWebSocketUser.setUsername(loginUser.getUsername());
			activeWebSocketUser.setConnectionTime(DateUtil.date());
			this.repository.save(activeWebSocketUser);
			this.messagingTemplate.convertAndSend("/topic/friends/signin", Collections.singletonList(loginUser.getUsername()));
		}
	}

}

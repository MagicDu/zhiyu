/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhiyu.framework.config.websocket;

import java.util.Collections;


import cn.hutool.core.util.ObjectUtil;
import com.zhiyu.common.core.entity.ActiveWebSocketUser;
import com.zhiyu.system.service.ActiveWebSocketUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public class WebSocketDisconnectHandler<S> implements ApplicationListener<SessionDisconnectEvent> {

	private ActiveWebSocketUserService repository;

	private SimpMessageSendingOperations messagingTemplate;

	public WebSocketDisconnectHandler(SimpMessageSendingOperations messagingTemplate,
									  ActiveWebSocketUserService repository) {
		super();
		this.messagingTemplate = messagingTemplate;
		this.repository = repository;
	}

	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		String id = event.getSessionId();
		if (id == null) {
			return;
		}
		ActiveWebSocketUser user = this.repository.getById(id);
		if(ObjectUtil.isNotNull(user)){
			this.repository.removeById(id);
			this.messagingTemplate.convertAndSend("/topic/friends/signout",
					Collections.singletonList(user.getUsername()));
		}
	}

}

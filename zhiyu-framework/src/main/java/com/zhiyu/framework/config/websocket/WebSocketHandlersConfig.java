
package com.zhiyu.framework.config.websocket;



import com.zhiyu.framework.security.TokenService;
import com.zhiyu.system.service.ActiveWebSocketUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.session.Session;

/**
 * These handlers are separated from WebSocketConfig because they are specific to this
 * application and do not demonstrate a typical Spring Session setup.
 *
 * @author Rob Winch
 */
@Configuration
public class WebSocketHandlersConfig<S extends Session> {

	@Bean
	public WebSocketConnectHandler<S> webSocketConnectHandler(SimpMessageSendingOperations messagingTemplate,
															  ActiveWebSocketUserService repository, TokenService tokenService) {
		return new WebSocketConnectHandler<>(messagingTemplate, repository,tokenService);
	}

	@Bean
	public WebSocketDisconnectHandler<S> webSocketDisconnectHandler(SimpMessageSendingOperations messagingTemplate,
			ActiveWebSocketUserService repository) {
		return new WebSocketDisconnectHandler<>(messagingTemplate, repository);
	}

}

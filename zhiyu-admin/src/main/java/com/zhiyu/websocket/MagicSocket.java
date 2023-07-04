package com.zhiyu.websocket;

import org.springframework.stereotype.Controller;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Controller
//@ServerEndpoint(value = "/messages")
public class MagicSocket {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("连接建立");
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("连接断开");
    }
}

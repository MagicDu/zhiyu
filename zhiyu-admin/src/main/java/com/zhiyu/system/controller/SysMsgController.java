package com.zhiyu.system.controller;

import com.zhiyu.common.core.entity.SysMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SysMsgController {

    //@Autowired
   // private SimpMessagingTemplate messagingTemplate;


   // @MessageMapping("/im")
    //public void im(SysMsg im) {

        //this.messagingTemplate.convertAndSendToUser(im.getTo(), "/queue/messages", im);
        //this.messagingTemplate.convertAndSendToUser(im.getFrom(), "/queue/messages", im);
   // }
}

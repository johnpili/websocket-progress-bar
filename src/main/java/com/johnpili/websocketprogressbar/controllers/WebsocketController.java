package com.johnpili.websocketprogressbar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController
{
	@Autowired
	ObjectMapper objectMapper;

	@MessageMapping("/test")
	@SendTo("/ws-broadcast/update-progress")
	public String updateProgress() throws Exception
	{
		return "";
	}
}

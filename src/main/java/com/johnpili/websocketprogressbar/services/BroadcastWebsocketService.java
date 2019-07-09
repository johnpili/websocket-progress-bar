package com.johnpili.websocketprogressbar.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class BroadcastWebsocketService
{
	private static final Logger logger = LoggerFactory.getLogger(BroadcastWebsocketService.class);

	@Autowired
	SimpMessagingTemplate messagingTemplate;

	public void broadcastProgressUpdate(String destination, int progress)
	{
		try
		{
			messagingTemplate.convertAndSend(destination, progress);
		}
		catch (Exception exception)
		{
			logger.error(exception.getMessage());
		}
	}

	public void broadcastProgressUpdate(String destination, String jsonPayload)
	{
		try
		{
			messagingTemplate.convertAndSend(destination, jsonPayload);
		}
		catch (Exception exception)
		{
			logger.error(exception.getMessage());
		}
	}
}

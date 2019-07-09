package com.johnpili.websocketprogressbar.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendProgressRunnable implements Runnable
{
	private static final Logger logger = LoggerFactory.getLogger(SendProgressRunnable.class);

	public static String BROADCAST_WS_ENDPOINT = "/ws-broadcast/update-progress";

	BroadcastWebsocketService broadcastWebsocketService;

	public SendProgressRunnable(BroadcastWebsocketService broadcastWebsocketService)
	{
		this.broadcastWebsocketService = broadcastWebsocketService;
	}

	@Override
	public void run()
	{
		for(int i = 0; i <= 100; ++i)
		{
			try
			{
				this.broadcastWebsocketService.broadcastProgressUpdate(BROADCAST_WS_ENDPOINT, i);
				Thread.sleep(500);
				if(i >= 100)
				{
					Thread.currentThread().interrupt();
					break;
				}
			}
			catch(Exception exception)
			{
				logger.error(exception.getMessage());
			}
		}
	}
}

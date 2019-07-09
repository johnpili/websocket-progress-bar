package com.johnpili.websocketprogressbar.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendProgressRunnable implements Runnable
{
	private static final Logger logger = LoggerFactory.getLogger(SendProgressRunnable.class);

	public static String BROADCAST_WS_ENDPOINT = "/ws-broadcast/update-progress";
	public static String BROADCAST_WS_PROGRESS_UPDATE_WITH_DETAILS = "/ws-broadcast/update-progress-with-details";

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
				if(i >= 100)
				{
					Thread.currentThread().interrupt();
					break;
				}
				Thread.sleep(100);
			}
			catch(Exception exception)
			{
				logger.error(exception.getMessage());
			}
		}
	}
}

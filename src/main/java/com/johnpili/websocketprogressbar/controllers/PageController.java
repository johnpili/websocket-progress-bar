package com.johnpili.websocketprogressbar.controllers;

import com.johnpili.websocketprogressbar.models.SampleViewModel;
import com.johnpili.websocketprogressbar.services.BroadcastWebsocketService;
import com.johnpili.websocketprogressbar.services.SendProgressRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController
{
	@Autowired
	BroadcastWebsocketService broadcastWebsocketService;

	@RequestMapping(value = "/")
	public String index()
	{
		return "index";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String upload(@RequestBody SampleViewModel sampleViewModel)
	{
		new SendProgressRunnable(broadcastWebsocketService).run();
		return "";
	}
}

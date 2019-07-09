package com.johnpili.websocketprogressbar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnpili.websocketprogressbar.models.SampleViewModel;
import com.johnpili.websocketprogressbar.services.BroadcastWebsocketService;
import com.johnpili.websocketprogressbar.services.ProcessDictionaryRunnable;
import com.johnpili.websocketprogressbar.services.SendProgressRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController
{
	@Autowired
	BroadcastWebsocketService broadcastWebsocketService;

	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping(value = "/")
	public String index()
	{
		return "index";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String upload(@RequestBody SampleViewModel sampleViewModel)
	{
		new SendProgressRunnable(broadcastWebsocketService).run();
		return "";
	}

	@RequestMapping(value = "process-dictionary", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String processDictionary(@RequestBody SampleViewModel sampleViewModel)
	{
		new ProcessDictionaryRunnable(broadcastWebsocketService, objectMapper).run();
		return "";
	}
}

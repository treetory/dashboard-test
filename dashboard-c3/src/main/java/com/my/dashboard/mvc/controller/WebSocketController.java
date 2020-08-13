package com.my.dashboard.mvc.controller;

import com.my.dashboard.mvc.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class WebSocketController {

	private static final Logger LOG = LoggerFactory.getLogger(WebSocketController.class);

	@Autowired
	private WebSocketService wss;

	@MessageMapping("/line")
    @SendTo("/topic/line")
    public List<Map<String, List<Object>>> drawLine(Map<String, String> message) throws Exception {

		System.out.println("------------------->>>>>"+ message);
		
		//Thread.sleep(1000); // simulated delay

		return wss.getLineData();
    }

    @MessageMapping("/timeSeries")
    @SendTo("/topic/timeSeries")
    public List<Map<String, List<Object>>> drawTimeSeries(Map<String, String> message) throws Exception {

        System.out.println("------------------->>>>>"+ message);

        //Thread.sleep(1000); // simulated delay

        return wss.getTimeSeriesData();
    }
}

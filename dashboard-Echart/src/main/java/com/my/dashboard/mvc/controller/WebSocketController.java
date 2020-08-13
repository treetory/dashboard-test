package com.my.dashboard.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {

	@MessageMapping("/EchartTest1")
    @SendTo("/topic/testData1")	
	public List<Integer> test(String greeting) throws InterruptedException {

		System.out.println("------------------->>>>>"+ greeting);
		
		Thread.sleep(1000); // simulated delay
		
		List<Integer> dataValue = new ArrayList<Integer>();
		for(int i=0; i<5; i++) {
			dataValue.add((int) (Math.random() * 1000) + 1);
		}


		return dataValue;
	}
	
	@MessageMapping("/EchartTest2")
    @SendTo("/topic/testData2")	
	public List<Integer> test2(String greeting) throws InterruptedException {

		System.out.println("------------------->>>>>"+ greeting);

		Thread.sleep(1000); // simulated delay
		
		List<Integer> Value = new ArrayList<Integer>();
		for(int i=0; i<4; i++) {
			Value.add((int) (Math.random() * (100000-10000+1)) + 10000);
		}


		return Value;
	}
	
	@MessageMapping("/EchartTest3")
    @SendTo("/topic/testData3")	
	public List<Map<String, List<Integer>>> test3(String greeting) throws InterruptedException {

		System.out.println("------------------->>>>>"+ greeting);

		Thread.sleep(1000); // simulated delay
		
		List<Map<String, List<Integer>>> data = new ArrayList<Map<String, List<Integer>>>();

		Map<String, List<Integer>> listValue = new HashMap<>();

		List<Integer> Value = new ArrayList<Integer>();
		for(int j=0; j<2; j++) {
			for(int i=0; i<6; i++) {
				Value.add((int) (Math.random() * (50000-3000+1)) + 3000);

			}

			listValue.put(String.valueOf(j), Value);
			data.add(listValue);
			Value = new ArrayList<Integer>();
		}
		return data;
	}
	
	
	@MessageMapping("/EchartTest4")
    @SendTo("/topic/testData4")	
	public List<Double> test4(String greeting) throws InterruptedException {

		System.out.println("------------------->>>>>"+ greeting);

		Thread.sleep(1000); // simulated delay
		
		List<Double> dataValue = new ArrayList<Double>();

		dataValue.add(Double.parseDouble(String.format("%.2f", Math.random() * 100)));
		return dataValue;
	}
	
	
	@MessageMapping("/EchartTest5")
    @SendTo("/topic/testData5")	
	public List<Map<String, List<Integer>>> test5(String greeting) throws InterruptedException {

		System.out.println("------------------->>>>>"+ greeting);

		Thread.sleep(1000); // simulated delay
		
		List<Map<String, List<Integer>>> data = new ArrayList<Map<String, List<Integer>>>();

		Map<String, List<Integer>> listValue = new HashMap<>();

		List<Integer> Value = new ArrayList<Integer>();
		for(int j=0; j<5; j++) {
			for(int i=0; i<7; i++) {
				Value.add((int) (Math.random() * (800-100+1)) + 100);
			}

			listValue.put(String.valueOf(j), Value);
			data.add(listValue);
			Value = new ArrayList<Integer>();
		}
		return data;
		
	}
	
	@MessageMapping("/EchartTest6")
    @SendTo("/topic/testData6")	
	public List<Map<String, List<Integer>>> test6(String greeting) throws InterruptedException {

		System.out.println("------------------->>>>>"+ greeting);

		Thread.sleep(1000); // simulated delay
		
		List<Map<String, List<Integer>>> data = new ArrayList<Map<String, List<Integer>>>();

		Map<String, List<Integer>> listValue = new HashMap<>();

		List<Integer> Value = new ArrayList<Integer>();
		for(int j=0; j<5; j++) {
			for(int i=0; i<7; i++) {
				Value.add((int) (Math.random() * (800-100+1)) + 100);
			}

			listValue.put(String.valueOf(j), Value);
			data.add(listValue);
			Value = new ArrayList<Integer>();
		}
		return data;
		
	}	
	
	
}

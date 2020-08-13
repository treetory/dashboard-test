package com.my.dashboard.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

	@RequestMapping(value = "/EchartTest1",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Integer> test(HttpServletRequest request, HttpServletResponse response) {
		
		List<Integer> dataValue = new ArrayList<Integer>();
		for(int i=0; i<5; i++) {
			dataValue.add((int) (Math.random() * 1000) + 1);
		}
		
		
		return dataValue;
	}
	
	@RequestMapping(value = "/EchartTest2",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Integer> test2(HttpServletRequest request, HttpServletResponse response) {
		
		List<Integer> Value = new ArrayList<Integer>();
		for(int i=0; i<4; i++) {
			Value.add((int) (Math.random() * (100000-10000+1)) + 10000);
		}
		
		
		return Value;
	}
	
	
	@RequestMapping(value = "/EchartTest3",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, List<Integer>>> test3(HttpServletRequest request, HttpServletResponse response) {
		
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
	
	
	@RequestMapping(value = "/EchartTest4",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Double> test4(HttpServletRequest request, HttpServletResponse response) {
		
		List<Double> dataValue = new ArrayList<Double>();

		dataValue.add(Double.parseDouble(String.format("%.2f", Math.random() * 100)));
		return dataValue;
	}
	
	
	@RequestMapping(value = "/EchartTest5",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, List<Integer>>> test5(HttpServletRequest request, HttpServletResponse response) {
		
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
	
	
	@RequestMapping(value = "/EchartTest6",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, List<Integer>>> test6(HttpServletRequest request, HttpServletResponse response) {
		
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

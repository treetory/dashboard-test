package com.my.dashboard.mvc.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@RequestMapping(
			value = "/radar",
			method = { RequestMethod.GET },
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ArrayList<Map<String, Object>>  test_radar() {

        /**
         * [
         *
         * 	{type : "v1", warrior : 100, wizard : 60, archer : 20 },
         * 	{type : "v2", warrior : 100, wizard : 60, archer : 20 },
         * 	...
         *  {type : "vN", warrior : 100, wizard : 60, archer : 20 },
         *
         * ]
         */

        Map<String, Object> jsonSubObject = null;
        ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        //1번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("type", "STR");
        jsonSubObject.put("warrior", (Math.random() * 100) );
        jsonSubObject.put("wizard", (Math.random() * 100) );
        jsonSubObject.put("archer", (Math.random() * 100) );
        jsonSubObject.put("artist", (Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //2번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("type", "VIT");
        jsonSubObject.put("warrior", (Math.random() * 100) );
        jsonSubObject.put("wizard", (Math.random() * 100) );
        jsonSubObject.put("archer", (Math.random() * 100) );
        jsonSubObject.put("artist", (Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //3번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("type", "DEX");
        jsonSubObject.put("warrior", (Math.random() * 100) );
        jsonSubObject.put("wizard", (Math.random() * 100) );
        jsonSubObject.put("archer", (Math.random() * 100) );
        jsonSubObject.put("artist", (Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //4번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("type", "AGI");
        jsonSubObject.put("warrior", (Math.random() * 100) );
        jsonSubObject.put("wizard", (Math.random() * 100) );
        jsonSubObject.put("archer", (Math.random() * 100) );
        jsonSubObject.put("artist", (Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //5번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("type", "INT");
        jsonSubObject.put("warrior", (Math.random() * 100) );
        jsonSubObject.put("wizard", (Math.random() * 100) );
        jsonSubObject.put("archer", (Math.random() * 100) );
        jsonSubObject.put("artist", (Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //6번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("type", "WIS");
        jsonSubObject.put("warrior", (Math.random() * 100) );
        jsonSubObject.put("wizard", (Math.random() * 100) );
        jsonSubObject.put("archer", (Math.random() * 100) );
        jsonSubObject.put("artist", (Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //System.out.println(jsonList);
        
        return jsonList;

	}

	@RequestMapping(
			value = "/donut",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ArrayList<Map<String, Object>>  test_donut() {

        /**
         * [
         *
         * 	{type1 : 30, type2 : 100, type3 : 60, type4 : 20, type5 : 20, type6 : 20 },
         * 	
         * ]
         */

        Map<String, Object> jsonSubObject = null;
        ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        //1번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("ie", (int)(Math.random() * 20) );
        jsonSubObject.put("ff", (int)(Math.random() * 20) );
        jsonSubObject.put("chrome", (int)(Math.random() * 20) );
        jsonSubObject.put("safari", (int)(Math.random() * 20) );
        jsonSubObject.put("other", (int)(Math.random() * 20) );
        jsonList.add(jsonSubObject);

        //System.out.println(jsonList);
        
        return jsonList;

	}
	
	@RequestMapping(
			value = "/line",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ArrayList<Map<String, Object>>  test_line() {

        /**
         * [
         *
         * 	{type1 : 30, type2 : 100, type3 : 60},
         *  {type1 : 30, type2 : 100, type3 : 60},
         *  ...
         *  {type1 : 30, type2 : 100, type3 : 60}
         * 	
         * ]
         */

        Map<String, Object> jsonSubObject = null;
        ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        //1번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("apple", (int)(Math.random() * 100) );
        jsonSubObject.put("microsoft", (int)(Math.random() * 100) );
        jsonSubObject.put("oracle", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);
        
        //2번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("apple", (int)(Math.random() * 100) );
        jsonSubObject.put("microsoft", (int)(Math.random() * 100) );
        jsonSubObject.put("oracle", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);
        
        //3번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("apple", (int)(Math.random() * 100) );
        jsonSubObject.put("microsoft", (int)(Math.random() * 100) );
        jsonSubObject.put("oracle", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);
        
        //4번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("apple", (int)(Math.random() * 100) );
        jsonSubObject.put("microsoft", (int)(Math.random() * 100) );
        jsonSubObject.put("oracle", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);
        
        //System.out.println(jsonList);
        
        return jsonList;

	}

    @RequestMapping(
            value = "/thr_bar",
            method = { RequestMethod.GET },
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ArrayList<Map<String, Object>>  test_thr_bar() {

        /**
         * [
         *
         * 	{type1 : 30, type2 : 100, type3 : 60},
         *  {type1 : 30, type2 : 100, type3 : 60},
         *  ...
         *  {type1 : 30, type2 : 100, type3 : 60}
         *
         * ]
         */

        Map<String, Object> jsonSubObject = null;
        ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        //1번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("sales", (int)(Math.random() * 100) );
        jsonSubObject.put("profit", (int)(Math.random() * 100) );
        jsonSubObject.put("total", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //2번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("sales", (int)(Math.random() * 100) );
        jsonSubObject.put("profit", (int)(Math.random() * 100) );
        jsonSubObject.put("total", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //3번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("sales", (int)(Math.random() * 100) );
        jsonSubObject.put("profit", (int)(Math.random() * 100) );
        jsonSubObject.put("total", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //4번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("sales", (int)(Math.random() * 100) );
        jsonSubObject.put("profit", (int)(Math.random() * 100) );
        jsonSubObject.put("total", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //System.out.println(jsonList);

        return jsonList;

    }

    @RequestMapping(
            value = "/bas_bar",
            method = { RequestMethod.GET },
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ArrayList<Map<String, Object>>  test_bas_bar() {

        /**
         * [
         *
         * 	{type1 : "1Q", type2 : 100, type3 : 60},
         *  {type1 : 2Q", type2 : -100, type3 : 60},
         *  ...
         *  {type1 : 3Q", type2 : -100, type3 : 60}
         *
         * ]
         */

        Map<String, Object> jsonSubObject = null;
        ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        //1번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("quarter", "1Q" );
        jsonSubObject.put("sales", (int)(Math.random() * 100 - 50) );
        jsonSubObject.put("profit", (int)(Math.random() * 100- 50) );
        jsonList.add(jsonSubObject);

        //2번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("quarter", "1Q" );
        jsonSubObject.put("sales", (int)(Math.random() * 100 - 50) );
        jsonSubObject.put("profit", (int)(Math.random() * 100- 50) );
        jsonList.add(jsonSubObject);

        //3번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("quarter", "1Q" );
        jsonSubObject.put("sales", (int)(Math.random() * 100 - 50) );
        jsonSubObject.put("profit", (int)(Math.random() * 100- 50) );
        jsonList.add(jsonSubObject);

        //4번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("quarter", "1Q" );
        jsonSubObject.put("sales", (int)(Math.random() * 100 - 50) );
        jsonSubObject.put("profit", (int)(Math.random() * 100- 50) );
        jsonList.add(jsonSubObject);

        //System.out.println(jsonList);

        return jsonList;

    }

    @RequestMapping(
            value = "/bubble",
            method = { RequestMethod.GET },
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ArrayList<Map<String, Object>>  test_bubble() {

        /**
         * [
         *
         * 	{type1 : "1Q", type2 : 100, type3 : 60},
         *  {type1 : 2Q", type2 : 100, type3 : 60},
         *  ...
         *  {type1 : 3Q", type2 : 100, type3 : 60}
         *
         * ]
         */

        Map<String, Object> jsonSubObject = null;
        ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        //1번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("quarter", "1Q" );
        jsonSubObject.put("sales", (int)(Math.random() * 100) );
        jsonSubObject.put("profit", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //2번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("quarter", "1Q" );
        jsonSubObject.put("sales", (int)(Math.random() * 100) );
        jsonSubObject.put("profit", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //3번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("quarter", "1Q" );
        jsonSubObject.put("sales", (int)(Math.random() * 100) );
        jsonSubObject.put("profit", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //4번째 데이터
        jsonSubObject = new HashMap<String, Object>();
        jsonSubObject.put("quarter", "1Q" );
        jsonSubObject.put("sales", (int)(Math.random() * 100) );
        jsonSubObject.put("profit", (int)(Math.random() * 100) );
        jsonList.add(jsonSubObject);

        //System.out.println(jsonList);

        return jsonList;

    }

}

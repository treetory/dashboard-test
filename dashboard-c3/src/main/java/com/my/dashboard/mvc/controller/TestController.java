package com.my.dashboard.mvc.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;

import com.deviceatlas.cloud.deviceidentification.client.Client;
import com.deviceatlas.cloud.deviceidentification.client.ClientException;
import com.deviceatlas.cloud.deviceidentification.client.Result;
import com.deviceatlas.cloud.deviceidentification.endpoint.EndPoint;
import com.my.dashboard.mvc.service.DeviceDetectionService;
import com.my.dashboard.mvc.service.LetseeService;
import com.my.dashboard.mvc.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

	private WebSocketService wss;

	private LetseeService ls;

	private DeviceDetectionService dds;

	public TestController(WebSocketService wss, LetseeService ls, DeviceDetectionService dds) {
		this.wss = wss;
		this.ls = ls;
		this.dds = dds;
	}

	@RequestMapping(value = "/line",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, List<Object>>> getLineData(HttpServletRequest request, HttpServletResponse response) {

		return wss.getLineData();
	}

	@RequestMapping(value = "/timeSeries",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, List<Object>>> getTimeSeriesData(HttpServletRequest request, HttpServletResponse response) {

		return wss.getTimeSeriesData();
	}

	@RequestMapping(value = "/Spline",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, List<Object>>> getSplineData(HttpServletRequest request, HttpServletResponse response) {

		return wss.getSplineData();
	}

    @RequestMapping(value = "/step",
            method = { RequestMethod.GET },
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, List<Object>>> getStepData(HttpServletRequest request, HttpServletResponse response) {

        return wss.getLineData();
    }

    @RequestMapping(value = "/areastep",
            method = { RequestMethod.GET },
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, List<Object>>> getAreaStepData(HttpServletRequest request, HttpServletResponse response) {

        return wss.getLineData();
    }

	@RequestMapping(value = "/multipleXY",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, List<Object>>> getMultipleXYData(HttpServletRequest request, HttpServletResponse response) {

		return wss.getMultipleXYData();
	}

	@RequestMapping(value = "/loadingTime",
			method = { RequestMethod.POST },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public String insertLoadingTime(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, List<Map<String, Object>>> body) {
		return ls.insert(request.getHeader("user-agent"), body);
	}

	@RequestMapping(value = "/opticalFlow",
			method = { RequestMethod.POST },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public String insertOpticalFlowTestResult(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> test) {
		return ls.insertOpticalFlowTestResult(request.getHeader("user-agent"), test);
	}

	@RequestMapping(value = "/deviceTest",
		method = {RequestMethod.GET},
		produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
		consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE }
	)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> deviceTest(HttpServletRequest request, HttpServletResponse response) {
		return dds.test(request);
	}

	@RequestMapping(value = "/modelName",
			method = {RequestMethod.GET},
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE }
	)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> deviceDetect(HttpServletRequest request, HttpServletResponse response) {
		return dds.deviceDetect(request);
	}
}

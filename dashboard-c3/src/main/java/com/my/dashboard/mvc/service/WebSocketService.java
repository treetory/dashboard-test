package com.my.dashboard.mvc.service;

import java.util.List;
import java.util.Map;

public interface WebSocketService {

    public List<Map<String, List<Object>>> getLineData();

    public List<Map<String, List<Object>>> getTimeSeriesData();

    public List<Map<String, List<Object>>> getSplineData();

    public List<Map<String, List<Object>>> getMultipleXYData();
}

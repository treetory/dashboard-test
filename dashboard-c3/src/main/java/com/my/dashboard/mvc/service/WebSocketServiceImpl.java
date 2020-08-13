package com.my.dashboard.mvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServiceImpl.class);

    @Override
    public List<Map<String, List<Object>>> getLineData() {

        /**
         * [
         * 	"key-1" : ["v1", "v2", ... , "vn"],
         * 	"key-2" : ["v1", "v2", ... , "vn"],
         * 	...
         * 	"ken-n" : ["v1", "v2", ... , "vn"]
         *
         * ]
         */

        List<Map<String, List<Object>>> data = new ArrayList<Map<String, List<Object>>>();

        Map<String, List<Object>> data1 = new HashMap<>();

        ArrayList<Object> data1_arr = new ArrayList<>();
        for (int i=5; i>0; i--) {
            data1_arr.add(Integer.valueOf((int) (Math.random() * 10) + 1));
        }

        data1.put("data1", data1_arr);

        data.add(data1);

        Map<String, List<Object>> data2 = new HashMap<>();

        ArrayList<Object> data2_arr = new ArrayList<>();
        for (int i=5; i>0; i--) {
            data2_arr.add((int) (Math.random() * 10) + 1);
        }

        data2.put("data2", data2_arr);

        data.add(data2);

        return data;
    }

    @Override
    public List<Map<String, List<Object>>> getTimeSeriesData() {

        List<Map<String, List<Object>>> data = this.getLineData();

        Map<String, List<Object>> x = new HashMap<>();

        ArrayList<Object> x_arr = new ArrayList<>();
        for (int i=5; i>0; i--) {
            x_arr.add(this.getRandomDate());
        }

        x.put("x", x_arr);

        data.add(x);

        return data;
    }

    private static int cumulated_day = 0;

    private static final long current = System.currentTimeMillis();

    @Override
    public List<Map<String, List<Object>>> getSplineData() {

        List<Map<String, List<Object>>> data = this.getLineData();

        Map<String, List<Object>> x = new HashMap<>();

        ArrayList<Object> x_arr = new ArrayList<>();
        for (int i=1; i<6; i++) {
            x_arr.add(this.getCumulatedDate());
        }

        //LOG.debug("{} -> {}", cumulated_day, x_arr);

        x.put("x", x_arr);

        data.add(x);

        return data;
    }

    @Override
    public List<Map<String, List<Object>>> getMultipleXYData() {

        List<Map<String, List<Object>>> data = this.getLineData();

        Map<String, List<Object>> x1 = new HashMap<>();

        ArrayList<Object> x1_arr = new ArrayList<>();
        for (int i=5; i>0; i--) {
            x1_arr.add((int) (Math.random() * 10) + 1);
        }

        x1.put("x1", x1_arr);

        Map<String, List<Object>> x2 = new HashMap<>();

        ArrayList<Object> x2_arr = new ArrayList<>();
        for (int i=5; i>0; i--) {
            x2_arr.add((int) (Math.random() * 20) + 1);
        }

        x2.put("x2", x2_arr);

        data.add(x1);
        data.add(x2);

        return data;
    }

    private Object getCumulatedDate() {

        cumulated_day++;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(current));
        c.add(Calendar.DATE, cumulated_day);

        return sdf.format(c.getTime());
    }

    private String getRandomDate() {

        GregorianCalendar gc = new GregorianCalendar();

        int year = this.randBetween(2018, 2018);

        gc.set(Calendar.YEAR, year);

        int dayOfYear = this.randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH);
    }

    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}

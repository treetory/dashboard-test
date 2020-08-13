package com.my.dashboard.mvc.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LetseeService {

    private static final Logger LOG = LoggerFactory.getLogger(LetseeService.class);

    private final Gson gson;

    public LetseeService(Gson gson) {
        this.gson = gson;
    }

    public String insert(String header, Map<String, List<Map<String, Object>>> data) {

        StringBuffer sb = new StringBuffer();
        String modelName = "";

        Pattern pattern = Pattern.compile("[\\(][\\w\\d\\s-;.\\/]+[\\)]");
        Matcher matcher = pattern.matcher(header);

        if (matcher.find()) {
            modelName = matcher.group();
        }
        sb.append(System.lineSeparator());
        sb.append("-------------------------------------");
        sb.append(modelName);
        sb.append("-------------------------------------");
        sb.append(System.lineSeparator());

        sb = getData("sdk", data.get("sdk"), sb);
        sb = getData("wasm", data.get("wasm"), sb);

        String[] strs = modelName.substring(1, modelName.length()-1).split(";");

        this.write(strs[strs.length-1].trim(), sb.toString());

        return "OK";
    }

    private String getModelName(String header) {

        String modelName = "";

        Pattern pattern = Pattern.compile("[\\(][\\w\\d\\s-;.\\/]+[\\)]");
        Matcher matcher = pattern.matcher(header);

        if (matcher.find()) {
            modelName = matcher.group();
        }

        String[] names = modelName.substring(1, modelName.length()-1).split(";");

        return names[names.length-1].trim();
    }

    private StringBuffer getData(String type, List<Map<String, Object>> body, StringBuffer sb) {

        sb.append("=====================================");
        sb.append(System.lineSeparator());
        sb.append(type);
        sb.append(System.lineSeparator());
        sb.append("=====================================");
        sb.append(System.lineSeparator());

        body.forEach(log -> {
            Set<Map.Entry<String, Object>> entry = log.entrySet();
            Iterator<Map.Entry<String, Object>> ir = entry.iterator();
            while(ir.hasNext()) {
                Map.Entry e = ir.next();
                sb.append(e.getKey());
                sb.append(" : ");
                sb.append(e.getValue());
                sb.append(System.lineSeparator());

            }
            sb.append("+++++++++++++++++++++++++++++++++++++");
            sb.append(System.lineSeparator());
        });

        return sb;
    }

    private void write(String modelName, String str) {

        try {

            Path filePath = Paths.get(String.format("src/main/resources/log/%s_%d.txt", modelName.replace("/", "-"), System.currentTimeMillis()));
            Path path = Files.createFile(filePath);
            FileWriter fw = new FileWriter(new File(path.toUri()));
            fw.write(str);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String insertOpticalFlowTestResult(String header, Map<String, Object> test) {

        try {

            test.put("modelName", this.getModelName(header).replace("/", "-"));

            Path filePath = Paths.get(String.format("src/main/resources/log/%s-%s-%d.json", test.get("fileName").toString(), test.get("modelName").toString(), System.currentTimeMillis()));
            Path path = Files.createFile(filePath);
            FileWriter fw = new FileWriter(new File(path.toUri()));
            fw.write(gson.toJson(test));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "OK";
    }
}

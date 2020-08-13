package com.my.dashboard.mvc.service;

import com.deviceatlas.cloud.deviceidentification.client.*;
import com.deviceatlas.cloud.deviceidentification.endpoint.EndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class DeviceDetectionService {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceDetectionService.class);

    public DeviceDetectionService(Client client) {
        this.client = client;
    }

    private Client client;

    public Client getClient() {
        return client;
    }

    private Result getCloudResponse(Map<String, String> httpHeaders) throws ClientException {
        return client.getResult(httpHeaders);
    }

    public Map<String, Object> test(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();

        Result cloudData;
        Map <String, String> headers = new HashMap<String, String>();
        String source = "-";

        try {

            Map<String, String> reqHeaders = new HashMap<>();
            Enumeration<String> headerNames = request.getHeaderNames();
            while(headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                reqHeaders.put(name, request.getHeader(name));
            }

            cloudData = this.getCloudResponse(reqHeaders);
            headers = cloudData.getHeaders();
            source = cloudData.getSource();
            source = source.substring(0, 1).toUpperCase() + source.substring(1);

            Client c = this.getClient();
            EndPoint e[] = null;

            e = c.getEndPoints();

            String cloudUrl = c.getCloudUrl();
            if (cloudUrl != null) {
                cloudUrl = cloudUrl.replace("/v1/detect/properties", "").replace("http://", "").replace(":80", "");
            } else {
                cloudUrl = "-";
            }

            result.put("headers", headers);
            result.put("source", source);
            result.put("endpoints", e);
            result.put("cloudUrl", cloudUrl);
            result.put("apiVersion", c.getVersion());
            result.put("licenceKey", c.getLicenceKey());
            result.put("useCache", c.getUseCache());
            result.put("sendExtraHeaders", c.getSendExtraHeaders());
            result.put("useClientCookie", c.getUseClientCookie());

        } catch (ClientException ex) {
            LOG.error("{}", ex.getMessage());
        }

        return result;
    }

    public Map<String, String> deviceDetect(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();

        Result cloudData;

        try {

            Map<String, String> reqHeaders = new HashMap<>();
            Enumeration<String> headerNames = request.getHeaderNames();
            while(headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                reqHeaders.put(name, request.getHeader(name));
            }

            cloudData = this.getCloudResponse(reqHeaders);
            Properties properties = cloudData.getProperties();

            Iterator<Map.Entry<String, Property>> ir = properties.entrySet().iterator();

            while (ir.hasNext()) {
                Map.Entry<String, Property> entry = ir.next();
                if (entry.getValue().getDataType().equals("String")) {
                    result.put(entry.getKey(), entry.getValue().asString());
                }
            }

        } catch (ClientException ex) {
            LOG.error("{}", ex.getMessage());
        }

        System.out.println(result.toString());

        return result;
    }
}

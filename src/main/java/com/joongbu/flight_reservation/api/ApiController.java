package com.joongbu.flight_reservation.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final String END_POINT = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService";
    private final String ENCODOE_KEY = "D3j1vRFzobgXWnqUIARqQGVsV9YEe0KK6GVqIpdNGb8hF9hfUtvp9bddjYyda%2BoXJ%2FQ0bCyPxeZJk1hvOsLSQA%3D%3D";
    private final String DECODE_KEY = "D3j1vRFzobgXWnqUIARqQGVsV9YEe0KK6GVqIpdNGb8hF9hfUtvp9bddjYyda+oXJ/Q0bCyPxeZJk1hvOsLSQA==";


    public String airflightAPI() throws IOException {
        // urlBuilder.append("?" + )
        return "";
    }

    @GetMapping("/jsonapi")
    public String airlineInfo() throws IOException {

        StringBuffer result = new StringBuffer();
        HttpURLConnection conn = null;
        BufferedReader rd = null;

        try {
            StringBuilder urlBuilder = new StringBuilder(END_POINT);
            urlBuilder.append("/getAirmanList");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey",StandardCharsets.UTF_8) + "="+ENCODOE_KEY); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("_type",StandardCharsets.UTF_8) + "=" + URLEncoder.encode("json", StandardCharsets.UTF_8)); /*데이터 타입(xml, json)*/
            URL url = new URL(urlBuilder.toString());

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }else{
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while((line=rd.readLine())!= null){
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rd!= null)
                rd.close();
            if(conn != null)
                conn.disconnect();
        }

        return result.toString();
    }
}

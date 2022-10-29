package com.joongbu.flight_reservation.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final String END_POINT = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService";
    private final String ENCODOE_KEY = "D3j1vRFzobgXWnqUIARqQGVsV9YEe0KK6GVqIpdNGb8hF9hfUtvp9bddjYyda%2BoXJ%2FQ0bCyPxeZJk1hvOsLSQA%3D%3D";
    private final String DECODE_KEY = "D3j1vRFzobgXWnqUIARqQGVsV9YEe0KK6GVqIpdNGb8hF9hfUtvp9bddjYyda+oXJ/Q0bCyPxeZJk1hvOsLSQA==";

    @GetMapping("/airflightJson/{airlineId}/{depAirportId}/{arrAirportId}/{depPlandTime}")
    public String airflightInfo(@PathVariable("airlineId") String airlineId, @PathVariable("depAirportId")String depAirportId, @PathVariable("arrAirportId") String arrAirportId, @PathVariable("depPlandTime") String depPlandTime) throws IOException {

        StringBuffer result = new StringBuffer();
        HttpURLConnection conn = null;
        BufferedReader rd = null;

        JSONObject resultJson = null;

        try {
            StringBuilder urlBuilder = new StringBuilder(END_POINT);
            urlBuilder.append("/getFlightOpratInfoList");

            urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + ENCODOE_KEY); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("_type", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("json", StandardCharsets.UTF_8)); /*데이터 타입(xml, json)*/
            urlBuilder.append("&" + URLEncoder.encode("depAirportId",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(depAirportId, StandardCharsets.UTF_8)); /*출발공항ID*/
            urlBuilder.append("&" + URLEncoder.encode("arrAirportId",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(arrAirportId, StandardCharsets.UTF_8)); /*도착공항ID*/
            urlBuilder.append("&" + URLEncoder.encode("depPlandTime",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(depPlandTime, StandardCharsets.UTF_8)); /*출발일(YYYYMMDD)*/
            urlBuilder.append("&" + URLEncoder.encode("airlineId",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(airlineId, StandardCharsets.UTF_8)); /*항공사ID*/

            URL url = new URL(urlBuilder.toString());

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            JSONParser parser = new JSONParser();
            resultJson = (JSONObject) parser.parse(result.toString());
            JSONArray dataArr = (JSONArray) resultJson.get("item");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rd != null)
                rd.close();
            if (conn != null)
                conn.disconnect();
        }


        return result.toString();
    }

    @GetMapping("/airlineJson")
    public JSONArray airlineInfo() throws IOException {

        StringBuffer result = new StringBuffer();
        HttpURLConnection conn = null;
        BufferedReader rd = null;
        JSONObject resultJson = null;

        JSONArray airlineJsonArr = null;
        try {
            StringBuilder urlBuilder = new StringBuilder(END_POINT);
            urlBuilder.append("/getAirmanList");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + ENCODOE_KEY); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("_type", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("json", StandardCharsets.UTF_8)); /*데이터 타입(xml, json)*/
            URL url = new URL(urlBuilder.toString());

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            JSONParser parser = new JSONParser();
            resultJson = (JSONObject) parser.parse(result.toString());
            resultJson = (JSONObject) resultJson.get("response");
            resultJson = (JSONObject) resultJson.get("body");
            resultJson = (JSONObject) resultJson.get("items");
            airlineJsonArr = (JSONArray) resultJson.get("item");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rd != null)
                rd.close();
            if (conn != null)
                conn.disconnect();
        }

        return airlineJsonArr;
    }

    @GetMapping("/airportJson")
    public  String airportInfo() throws IOException {

        StringBuffer result = new StringBuffer();
        HttpURLConnection conn = null;
        BufferedReader rd = null;

        try {
            StringBuilder urlBuilder = new StringBuilder(END_POINT);
            urlBuilder.append("/getArprtList");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + ENCODOE_KEY); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("_type", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("json", StandardCharsets.UTF_8)); /*데이터 타입(xml, json)*/
            URL url = new URL(urlBuilder.toString());

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rd != null)
                rd.close();
            if (conn != null)
                conn.disconnect();
        }

        return result.toString();
    }
}

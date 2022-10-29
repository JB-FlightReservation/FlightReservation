package com.joongbu.flight_reservation.api;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiController {
    private final String END_POINT = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService";
    private final String ENCODOE_KEY = "D3j1vRFzobgXWnqUIARqQGVsV9YEe0KK6GVqIpdNGb8hF9hfUtvp9bddjYyda%2BoXJ%2FQ0bCyPxeZJk1hvOsLSQA%3D%3D";
    private final String DECODE_KEY = "D3j1vRFzobgXWnqUIARqQGVsV9YEe0KK6GVqIpdNGb8hF9hfUtvp9bddjYyda+oXJ/Q0bCyPxeZJk1hvOsLSQA==";

    public List<Element> airflightInfo(String depAirportId, String arrAirportId, String depPlandTime) throws IOException {

        StringBuffer result = new StringBuffer();
        HttpURLConnection conn = null;

        List<Element> item_list = null;

        try {
            StringBuilder urlBuilder = new StringBuilder(END_POINT);
            urlBuilder.append("/getFlightOpratInfoList");

            urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + ENCODOE_KEY); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("_type", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("xml", StandardCharsets.UTF_8)); /*데이터 타입(xml, json)*/
            urlBuilder.append("&" + URLEncoder.encode("depAirportId",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(depAirportId, StandardCharsets.UTF_8)); /*출발공항ID*/
            urlBuilder.append("&" + URLEncoder.encode("arrAirportId",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(arrAirportId, StandardCharsets.UTF_8)); /*도착공항ID*/
            urlBuilder.append("&" + URLEncoder.encode("depPlandTime",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(depPlandTime, StandardCharsets.UTF_8)); /*출발일(YYYYMMDD)*/

            URL url = new URL(urlBuilder.toString());

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                SAXBuilder builder = new SAXBuilder();
                Document document = builder.build(conn.getInputStream());
                Element root = document.getRootElement();
                Element body = root.getChild("body");
                Element items = body.getChild("items");
                item_list = items.getChildren("item");
            } else {
                System.out.println("ERROR] api connection error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }


        return item_list;
    }

    public List<Element> airlineInfo() throws IOException {

        StringBuffer result = new StringBuffer();
        HttpURLConnection conn = null;
        List<Element> item_list = null;

        try {
            StringBuilder urlBuilder = new StringBuilder(END_POINT);
            urlBuilder.append("/getAirmanList");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + ENCODOE_KEY); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("_type", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("xml", StandardCharsets.UTF_8)); /*데이터 타입(xml, json)*/
            URL url = new URL(urlBuilder.toString());

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                SAXBuilder builder = new SAXBuilder();
                Document document = builder.build(conn.getInputStream());
                Element root = document.getRootElement();
                Element body = root.getChild("body");
                Element items = body.getChild("items");
                item_list = items.getChildren("item");
            } else {
                System.out.println("ERROR] api connection error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (conn != null)
                conn.disconnect();
        }

        return item_list;
    }


    public List<Element> airportInfo() throws IOException {

        StringBuffer result = new StringBuffer();
        HttpURLConnection conn = null;
        List<Element> item_list = null;

        try {
            StringBuilder urlBuilder = new StringBuilder(END_POINT);
            urlBuilder.append("/getArprtList");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + ENCODOE_KEY); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("_type", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("xml", StandardCharsets.UTF_8)); /*데이터 타입(xml, json)*/
            URL url = new URL(urlBuilder.toString());

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                SAXBuilder builder = new SAXBuilder();
                Document document = builder.build(conn.getInputStream());
                Element root = document.getRootElement();
                Element body = root.getChild("body");
                Element items = body.getChild("items");
                item_list = items.getChildren("item");

            } else {
                System.out.println("ERROR] api connection error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }

        return item_list;
    }
}

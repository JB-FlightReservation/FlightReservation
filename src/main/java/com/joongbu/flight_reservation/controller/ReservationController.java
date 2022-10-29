package com.joongbu.flight_reservation.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.joongbu.flight_reservation.api.ApiController;
import com.joongbu.flight_reservation.vo.AirflightVO;
import com.joongbu.flight_reservation.vo.AirportVO;
import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.joongbu.flight_reservation.dto.AirflightDto;
import com.joongbu.flight_reservation.dto.PassengerInfoDto;
import com.joongbu.flight_reservation.dto.PassengerRP;
import com.joongbu.flight_reservation.dto.ReservationDto;
import com.joongbu.flight_reservation.dto.ReservationProgress;
import com.joongbu.flight_reservation.mapper.AirflightMapper;
import com.joongbu.flight_reservation.mapper.ReservationMapper;

@RequestMapping("/reservation")
@Controller
public class ReservationController {

    @Autowired
    AirflightMapper afMapper;
    ReservationMapper rMapper;

    ReservationProgress rp = new ReservationProgress();
    PassengerRP pList = new PassengerRP();

    // ------------- 예매 1 ------------------

    //TODO: main 페이지의 예매 페이지와 연동하기
    @GetMapping("/temp.do")
    public String tempPage(Model model) throws IOException {
        ApiController api = new ApiController();
        List<Element> airport = api.airportInfo();

        int idx = 0;
        AirportVO[] apVOs = new AirportVO[airport.size()];
        for (Element item : airport) {
            String airportId = item.getChildText("airportId").substring(4);
            String airportNm = item.getChildText("airportNm");

            AirportVO vo = new AirportVO(airportId, airportNm);
            apVOs[idx++] = vo;
        }
        model.addAttribute("airportList", apVOs);
        return "reservation/temp";
    }
    
    //TODO: main 페이지의 예매 페이지와 연동하기
    @PostMapping("/temp.do")
    public String reseravtionInputForm (
            HttpSession session,
            @RequestParam(value="depAprt") String depAprt,
            @RequestParam(value="landAprt") String landAprt,
            @RequestParam(value="depDate") String depDate
            )throws IOException{
        String tempDate = depDate.replace("-","");

        ApiController api = new ApiController();
        List<Element> airflight = api.airflightInfo("NAAR"+depAprt, "NAAR"+landAprt, tempDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
        int idx = 0;
        AirflightVO[] afVOs = new AirflightVO[airflight.size()];
        for (Element item : airflight) {
            String vihicleId = item.getChildText("vihicleId");
            String airlineNm = item.getChildText("airlineNm");

            String depPlandTime = item.getChildText("depPlandTime");
            LocalDateTime parse = LocalDateTime.parse(depPlandTime, formatter);
            depPlandTime = formatter2.format(parse);
            String arrPlandTime = item.getChildText("arrPlandTime");
            parse = LocalDateTime.parse(arrPlandTime, formatter);
            arrPlandTime = formatter2.format(parse);
            String economyCharge = item.getChildText("economyCharge");
            String prestigeCharge = item.getChildText("prestigeCharge");
            String depAirportNm = item.getChildText("depAirportNm");
            String arrAirportNm = item.getChildText("arrAirportNm");

            AirflightVO vo = new AirflightVO(vihicleId, airlineNm, depPlandTime, arrPlandTime, economyCharge, prestigeCharge, depAirportNm, arrAirportNm);
            afVOs[idx++] = vo;
        }
        if(afVOs.length > 0){
            session.setAttribute("depAprt", "NAAR"+depAprt);
            session.setAttribute("landAprt", "NAAR"+landAprt);
            session.setAttribute("depDate", depDate);
            session.setAttribute("airflightList", afVOs);
            return "redirect:/reservation/booking.do";
        }else{
            return "redirect:/reservation/temp.do";
        }
    }

    @GetMapping("/booking.do")
    public String booking(HttpSession session, Model model) throws Exception {
        ApiController api = new ApiController();
        model.addAttribute("depAprt", ((String)session.getAttribute("depAprt")).substring(4));
        model.addAttribute("landAprt", ((String)session.getAttribute("landAprt")).substring(4));
        model.addAttribute("depDate", session.getAttribute("depDate"));
        AirflightVO[] airflightList = (AirflightVO[]) session.getAttribute("airflightList");
        model.addAttribute("airflightList", airflightList);

        List<Element> airport = api.airportInfo();

        int idx = 0;
        AirportVO[] apVOs = new AirportVO[airport.size()];
        for (Element item : airport) {
            String airportId = item.getChildText("airportId").substring(4);
            String airportNm = item.getChildText("airportNm");

            AirportVO vo = new AirportVO(airportId, airportNm);
            apVOs[idx++] = vo;
        }
        model.addAttribute("airportList", apVOs);
        return "reservation/reservationSearch";
    }

    // ------------- 예매 2 ------------------
    @GetMapping("/passenger_list.do")
    public String passenger() {
        return "reservation/passenger_list";
    }

    @PostMapping("/passenger_list.do")
    public void passenger(PassengerInfoDto dto, @SessionAttribute PassengerInfoDto sdto, ReservationDto r, AirflightDto a, HttpSession session) {
        sdto.setPgBirth(dto.getPgBirth());
//		session.setAttribute("passenger", dto);
    }

    // ------------- 예매 3 ------------------
    @GetMapping("/baggage.do")
    public void baggage() {

    }

    // ------------- 예매 4 ------------------
    @GetMapping("/terms.do")
    public String terms() {
        return "reservation/reservationTerms";
    }

    // ------------- 예매 5 ------------------
    @GetMapping("/pay.do")
    public String pay() {
        return "reservation/reservationPay";
    }

    @GetMapping("/payComplete.do")
    public String payComplete() {
        return "reservation/reservationPayComplete";
    }


}

package com.joongbu.flight_reservation.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.joongbu.flight_reservation.api.ApiController;
import com.joongbu.flight_reservation.dto.SelectInfo;
import com.joongbu.flight_reservation.vo.AirflightVO;
import com.joongbu.flight_reservation.vo.AirportVO;
import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.joongbu.flight_reservation.dto.AirflightDto;
import com.joongbu.flight_reservation.dto.AirlineDto;
import com.joongbu.flight_reservation.dto.AirportDto;

import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.dto.PassengerInfoDto;
import com.joongbu.flight_reservation.dto.PriceDto;
import com.joongbu.flight_reservation.dto.ReservationDto;
import com.joongbu.flight_reservation.mapper.AirflightMapper;
import com.joongbu.flight_reservation.mapper.ReservationMapper;

@RequestMapping("/reservation")
@Controller
public class ReservationController {
    @Autowired
    AirflightMapper afMapper;
    ReservationMapper rMapper;

    // ------------- 예매 1 ------------------

    //TODO: main 페이지의 예매 페이지와 연동하기

    @GetMapping("/flightsearch.do")
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
        return "reservation/flightsearch";
    }

    //TODO: main 페이지의 예매 페이지와 연동하기
    @PostMapping("/temp.do")
    public String reseravtionInputForm(
            HttpSession session,
            SelectInfo si
    ) throws IOException {
        String tempDate = si.getDepDate().replace("-", "");
        ApiController api = new ApiController();

        List<Element> airflight = api.airflightInfo("NAAR" + si.getDepAprt(), "NAAR" + si.getLandAprt(), tempDate);

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
        if (afVOs.length > 0) {
            session.setAttribute("selectInfo", si);
            session.setAttribute("airflightList", afVOs);
            return "redirect:/reservation/booking.do";
        } else {
            return "redirect:/reservation/temp.do";
        }
    }

    @GetMapping("/booking.do")
    public String booking(@SessionAttribute SelectInfo si,@SessionAttribute AirflightVO[] afs, Model model) throws Exception {
        ApiController api = new ApiController();
        model.addAttribute("depAprt", si.getDepAprt().substring(4));
        model.addAttribute("landAprt", si.getLandAprt().substring(4));
        model.addAttribute("depDate", si.getDepDate());
        model.addAttribute("airflightList", afs);

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

    @PostMapping("/book")
    public String firstBook() {
        return "redirect:/reservation/passenger.do";
    }

    // ------------- 예매 2 ------------------
    @GetMapping("/passenger.do")
    public String passenger() {
        return "reservation/passenger";
    }

    @PostMapping("/passenger")
    public String passengerInput(PassengerInfoDto pDto, ReservationDto rDto, HttpSession session,
                                 @SessionAttribute(required = false) CustomerDto loginCt) {

        pDto.getP().get(0).setPgBirth(pDto.getPgBirth());
        session.setAttribute("rSession", rDto); // ReservationDto session

//        rSession.setRvEmail(rDto.getRvEmail());
//        rSession.setRvPhone(rDto.getRvPhone());

		session.setAttribute("pSession", pDto);

		return "redirect:/reservation/baggage.do";
	}

	// ------------- 예매 3 ------------------
	@GetMapping("/baggage.do")
	public String baggage(@SessionAttribute PassengerInfoDto pSession) {
		return "reservation/baggage";
	}

    @PostMapping("/baggage")
    public String baggageInput(PassengerInfoDto pDto, /*@SessionAttribute ReservationDto rSession,*/ @SessionAttribute PassengerInfoDto pSession) {
        for (int i = 0; i < pSession.getP().size(); i++) {
            pSession.getP().get(i).setPgBaggage(pDto.getP().get(i).getPgBaggage());
        }

        for (PassengerInfoDto p : pSession.getP()) {
            System.out.println("p->pgBaggage: " + p.getPgBaggage());
        }
		return "redirect:/reservation/terms.do";
	}

	// ------------- 예매 4 ------------------
	@GetMapping("/terms.do")

	public String terms(Model model, HttpSession session) {
		ReservationDto reservation = (ReservationDto) session.getAttribute("rSession"); // ReservationDto session
		AirflightDto airflight = (AirflightDto) session.getAttribute("aSession"); // AirflightDto session
		PriceDto price = (PriceDto) session.getAttribute("priceSession");
		AirportDto airport = (AirportDto) session.getAttribute("airportSession");

		// reservation
		if (reservation == null) {
			reservation = new ReservationDto();
			reservation.setRvDepartAp("2022-10-28(목)");
			reservation.setRvLandAp("2022-11-01(화)");

			session.setAttribute("rSession", reservation);
		}
		model.addAttribute("reservation", reservation);

		// airport
		if (airport == null) {
			airport = new AirportDto();
			airport.setApName("제주2");
			session.setAttribute("airportSession", airport);
		}
		model.addAttribute("airport", airport);

		// price
		if (price == null) {
			price = new PriceDto();
			price.setPrAirfare(10000);
			price.setPrAirportUsageFee(2000);
			price.setPrDiscount(0);
			price.setPrFuelSurcharge(5000);
			price.setPrTotal(price.getPrAirfare() + price.getPrAirportUsageFee() + price.getPrDiscount()
					+ price.getPrFuelSurcharge());
			session.setAttribute("priceSession", price);
		}
		model.addAttribute("price", price);

		// airflight
		if (airflight == null) {
			airflight = new AirflightDto();
			airflight.setAfEconomy(1111);
			airflight.setAfNo(1234);
			session.setAttribute("aSession", airflight);
		}
		model.addAttribute("airflight", airflight);

		AirlineDto airline = new AirlineDto();
		model.addAttribute("airline", airline);

		return "reservation/reservationTerms";
	}


	// ------------- 예매 5 ------------------
	@GetMapping("/pay.do")
	public String pay(Model model, HttpSession session) {
		PriceDto price = (PriceDto) session.getAttribute("priceSession");

		price.getPrTotal();
		return "reservation/reservationPay";
	}

	@GetMapping("/payComplete.do")
	public String payComplete(HttpSession session) {
		ReservationDto reservation = (ReservationDto) session.getAttribute("rSession"); // ReservationDto session
		AirflightDto airflight = (AirflightDto) session.getAttribute("aSession"); // AirflightDto session

		if (reservation == null)
			reservation = new ReservationDto();
		// test only
//		rv_no, ct_no, rv_phone, rv_email, rv_is_ticketed, rv_date, rv_mature_adult, 
//		rv_mature_teen, rv_mature_baby, rv_depart_ap, rv_land_ap, next_rv_no, rv_seat_grade
		reservation.setRvNo(6);
		reservation.setCtNo(1);
		reservation.setRvPhone("1");
		reservation.setRvEmail("1");
		reservation.setRvIsTicketed(1);
		reservation.setRvDate(new Date());
		reservation.setRvMatureAdult(1);
		reservation.setRvMatureTeen(1);
		reservation.setRvMatureBaby(1);
		reservation.setRvDepartAp("1");
		reservation.setRvLandAp("1");
		reservation.setNextRvNo(1);
		reservation.setRvSeatGrade("1");
//		System.out.println(reservation.getRvDepartAp());
		rMapper.insert(reservation);

		return "reservation/reservationPayComplete";
	}
}

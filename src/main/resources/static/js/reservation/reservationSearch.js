var swiper = new Swiper(".service_section_slide", {
	slidesPerView: 'auto',
	centeredSlides: true,
	navigation: {
		nextEl: ".btn_slide_next",
		prevEl: ".btn_slide_prev",
	},
	pagination: { // 페이징 설정
		el: '.swiper-pagination',
		clickable: true,
	},
});
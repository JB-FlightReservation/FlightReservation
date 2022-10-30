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
})

$("input[name='rvSeatGrade']").change(function(){
	let vihicleId = document.querySelector('#v_id');
	let airlineNm = document.querySelector('#al_nm');
	let depTime = document.querySelector('#dp_tm');
	let arrTime = document.querySelector('#ar_tm');
	let seatGrade = document.querySelector('#s_g');
	let seatCharge = document.querySelector('#s_g_c');

	let RootNode = $("input[name='rvSeatGrade']:checked");
	for(; RootNode.nodeName !== 'li'; RootNode = RootNode.parentElement);
	dirlineNm = RootNode.querySelector('.jb');
	var test = $("input[name='rvSeatGrade']:checked").val();
	alert(test);
});

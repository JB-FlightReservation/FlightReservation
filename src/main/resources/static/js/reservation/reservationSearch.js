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

	let RootNode = this;
	for(RootNode; RootNode.nodeName !== 'LI'; RootNode= RootNode.parentElement){}

	airlineNm.value = RootNode.querySelector('.jb.airline').innerText;
	vihicleId.value = RootNode.querySelector('.vihicle').innerText;
	depTime.value = RootNode.querySelector('.tit.depTime').innerText;
	arrTime.value = RootNode.querySelector('.tit.arrTime').innerText;
	if(this.id === 'normal'){
		seatGrade.value = "economy";
	} else if(this.id === 'business'){
		seatGrade.value = "business";
	}

	seatCharge.value = $("input[name='rvSeatGrade']:checked").val();

	// alert(
	// 	"vihicleId: " + vihicleId.value
	// 	+ "\nairlineNm: " + airlineNm.value
	// 	+ "\ndepTime: " + depTime.value
	// 	+ "\narrTime: " + arrTime.value
	// 	+ "\nseatGrade: " + seatGrade.value
	// 	+ "\nseatCharge: " + seatCharge.value
	// )
});

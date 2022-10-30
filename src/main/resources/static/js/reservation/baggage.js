var swiper = new Swiper("#secondSwiper", {
	slidesPerView: 'auto',
	spaceBetween: 30,
	centeredSlides: true,
	navigation: {
		nextEl: ".btn_slide_next",
		prevEl: ".btn_slide_prev",
	},
	
});

var slider = document.getElementById("myRange");

// output.innerHTML = slider.value; // Display the default slider value

// Update the current slider value (each time you drag the slider handle)
// slider.oninput = function() {
//   output.innerHTML = this.value;
//   output2.innerHTML = this.value;
// }

swiper.on('transitionEnd', function(e){
	let idx = document.querySelector('.swiper-slide-active.passenger').id.replace("passenger_","");
	let output = document.querySelector('.swiper-slide-active.passenger .add_price #p_bweight_'+idx);
	let baggageOutput = document.querySelector('#b_weight');
	let pgBaggage = document.querySelector('.add_price #baggage_value_'+idx);
	slider.value = output.innerText;
	baggageOutput.innerHTML = slider.value;
	pgBaggage.value = this.value;
})


slider.oninput = function(){
	let idx = document.querySelector('.swiper-slide-active.passenger').id.replace("passenger_","");
	let output = document.querySelector('.swiper-slide-active.passenger .add_price #p_bweight_'+idx);
	let baggageOutput = document.querySelector('#b_weight');
	let pgBaggage = document.querySelector('.add_price #baggage_value_'+idx);
	output.innerHTML = this.value;
	pgBaggage.value = this.value;
	baggageOutput.innerHTML = this.value;
	slider.value = this.value;
}
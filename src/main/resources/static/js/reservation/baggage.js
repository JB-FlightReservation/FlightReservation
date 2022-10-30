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
var output = document.getElementById("p_bweight");
var output2 = document.getElementById("b_weight");

output.innerHTML = slider.value; // Display the default slider value

// Update the current slider value (each time you drag the slider handle)
slider.oninput = function() {
  output.innerHTML = this.value;
  output2.innerHTML = this.value;
}

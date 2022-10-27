var swiper = new Swiper("#secondSwiper", {
	slidesPerView: 'auto',
	spaceBetween: 30,
	centeredSlides: true,
	navigation: {
		nextEl: ".btn_slide_next",
		prevEl: ".btn_slide_prev",
	},
});

$('input[type="range"]').rangeslider();

// Destroy all plugin instances created from the
// e.g. $('input[type="range"]') elements.
$('input[type="range"]').rangeslider('destroy');

// Update all rangeslider instances for all
// e.g. $('input[type="range"]') elements.
// Usefull if you changed some attributes e.g. `min` or `max` etc.
$('input[type="range"]').rangeslider('update', true);
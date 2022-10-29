function toggle(event) {
	var pa = event.parentNode;
	pa.classList.toggle('on');
}

var birth = "";

function selectDate(event) {
	var va = event.value;
	// console.log(va);

	birth += va;
	console.log(birth);
}
// $('input[name=(p[0].pgBirth)]').attr('value', birth);
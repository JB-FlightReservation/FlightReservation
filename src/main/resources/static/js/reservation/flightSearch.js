var number_st = $('#adult_count').val();
var num = parseInt(number_st);

$('#aPlus').click(function() {
	num += 1;
	$('input[name=rvMatureAdult]').attr('value', num);

})

$('#aMinus').click(function() {
	if (num > 0) {
		num -= 1;
		$('input[name=rvMatureAdult]').attr('value', num);
	} else {
		alert("0보다 작을 수 없습니다.");
	}
})


var tnumber_st = $('#teen_count').val();
var tnum = parseInt(tnumber_st);

$('#tPlus').click(function() {
	tnum += 1;
	$('input[name=rvMatureTeen]').attr('value', tnum);

})

$('#tMinus').click(function() {
	if (tnum > 0) {
		tnum -= 1;
		$('input[name=rvMatureTeen]').attr('value', tnum);
	} else {
		alert("0보다 작을 수 없습니다.");
	}
})

var bnumber_st = $('#baby_count').val();
var bnum = parseInt(bnumber_st);

$('#bPlus').click(function() {
	bnum += 1;
	$('input[name=rvMatureBaby]').attr('value', bnum);

})

$('#bMinus').click(function() {
	if (bnum > 0) {
		bnum -= 1;
		$('input[name=rvMatureBaby]').attr('value', bnum);
	} else {
		alert("0보다 작을 수 없습니다.");
	}
})
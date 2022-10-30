//const otpNum = document.getElementById('otpNum');
//const btnAuthNo=document.getElementById('btnAuthNo');


$(document).on('click','#btnAuthNo',function(){
	send();
	
});

function send(){
	let name = $('#inputName').val();
	let email = $('#inputEmail').val();
	
	$.ajax({
		url: '/login/findId.do',
		type: 'post',
		data : {name : name , email :email },
		dataType : 'json',
	});
	$('#otpNum').attr('disabled', false);
}

//$(document).on('click','#findId',function(){
//	mainSbmit();
//});
function mainSubmit(){
	let num=$('#otpNum').val();
	console.log(num);
	
//	$ajax({
//		url:'/login/checkAuthNum.do',
//		type: 'post',
//		data : {num: num},
//		dataType : 'json',
//	});
	
	document.findIdForm.submit()
}

		


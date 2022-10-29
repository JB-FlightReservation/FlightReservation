$(document).on('click','#btnAuthNo',function(){
	findIdChk();
});
function findIdChk(){
	let name = $('#inputName').val();
	let email = $('#inputEmail').val();
	
	$.ajax({
		url: '/login/findId.do',
		type: 'post',
		data : {name : name , email :email },
		dataType : 'json'
	})
}

//$(document).on('click','#findId',function(){
//	mainSbmit();
//});
function mainSubmit(){
	let num=$('#auth').val();
	console.log(num);
}

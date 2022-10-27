$("#btnOtp").submit(function(event){
	btnOtp();
	event.preventDefault();
})
$("#btnOtp").submit();
function btnOtp(){
	document.getElementById("otpNum").removeAttr("disabled");
}
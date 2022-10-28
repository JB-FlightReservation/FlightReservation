
function sendbtnOtp(){
	if($("#inputName").val()==null||$("#inputName").val()==""){
		alert("이름을 입력해주세요.");
		$("#inputName").focus();
		return false;
	}
	if($("#inputEmail").val()==null||$("#inputEmail").val()==""){
		alert("가입한 이메일을 입력해주세요.");
		$("#inputEmail").focus();
		return false;
	}
}
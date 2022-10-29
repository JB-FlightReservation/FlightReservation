/**
 * /reservation/payComplete.do
 */
 const nameChk = document.querySelector(".nameChk");
 const account = document.querySelector(".account");
 
 function chk(){
	 if(!nameChk.value.trim() || !account.value.trim()){
		 if(!nameChk.value.trim()){
		 console.log("비어있음")
		 alert("입금자명을 입력해주세요.")
		 return
	 	}
	 	if(!account.value.trim()){
		 console.log("비어있음")
		 alert("계좌번호를 입력해주세요.")
		 return
	 	}
	 }else{
		 location.href="/reservation/payComplete.do";
	 }
 }
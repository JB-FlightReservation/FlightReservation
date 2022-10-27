adminFindPw.adminId.addEventListener("change",checkUserId)

async function checkAdminId(){
	adminFindPw.adminId.classList.remove("is-invalid");
	adminFindPw.adminId.classList.remove("is-valid");
	let adminId=adminFindPw.adminId.value;
	let url="/adminpage/checkAdminId.do?adminId="+adminId;
	if(userId.length>3){
	let resp=await fetch(url);
		if(resp.status==200){
			let json=await resp.json();		
			if(json.check==1){
				adminFindPw.innerText="존재하지않는 아이디입니다.";
				adminFindPw.adminId.classList.add("is-invalid");
			}else if(json.check==0){
				adminFindPw.innerText="존재하는 아이디입니다.";
				adminFindPw.adminId.classList.add("is-valid");
			}else if(json.check==-1){
				alert("db 조회 실패");
			}
		}else{
			alert("통신 장애(다시시도)"+resp.status);
		}
	}else{
		userIdInvalid.innerText="4글자 이상 작성하세요.";
		userInsertForm.userId.classList.add("is-invalid");
	}
}
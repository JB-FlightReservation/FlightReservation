adminFindPw.adminId.addEventListener("change",checkAdminId)

async function checkAdminId(){
	adminFindPw.adminId.classList.remove("is-invalid");
	adminFindPw.adminId.classList.remove("is-valid");
	let adminId=adminFindPw.adminId.value;
	let url="/adminpage/checkAdminId.do?adminId="+adminId;
	if(adminId.length>3){
	let resp=await fetch(url);
		if(resp.status==200){
			let json=await resp.json();		
			if(json.check==1){
				adminIdInvalid.innerText="존재하지않는 아이디입니다.";
				adminFindPw.adminId.classList.add("is-invalid");
			}else if(json.check==0){
				adminIdValid.innerText="존재하는 아이디입니다.";
				adminFindPw.adminId.classList.add("is-valid");
			}else if(json.check==-1){
				alert("db 조회 실패");
			}
		}else{
			alert("통신 장애(다시시도)"+resp.status);
		}
	}else{
		adminIdInvalid.innerText="4글자 이상 작성하세요.";
		adminFindPw.adminId.classList.add("is-invalid");
	}
}
adminFindPw.admin_name.addEventListener("change",checkAdminName)

async function checkAdminName(){
	adminFindPw.admin_name.classList.remove("is-invalid");
	adminFindPw.admin_name.classList.remove("is-valid");
	let admin_name=adminFindPw.admin_name.value;
	let url="/adminpage/checkAdminName.do?admin_name="+admin_name;
	if(admin_name.length>1){
	let resp=await fetch(url);
		if(resp.status==200){
			let json=await resp.json();		
			if(json.check==1){
				admin_nameInvalid.innerText="일치하지않는 이름입니다.";
				adminFindPw.admin_name.classList.add("is-invalid");
			}else if(json.check==0){
				admin_nameValid.innerText="일치하는 이름입니다.";
				adminFindPw.admin_name.classList.add("is-valid");
			}else if(json.check==-1){
				alert("db 조회 실패");
			}
		}else{
			alert("통신 장애(다시시도)"+resp.status);
		}
	}else{
	admin_nameInvalid.innerText="2글자 이상 작성하세요.";
	adminFindPw.admin_name.classList.add("is-invalid");
	}
}
	

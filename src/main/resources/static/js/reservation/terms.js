const ckAll = document.querySelector(".chkAll");
const checkNodes=document.querySelectorAll("[name=check]");
const ckDanger = document.querySelector(".ckD");
const ckIn = document.querySelector(".inchk");
const btn = document.querySelector("#btn1");
const infobtn = document.querySelector("#infobtn");

infobtn.addEventListener("click",()=>{//위험물 안내 클릭시 체크박스 체크
	ckDanger.checked = true;
	
})

ckIn.addEventListener("click",()=>{//위험 안내 모달창 버튼 활성화 
	if(ckIn.checked){
		btn.disabled = false;
		
	}else{
		btn.disabled = true;
	}
})

ckDanger.addEventListener("click",()=>{//위험 안내 모달창 체크여부
	if(ckIn.checked){
		ckDanger.checked = true;
	}
	
})

ckAll.addEventListener("click",()=>{//규정 전체동의,해제
	checkNodes.forEach(ck => {
		ck.checked = ckAll.checked;
	})
})

checkNodes.forEach(ck => {//규정 전체동의,해제 (4개가 다 체크되있으면 전체동의 1개라도 빠지면 전체동의 해제)
	ck.addEventListener("click",()=>{
		let cnt =0;
		checkNodes.forEach(ck=>{
			if(ck.checked){
				cnt++;
			}
		})
		if(cnt==checkNodes.length){
			ckAll.checked = true;
		}else{
			ckAll.checked = false;
		}
	})
})

function chk(){
	if(!ckAll.checked || !ckDanger.checked){
		if(!ckAll.checked && ckDanger.checked){
			alert("예약 규정을 동의 해주세요.");
			location.href = "#chkAll";
			console.log(location.href);
			return
		}if(!ckDanger.checked && ckAll.checked){
			alert("위험물 안내 규정을 동의 해주세요.")
			location.href = "#defaultCheck1";
			return
		}
	alert("모든 규정을 동의 해주세요.");
	location.href = "#chkAll";
	}else{
		location.href = "/reservation/pay.do";
	}
	
}
function fn_chkDanger(){
	if(!ckIn.checked){
		alert("규정에 동의 해주세요.")
		return
	}
}

function info(){
	const chk = confirm("이전 페이지로 돌아갈 시 모든 정보가 초기화됩니다.")
	if(chk == true){
		location = "/reservation/baggage.do";
	}
	
}


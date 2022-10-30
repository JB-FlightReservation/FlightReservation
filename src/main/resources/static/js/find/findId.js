findNameForm.ctName.addEventListener("change",checkUserName)

async function checkUserName(){
  findNameForm.ctName.classList.remove("is-invalid");
  findNameForm.ctName.classList.remove("is-valid");
   let ctName=findNameForm.ctName.value;
   let url="/login/checkName.do?ctName="+ctName;
   if(ctName.length>1){
   let resp=await fetch(url);
      if(resp.status==200){
         let json=await resp.json();      
         if(json.check==1){
            ctNameInvalid.innerText="존재하지않는 이름입니다.";
            findNameForm.ctName.classList.add("is-invalid");
         }else if(json.check==0){
            ctNameValid.innerText="존재하는 이름입니다.";
            findNameForm.ctName.classList.add("is-valid");
         }else if(json.check==-1){
            alert("db 조회 실패");
         }
      }else{
         alert("통신 장애(다시시도)"+resp.status);
      }
   }else{
      ctNameInvalid.innerText="2글자 이상 작성하세요.";
       findNameForm.ctName.classList.add("is-invalid");
   }
}

findNameForm.ctEmail.addEventListener("change",checkUserEmail)

async function checkUserEmail(){
  findNameForm.ctEmail.classList.remove("is-invalid");
  findNameForm.ctEmail.classList.remove("is-valid");
   let ctEmail=findNameForm.ctEmail.value;
   let url="/login/checkEmail.do?ctEmail="+ctEmail;
   if(ctEmail.length>1){
   let resp=await fetch(url);
      if(resp.status==200){
         let json=await resp.json();      
         if(json.check==1){
            ctEmailInvalid.innerText="일치하지 않는 이메일 입니다.";
            findNameForm.ctEmail.classList.add("is-invalid");
         }else if(json.check==0){
            ctEmailValid.innerText="일치하는 이메일 입니다.";
            findNameForm.ctEmail.classList.add("is-valid");
         }else if(json.check==-1){
            alert("db 조회 실패");
         }
      }else{
         alert("통신 장애(다시시도)"+resp.status);
      }
   }else{
      ctEmailInvalid.innerText="가입하신 이메일을 입력하세요";
       findNameForm.ctEmail.classList.add("is-invalid");
   }
}


		


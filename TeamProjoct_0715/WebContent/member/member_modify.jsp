<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String flag = request.getParameter("flag");
	pageContext.setAttribute("flag",flag);
%>
<!doctype html>
<html lang="kr">
	<head>
	<meta charset="UTF-8">
	<title>http://www.blueb.co.kr</title>

<style rel="stylesheet">
html {
  width: 100%;
  height: 100%;
}

body {
  color: rgba(0, 0, 0, 0.6);
  font-family: "Roboto", sans-serif;
  font-size: 14px;
 /*  line-height: 1.6em; */
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.overlay, .form-panel.one:before {
  position: absolute;
  top: 0;
  left: 0;
  display: none;
  background: rgba(0, 0, 0, 0.8);
  width: 100%;
  height: 100%;
}

.form {
  z-index: 15;
  position: relative;
  background: #FFFFFF;
  width: 600px;
  border-radius: 4px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
  margin: 100px auto 10px;
  overflow: hidden;
}
.form-toggle {
  z-index: 10;
  position: absolute;
  top: 60px;
  right: 60px;
  background: #FFFFFF;
  width: 60px;
  height: 60px;
  border-radius: 100%;
  -webkit-transform-origin: center;
      -ms-transform-origin: center;
          transform-origin: center;
  -webkit-transform: translate(0, -25%) scale(0);
      -ms-transform: translate(0, -25%) scale(0);
          transform: translate(0, -25%) scale(0);
  opacity: 0;
  cursor: pointer;
  -webkit-transition: all 0.3s ease;
          transition: all 0.3s ease;
}
.form-toggle:before, .form-toggle:after {
  content: '';
  display: block;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 30px;
  height: 4px;
  background: #4285F4;
  -webkit-transform: translate(-50%, -50%);
      -ms-transform: translate(-50%, -50%);
          transform: translate(-50%, -50%);
}
.form-toggle:before {
  -webkit-transform: translate(-50%, -50%) rotate(45deg);
      -ms-transform: translate(-50%, -50%) rotate(45deg);
          transform: translate(-50%, -50%) rotate(45deg);
}
.form-toggle:after {
  -webkit-transform: translate(-50%, -50%) rotate(-45deg);
      -ms-transform: translate(-50%, -50%) rotate(-45deg);
          transform: translate(-50%, -50%) rotate(-45deg);
}
.form-toggle.visible {
  -webkit-transform: translate(0, -25%) scale(1);
      -ms-transform: translate(0, -25%) scale(1);
          transform: translate(0, -25%) scale(1);
  opacity: 1;
}
.form-group {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-wrap: wrap;
      -ms-flex-wrap: wrap;
          flex-wrap: wrap;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
      -ms-flex-pack: justify;
          justify-content: space-between;
  margin: 0 0 20px;
}
.form-group:last-child {
  margin: 0;
}
.form-group label {
  display: block;
  margin: 0 0 10px;
  color: rgba(0, 0, 0, 0.6);
  font-size: 12px;
  font-weight: 500;
  line-height: 1;
  text-transform: uppercase;
  letter-spacing: .2em;
}
.two .form-group label {
  color: #FFFFFF;
}
.form-group input {
  outline: none;
  display: block;
  background: rgba(0, 0, 0, 0.1);
  width: 100%;
  border: 0;
  border-radius: 4px;
  box-sizing: border-box;
  padding: 12px 20px;
  color: rgba(0, 0, 0, 0.6);
  font-family: inherit;
  font-size: inherit;
  font-weight: 500;
  line-height: inherit;
  -webkit-transition: 0.3s ease;
          transition: 0.3s ease;
}
.form-group input:focus {
  color: rgba(0, 0, 0, 0.8);
}
.two .form-group input {
  color: #FFFFFF;
}
.two .form-group input:focus {
  color: #FFFFFF;
}
.form-group button {
  outline: none;
  background: #4285F4;
  width: 100%;
  border: 0;
  border-radius: 4px;
  padding: 12px 20px;
  color: #FFFFFF;
  font-family: inherit;
  font-size: inherit;
  font-weight: 500;
  line-height: inherit;
  text-transform: uppercase;
  cursor: pointer;
}
.two .form-group signupbtn {
  background: #FFFFFF;
  color: #4285F4;
}
.form-group .form-remember {
  font-size: 12px;
  font-weight: 400;
  letter-spacing: 0;
  text-transform: none;
}
.form-group .form-remember input[type='checkbox'] {
  display: inline-block;
  width: auto;
  margin: 0 10px 0 0;
}
.form-group .form-recovery {
  color: #4285F4;
  font-size: 12px;
  text-decoration: none;
}
.form-panel {
  padding: 60px calc(5% + 60px) 60px 60px;
  box-sizing: border-box;
}
.form-panel.one:before {
  content: '';
  display: block;
  opacity: 0;
  visibility: hidden;
  -webkit-transition: 0.3s ease;
          transition: 0.3s ease;
}
.form-panel.one.hidden:before {
  display: block;
  opacity: 1;
  visibility: visible;
}

.form-header {
  margin: 0 0 40px;
}
.form-header h1 {
 /*  padding: 4px 0; */
  color: #4285F4;
  font-size: 22px;
  font-weight: 700;
  text-transform: uppercase;
}
.two .form-header h1 {
  position: relative;
  z-index: 40;
  color: #FFFFFF;
}

.pen-footer {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -webkit-flex-direction: row;
      -ms-flex-direction: row;
          flex-direction: row;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
      -ms-flex-pack: justify;
          justify-content: space-between;
  width: 600px;
  margin: 20px auto 100px;
}
.pen-footer a {
  color: #FFFFFF;
  font-size: 12px;
  text-decoration: none;
  text-shadow: 1px 2px 0 rgba(0, 0, 0, 0.1);
}
.pen-footer a .material-icons {
  width: 12px;
  margin: 0 5px;
  vertical-align: middle;
  font-size: 12px;
}

.cp-fab {
  background: #FFFFFF !important;
  color: #4285F4 !important;
}

</style>
</head>
<body>
  <div class="form">
  <div class="form-toggle"></div>
  <div class="form-panel one">
    <div class="form-header">
      <h1>Modify Account</h1>
    </div>
    <div class="form-content">
<form name="member" method="post" action="member?cmd=member_modify2" onsubmit="return formcheck();">
        <div class="form-group">
         <label><b>Userid</b></label> <input type="text" placeholder="Enter ID"
         name="memberid" id="memberid" value="${loginid }" readonly required="required"/>
      <div class="check_font" id="id_check"></div>
      </div>
         <div class="form-group">
          <label for="membername">Username</label>
          <input type="text" id="membername" name="membername" placeholder="Enter name" value="${vo.membername }" required="required"/>
         <div class="check_font" id="name_check"></div>
        </div>
        <div class="form-group">
          <label for="pass">Password</label>
          <input type="password"  required class="pass" placeholder="Enter passwd" id="pass" name="memberpasswd"  oninput="checkPwd()"  onkeyup="checkpw()" required="required"/>
       <div class="check_font" id="pw_check"></div>
        </div>
        <div class="form-group">
          <label for="repwd">Confirm Password</label>
          <input type="password" placeholder="Enter repasswd" id="repwd" name="repasswd"  oninput="checkPwd()" required="required"/>
        </div>
       <div class="form-group">
                 <label for="phone">Phone</label>
        <input type="text" placeholder="Enter phone" name="phone" required
         class="id" id="phone" onkeyup="checkphone()" value="${vo.phone }" required="required"/>      
      <div class="check_font" id="phone_check"></div>
      </div>
        <div class="form-group">
          <label for="email">Email Address</label>
          <input type="email" id="email" name="email" value="${vo.email }" onkeyup="checkemail()" required="required"/>
        <div class="check_font" id="email_check"></div>
        </div>
      <tr>
        <th><label><b>등급</b></label></th>
        <td><input type="radio" name="grade" value="D" <c:if test="${vo.grade=='D'}">checked</c:if>>지역주민&nbsp;&nbsp;</td>
       <td><input type="radio" name="grade" value="C" <c:if test="${vo.grade=='C'}">checked</c:if>>학부생&nbsp;&nbsp;</td>
       <td><input type="radio" name="grade" value="B" <c:if test="${vo.grade=='B'}">checked</c:if>>대학원생&nbsp;&nbsp;</td>
       <td><input type="radio" name="grade" value="A" <c:if test="${vo.grade=='A'}">checked</c:if>>교수&nbsp;&nbsp;</td>
      </tr>  
 <div class="form-group" style="padding-top:20px;"> 
          <button type="submit" id="signupbtn" class="signupbtn" name="signupbtn"
            disabled="disabled">Update</button>
 <input type="button" id="signupbtn" class="signupbtn" onClick="delCheck();" value="Withdraw">           
       </div> 
      </form>

    </div>
  </div>
  </div>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
function delCheck(){
	alert("asdfasdf");
	var url="member?cmd=member_delete";
	window.open(url,"","width=400, height=200");
}
</script>
 <script> 
// 아이디와 비밀번호가 맞지 않을 경우 가입버튼 비활성화를 위한 변수설정  
   var pwdCheck = false;
   var repwdCheck = false;
   var emailCheck = false;
   var phoneCheck = false;
   var nameCheck = false;
   $("#membername").blur(function() {       
       if($('#membername').val() == ""){            
           $('#name_check').text('이름을 입력해주세요:(');
           $('#name_check').css('color', 'red');
       }else{
    	   for (var i=0; i<$("#membername").val().length; i++)  { 
               var chk = $("#membername").val().substring(i,i+1); 
            if(chk.match(/[0-9]|[a-z]|[A-Z]/)) { 
                   $('#name_check').text('형식이 맞지 않습니다:(');
                     $('#name_check').css('color', 'red');
                     nameCheck = false;
               }else if(chk.match(/([^가-힣\x20])/i)){
                   $('#name_check').text('형식이 맞지 않습니다 :(');
                     $('#name_check').css('color', 'red');
                     nameCheck = false;
               }else{
                   $('#name_check').text('적절한 이름입니다:)');
                   $('#name_check').css('color', 'blue');
                   nameCheck = true;
               }
           }
       }      
      makeReal();
      });
      function checkPwd() {
         var inputed = $('.pass').val();
         var reinputed = $('#repwd').val();         
         if (reinputed == ""
               && (inputed != reinputed || inputed == reinputed)) {
            $("#repwd").css("background-color", "#FFCECE");
            repwdCheck = false;
         } else if (inputed == reinputed) {
            $("#repwd").css("background-color", "#B0F6AC");
            repwdCheck = true;
         } else if (inputed != reinputed) {
            $("#repwd").css("background-color", "#FFCECE");
            repwdCheck = false;
      		} 
         makeReal();
         }      
      function checkpw(){
           var v1 = member.memberpasswd.value;
           var v2 =  member.repasswd.value;
           if(v1.length<6 || v1.length>12){
               document.getElementById('pw_check').style.color = "red";
               document.getElementById('pw_check').innerHTML = "비밀번호는 6자이상 12자 이내로 입력하세요:(";
               pwdCheck = false;
           }else{
               document.getElementById('pw_check').style.color = "blue";
               document.getElementById('pw_check').innerHTML = "사용가능한 비밀번호입니다:)"; 
               pwdCheck = true;
           }
           makeReal();
          }
      function checkphone(){
           var v1 = member.phone.value;
           if(!tel_check(v1)){
               document.getElementById('phone_check').style.color = "red";               
               document.getElementById('phone_check').innerHTML = "형식이 맞지않습니다:(";
                  phoneCheck = false;
           }
                  else if(v1==""){
                     document.getElementById('phone_check').style.color = "red";               
                  document.getElementById('phone_check').innerHTML = "필수입력입니다:(";
                  phoneCheck = false;
             
           }else{
               document.getElementById('phone_check').style.color = "blue";
               document.getElementById('phone_check').innerHTML = "사용가능합니다:)"; 
               phoneCheck = true;
           }
           makeReal();
          }
      function checkemail(){
           var v1 = member.email.value;          
           if(!email_check(member.email.value)){
               document.getElementById('email_check').style.color = "red";
               document.getElementById('email_check').innerHTML = "gmail만 가능합니다";
               emailCheck = false;
           }else if(v1==""){
                     document.getElementById('email_check').style.color = "red";               
                  document.getElementById('email_check').innerHTML = "필수입력입니다";
                  emailCheck = false;                 
           }else{
               document.getElementById('email_check').style.color = "blue";
               document.getElementById('email_check').innerHTML = "사용가능한 이메일입니다."; 
               emailCheck = true;
           }
           makeReal();
          }
      function tel_check(str) {
         //010_1111,0101111둘다 가능하게
         str = str.split("-").join("");//-이들어오면 없애고 한줄로 합친다
         var phone = /^((01[0|1|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
         //var phone=/^(010[1-9][0-9]{7})$/;//pattern란 클래스에 속한 /^문자열 검색하는 패턱이다 /^( )$/ ==$/끝이라는 뜻 일치하면 true []{}크기         
         return phone.test(str);//.test 패턴을 검사하는 메소드 일치하는지 비교한다 ==정규식이라 검색하면 나옴         
      }
      //이메일 검사
      function email_check(str) {
         var mail = /^([0-9a-zA-Z_\.-]+)@gmail.com$/;
         ///^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+$/;
         return mail.test(str);
      }      
      function a_or_d(str) {
         var lower_str = str.toLowerCase();
         for (i = 0; i < lower_str.length; i++) {
            ch = lower_str.charAt(i);
            if (((ch < 'a') || (ch > 'z')) && ((ch < '0') || (ch > '9'))) {
               return 0;//영문자와 숫자 이외의 값이 들어왔을 때
            }
         }
         return 1;//정상적으로 들어왔을 때      
      }
      function makeReal(){
         var signupbtn = document.getElementById("signupbtn");       
         if( nameCheck==true && pwdCheck == true && repwdCheck==true && phoneCheck == true && emailCheck == true){
        	// alert('all true');
            signupbtn.disabled=false;
            $(".signupbtn").css("background-color", "#4285F4");
         }else{
            signupbtn.disabled=true;
            $(".signupbtn").css("background-color", "#aaaaaa");
            //alert('something false');
         }
		}
   </script>
</body>
</html>
<%@ include file="/include/footer.jsp"%>
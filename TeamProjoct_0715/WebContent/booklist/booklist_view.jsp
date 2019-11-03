<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<%@ include file="../include/header.jsp"%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
</head>
<style>
   #book_font{
   font-family: '나눔고딕',NanumGothic,ng,'돋움',dotum,"굴림",Gulim,"Trebuchet MS",arial,Verdana;
   font-size: 0.75em;
   color: #3a3a3a;
   padding: 2px 24px 2px 0px;
    width: 120px;
    text-align: left;
    border: 0px;
   }
   
   #book_font_right{
   font-family: '나눔고딕',NanumGothic,ng,'돋움',dotum,"굴림",Gulim,"Trebuchet MS",arial,Verdana;
    font-size: 0.75em;
   padding: 2px 24px 2px 12px;
    width: auto;
    border: 0px;
   }
   
   body{
   font-family: '나눔고딕',NanumGothic,ng,'돋움',dotum,"굴림",Gulim,"Trebuchet MS",arial,Verdana;
    font-size: 0.75em;
    line-height: 1.4em;
   }
</style>

<body>
   <section class="col-sm-10">
   <div class="container" style="margin-top: 50px;">
      <table class="table table-borderless" style="margin-top: 60px;">
         <thead>
         	<tr>
	            <td>
	            	<h3 style="color:#0a71b9; font-weight:bold;">상세정보</h3>
	            </td>
         	</tr>
         	<tr class="current">
         		
         	</tr>
         </thead>
	     	
      </table>
      </div>
      
      <div class="container">
	      <table class="table table-borderless" style="margin-top: 30px; background-color: #eff2f5;">
	         <thead>
	         	<tr>
	            	<td colspan="2"><h5 style="color:#4160af; font-weight:bold;">${vo.bookname}</h5></td>
	            	<td rowspan="4" style="text-align: right;"><img src="img/${vo.bookimg}" class="bookimg"></td>
	         	</tr>
	         	<tr>
	            	<td><h6 class="col-sm-4 font-weight-bold">서명</h6></td>
	            	<td><p class="h6">${vo.bookname}</p></td>
	         	</tr>
	         	<tr>
	            	<td><h6 class="col-sm-4 font-weight-bold">개인저자</h6></td>
	            	<td><p class="h6">${vo.writer}</p></td>
	        	 </tr>
	        	 <tr>
	            	<td><h6 class="col-sm-4 font-weight-bold">출판사</h6></td>
	           	 <td><p class="h6">${vo.publisher}</p></td>
	         	</tr>
	         	<tr>
	         		<td colspan="3">
	         			<button onclick="window.open('booklocation?cmd=booklocation&bookno=${vo.bookno}','location','width=800,height=550,location=no')" class="btn btn-outline-primary">도서위치</button>
	         		</td>
	         	</tr>
	         	</thead>
	      </table>
      <br>
      
      <form method="post" name="lend_fm">
         <input name="grade" type="hidden" value="${mvo.grade}">
         <input name="memberid" type="hidden" value="${memberid}">
         <input name="membername" type="hidden" value="${membername}">
         <input name="bookno" type="hidden" value="${vo.bookno}">
         <input name="bookname" type="hidden" value="${vo.bookname}">
         <input name="lenddue" type="hidden" value="${lenddue}">
         <table class="table" style="text-align: center; margin-top: 30px;">
            <tr>
               <td style="background-color:#4160ad; color:#ffffff;"><h6 class="font-weight-bold">소장정보</h6></td>
            </tr>
            <tr style="background-color: #eff2f5;">
               <td><h6 class="font-weight-bold" style="text-align: center;">도서번호</h6></td>
               <td><h6 class="font-weight-bold" style="text-align: center;">도서상태</h6></td>
               <td><h6 class="font-weight-bold" style="text-align: center;">반납예정일</h6></td>
            </tr>
            <tr>
               <td><h6>${vo.bookno}</h6></td>
               <td>
                  <c:if test="${vo.lend==1}"><h6>대출가능</h6></c:if>
                  <c:if test="${vo.lend!=1}"><h6>대출불가</h6></c:if>
               </td>
               <td><h6>${lenddue}</h6></td>
            </tr>
            <tr>
            	<td>
            	
            		<c:if test="${not empty session}">
				    	<c:if test="${vo.lend==1 || reserve == 1}">
				    	
				      		<button style="text-align: left;" onclick="lend('${reserve_bun}','${reserve}')" type="button" class="btn btn-primary">대여</button>
				    	</c:if>
				    	<c:if test="${not empty sessionScope.loginid && vo.lend!=1 && reserve != 1}">
				      		<button onclick="reserve()" type="button" class="btn btn-primary">예약</button>
				     	</c:if>
			     	</c:if>
            	</td>
            	<td>
            		<c:if test="${not empty session && session=='admin1'}">
		      			<button onclick="modify()" type="button" class="btn btn-primary">수정</button>
		      		</c:if>
            	</td>
            	<td>
            		<button onclick="back()" type="button" class="btn btn-primary">돌아가기</button>
            	</td>
            </tr>
         </table>
      </form>
   </div>
   </section>
   <footer>
      <div>
         <p>TeamProject</p>
      </div>
   </footer>
</body>
<script>
   function lend(reserve_bun,reserve){
      var message = confirm("대출하시겠습니까?");
      if(message == true){
    	  if(reserve_bun==1 && reserve==-1){
			   alert("예약된 책입니다.");
			   return;
		   }else{
	    	  if(reserve_bun==-1){
	    	  	lend_fm.action="booklend?cmd=booklend&reserve="+reserve;
	    	  	lend_fm.submit();
			   }else if(reserve==1){
				  lend_fm.action="booklend?cmd=booklend&reserve="+reserve;
		    	  	lend_fm.submit();
			   }
		   }
      }else{
         return;
      }
   }
   
   function modify(){
      location.href="booklist?cmd=booklist_modify_view&bookno=${vo.bookno}&nowpage=${nowpage}";
   }
   
   function back(){
      history.back();
   }
   
   function reserve(){
	   var message = confirm("예약하시겠습니까?");
	   if(message == true){
		lend_fm.action="booklend?cmd=booklend_reserve";
	   	lend_fm.submit();
	   }else{
		   return;
	   }
   }
</script>
<%@include file="../include/footer.jsp"%>
</html>
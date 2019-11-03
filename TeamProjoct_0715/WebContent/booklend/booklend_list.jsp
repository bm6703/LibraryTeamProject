<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<style>
	a{
		text-decoration:none;
	}
</style>
<body>
	<header>
		<h1 style="color: #696464; text-align:center; margin: 50px;">Rental List</h1>
	</header>
	<section>
	<div class="container" >
		<table class="table" style="text-align: center; border:  1px solid #dddddd;">
			<thead>
				<tr>
					<th style="background-color: #fafafa; text-align: center;">사진</th>
					<th style="background-color: #fafafa; text-align: center;">책이름</th>
					<th style="background-color: #fafafa; text-align: center;">대여자</th>
					<th style="background-color: #fafafa; text-align: center;">대여날짜</th>
					<th style="background-color: #fafafa; text-align: center;">반납예정일</th>
					<th style="background-color: #fafafa; text-align: center;">반납/연장</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${list.size()!=0}">
				<c:forEach var="booklend" items="${list}">
					<tr>
						<td><img src="img/${booklend.bookimg}" class="bookimg"></td>
						<td><a href="booklist?cmd=booklist_view&bookno=${booklend.bookno}" style="color:black;">${booklend.bookname}</a></td>
						<td>${booklend.lendid}</td>
						<td>${booklend.lenddate.substring(0,10)}</td>
						<td id = "renddue">${booklend.lenddue.substring(0,10)}</td>
						<td>
							<div class="col-sm-10"><button type="button" class="btn btn-primary" onclick="return_book('${booklend.lendno}','${booklend.bookno}')">도서반납</button></div><br><br><br>
							<div class="col-sm-10"><button type="button" class="btn btn-primary" class ="renewalBtn" onclick="renewalClick('${booklend.lendno}','${booklend.bookno }')">도서연장</button></div>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${list.size()==0}">
				<tr>
					<td colspan="9">등록된 자료가 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		</div>
		<br><br>
		
		<div class="container">
		<h2 align="center" style="color: #696464;">My Reservation</h2><br>
		<table class="table" style="text-align: center; border: 1px solid #dddddd;">
			<thead>
				<tr>
					<th style="background-color: #fafafa; text-align: center;">사진</th>
					<th style="background-color: #fafafa; text-align: center;">책이름</th>
					<th style="background-color: #fafafa; text-align: center;">대여자</th>
					<th style="background-color: #fafafa; text-align: center;">예약날짜</th>
					<th style="background-color: #fafafa; text-align: center;">예약변경</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${reserve_list.size()!=0}">
				<c:forEach var="reserve" items="${reserve_list}">
					<tr>
						<td><img src="img/${reserve.bookimg}" class="bookimg"></td>
						<td>${reserve.bookname}</td>
						<td>${reserve.reserveid}</td>
						<td>${reserve.reservedate.substring(0,10)}</td>
						<td>
							<c:if test="${reserve.reservestaus==1}">
								<div class="col-sm-10"><button type="button" class="btn btn-primary" onclick="lend('${reserve.bookno}')">대여하기</button></div><br><br><br><br>
							</c:if>
							<div class="col-sm-10"><button type="button" class="btn btn-primary" onclick="reserve_can_btn('${reserve.reservenum}','${reserve.bookno}');">예약 취소</button></div>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${reserve_list.size()==0}">
				<tr>
					<td colspan="9">등록된 자료가 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		</div>
<!-- 		<h2 align="center">bestsellers</h2><br> -->
<!-- 		<div align="center" style="margin: 50px;"> -->
<%-- 			<c:if test="${best_list.size()!=0}"> --%>
<%-- 				<c:forEach var="best" items="${best_list}"> --%>
<%-- 					<a href="booklist?cmd=booklist_view&bookno=${best.bookno}"><img src="img/${best.bookimg}" width="127" height="194" style="margin:10px; "></a> --%>
<%-- 				</c:forEach> --%>
<%-- 			</c:if> --%>
<!-- 		</div> -->
		
	</section>
</div>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   
   <script>
   
   
  function renewalClick(lendno , bookno){
		
	   if(confirm('연장하시겠습니까?')){
	   	
		$.ajax ({
			url : "booklend?cmd=book_extension&lendno="+lendno+"&bookno="+bookno,
			type : 'get',
		  
		  	dataType :'json',
		  	success : function(data){
				var row = data.row;
				var lenddue = data.lenddue;
				var renewal = data.renewal;
				var reserve_check = data.reserve_check;
				$('#renddue').innerText = lenddue;
			  	if(row == 1){
					alert('7일 연장 되었습니다.');
			  	}else if(renewal == 1){
				  	alert('도서 연장은 1회만 가능합니다.');
			  	}else if(reserve_check == 1){
			  		alert('예약된 책이있어서 연장불가.');
			  	}
		  },
		  error : function(){
			  console.log('연결 오류입니다');
		  }
	  	});
	   }
	   
	   
  }
   
   </script>
   
   <script>
   
   function lend(bookno){
	      var message = confirm("대출하시겠습니까?(아니요 선택시 자동 예약 취소됩나다.)");
	      if(message == true){
	    	  location.href="booklist?cmd=booklist_view&bookno="+bookno;
	      }else{
	         return;
	      }
	   }
   
	function search() {
		if (booklist_search.key.value == "") {
			alert("검색할 내용을 입력해주세요.");
			booklist_search.key.focus();
			return;
		}
		booklist_search.submit();
	}
	
	function return_book(lendno,bookno){
		location.href="booklend?cmd=booklend_return&lendno="+lendno+"&bookno="+bookno;
	}
	
	function reserve_can_btn(reservenum,bookno){
		var message = confirm("예약취소하시겠습니까?");
		if(message == true){
			location.href="booklend?cmd=booklend_reserve_delete&reservenum="+reservenum+"&bookno="+bookno;
		}else {
			return;
		}
	}
</script>
<%@ include file="../include/footer.jsp"%>
</html>
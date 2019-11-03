<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@ include file="../include/header.jsp"%>
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
	#lend_text_O{
		color: navy;
	}
	#lend_text_X{
		color: red;
	}
	#lend_text_Y{
		color: orange;
	}
</style>
<body>
<br>
<!-- 	<header> -->
<!-- 		<h1 style="text-align:center; margin: 50px;"><a href="booklist?cmd=booklist" style="color: grey;">book list</a></h1> -->
<!-- 	</header> -->
	<section>		
		<div class="container">
		<br><br><br>
		<h2 align="center" style="color: #696464;">bestsellers</h2><br>
			<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			    	<div class="row">
			    	<c:if test="${best_list.size()!=0}">
			    	<c:forEach var="best" items="${best_list}">
			    		<div class="col-3"><a href="booklist?cmd=booklist_view&bookno=${best.bookno}"><img src="img/${best.bookimg}" class="d-block w-100"></a></div>
			    		</c:forEach>
			    		</c:if>
			    	</div>
			    </div>
			    <div class="carousel-item">
			      <div class="row">
			      	<c:if test="${best_list_2page.size()!=0}">
			    	<c:forEach var="best2" items="${best_list_2page}">
			    		<div class="col-3"><a href="booklist?cmd=booklist_view&bookno=${best2.bookno}"><img src="img/${best2.bookimg}" class="d-block w-100"></a></div>
			    		</c:forEach>
			    		</c:if>
			    	</div>
			    </div>
			    <div class="carousel-item">
			      <div class="row">
			    		<c:if test="${best_list_3page.size()!=0}">
			    	<c:forEach var="best3" items="${best_list_3page}">
			    		<div class="col-3"><a href="booklist?cmd=booklist_view&bookno=${best3.bookno}"><img src="img/${best3.bookimg}" class="d-block w-100"></a></div>
			    		</c:forEach>
			    		</c:if>
			    	</div>
			    </div>
			  </div>
			  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>
		</div>
		<br><br><br><br><br><br><br><br>
		
		<table class="table" style="text-align: center; border:1px solid #dddddd;">
			<thead>
				<tr>
					<th style="background-color: #fafafa; text-align: center;">책사진</th>
					<th style="background-color: #fafafa; text-align: center;">책번호</th>
					<th style="background-color: #fafafa; text-align: center;">책이름</th>
					<th style="background-color: #fafafa; text-align: center;">분류</th>
					<th style="background-color: #fafafa; text-align: center;">저자</th>
					<th style="background-color: #fafafa; text-align: center;">등록일자</th>
					<th style="background-color: #fafafa; text-align: center;">대출상태</th>
					<th style="background-color: #fafafa; text-align: center;">대여횟수</th>
					<th style="background-color: #fafafa; text-align: center;">출판사</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list.size()!=0}">
					<c:forEach var="book" items="${list}">
					<tr>
						<td><a href="booklist?cmd=booklist_view&bookno=${book.bookno}&nowpage=${nowpage}" style="color:black;"><img src="img/${book.bookimg}" class="bookimg"></a></td>
						<td>${book.bookno}</td>
						<td><a href="booklist?cmd=booklist_view&bookno=${book.bookno}&nowpage=${nowpage}" style="color:black;">${book.bookname}</a></td>
						<td>${book.bookgenre}</td>
						<td>${book.writer}</td>
						<td>${book.regdate.substring(0,10)}</td>
						<td>
							<c:if test="${book.lend==1}">
								<p id="lend_text_O">대출가능</p>
							</c:if>
							<c:if test="${book.lend==2}">
								<p id="lend_text_X">대출불가</p>
							</c:if>
							<c:if test="${book.lend==3}">
								<p id="lend_text_Y">예약중</p>
							</c:if>
						</td>
						<td>${book.lendcnt}</td>
						<td>${book.publisher}</td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${list.size()==0}">
					<tr><td colspan="9">등록된 자료가 없습니다.</td></tr>
				</c:if>
			</tbody>
			
		</table>
		<p align="center">${pageSkip}</p>
		<form action="booklist?cmd=booklist_search" method="post"
			name="booklist_search">
			<br><br>
			<c:if test="${not empty session && session=='admin1'}">
				<button class="btn btn-dark" type="button" onclick="book_insert();">책등록</button>
				<button class="btn btn-dark" type="button" onclick="genreadd();">장르추가</button>
				<button class="btn btn-dark" type="button" onclick="book_down();">목록다운</button>
			</c:if>
			<table style="margin: auto; margin-right: 373px;">
				<tr>
					<td>
						<div class="col-xs-15">
							<select name="search" class="form-control">
									<option value="bookname">책 제목</option>
									<option value="writer">저자</option>
									<option value="lend">도서분류</option>
							</select>
						</div>
					</td>
					<td>
						<div class="col-xs-10">
							<input name="key" type="text" class="form-control" size="20">
						</div>
					</td>
					<td>
						<div class="col-xs-10">
							<button class="btn btn-primary" onclick="search();">검색</button>
						</div>
					</td>
				</tr>
			</table>			
		</form>
		<br><br><br>
	</section>
	<br><br><br><br><br>
	<footer>
		<div>
			<p align="center" style="margin: 50px;">TeamProject</p>
		</div>
	</footer>
<script>
	function lend(bookno){
			location.href="booklend?cmd=booklist_lend_view&nowpage=${nowpage}&bookno="+bookno;
	}
	function search() {
		if (booklist_search.key.value == "") {
			alert("검색할 내용을 입력해주세요.");
			booklist_search.key.focus();
			return;
		}
		booklist_search.submit();
	}
	
	 function genreadd(){
	      var url="booklist?cmd=booklist_genre_add";
	      window.open(url,"","width=400, height=300");
	   }
	function book_insert(){
		location.href="booklist?cmd=booklist_insert&nowpage=${nowpage}";
	}
	function book_down(){
		location.href="booklist?cmd=booklist_down";
	}
</script>
<%@ include file="../include/footer.jsp"%>
</html>
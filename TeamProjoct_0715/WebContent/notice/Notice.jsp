<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSL</title>
</head>
<body>
	<%@include file="../include/header.jsp"%>
	   <div class="container" style="margin-top: 50px;">
      <table class="table table-borderless" style="margin-top: 60px;">
         <thead>
         	<tr>
	            <td>
	            	<h3 style="color:#0a71b9; font-weight:bold;">공지사항</h3>
	            </td>
         	</tr>
         	<tr class="current">
         		
         	</tr>
         </thead>
	     	
      </table>
      </div>
	<div class="container">
	<table class="table table-borderless" style="text-align: center; border:1px solid #dddddd;">
		<thead class="thead-dark">
			<tr>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">등록일자</th>
			</tr>
		</thead>
		<tbody>
			
		
		<c:if test="${list.size()==0}">
			<!-- 공지사항 전체 리스트가 없을 때 -->
			<tr>
				<td colspan="3">공지사항이 존재하지 않습니다.</td>
			</tr>
		</c:if>
		<c:if test="${list.size()!=0}">
			<!-- 공지사항 전체 리스트가 있을 때 -->			
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.idx}</td>
					<td><a href="notice?cmd=notice_view&idx=${list.idx}&page=${nowpage}">${list.subject}</a></td>
					<td>${list.indate}</td>
				</tr>
			</c:forEach>
		</c:if>
		<tr>
		<td colspan="3">${pageSkip}</td>
		</tr>
		<tr>
		<td colspan="3">
		<c:if test="${not empty session && session=='admin1'}">
		<button type="button" class="btn btn-dark"
		onClick="javascript:location.href='notice?cmd=notice_write'">글쓰기</button>
		</c:if>
		</td>
		</tr>
		</tbody>
	</table>
</div>

	<%@include file="../include/footer.jsp"%>
</body>
</html>
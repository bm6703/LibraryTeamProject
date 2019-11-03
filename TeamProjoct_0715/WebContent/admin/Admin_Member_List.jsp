<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<title>JSL</title>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<div class="container" style="margin-top: 50px;">
      <table class="table table-borderless" style="margin-top: 60px;">
         <thead>
         	<tr>
	            <td>
	            	<h3 style="color:#0a71b9; font-weight:bold;">권한설정</h3>
	            </td>
         	</tr>
         	<tr class="current">
         		
         	</tr>
         </thead>
      </table>
      </div>
	<div class="container" style="margin-bottom: 50px;">
	<table class="table" style="text-align: center; border:1px solid #dddddd;">
		<thead>
			<tr>
				<th style="background-color: #fafafa; text-align: center;">ID</th>
				<th style="background-color: #fafafa; text-align: center;">이름</th>
				<th style="background-color: #fafafa; text-align: center;">이메일</th>
				<th style="background-color: #fafafa; text-align: center;">전화번호</th>
				<th style="background-color: #fafafa; text-align: center;">등급</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}">
			<tr>
				<td>${list.memberid}</td>
				<td><a href="admin?cmd=admin_member_modify&memberid='${list.memberid}'">${list.membername}</a></td>
				<td>${list.email}</td>
				<td>${list.phone}</td>
				<td>${list.grade}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>
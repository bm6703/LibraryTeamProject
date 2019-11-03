<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSL</title>
<script>
	function delcheck() {
		if (confirm("삭제하시겠습니까?") == true) {
			location.href = "notice?cmd=notice_delete&idx=${vo.idx}";
		} else {
			alert("삭제취소");
			return;
		}
	}
</script>
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
	<table class="table">
		<thead>
			<tr>
				<th colspan="2" style="text-align: center;">${vo.subject}</th>
			<tr>
		</thead>
		<tbody>
			<tr>
				<td style="text-align: left;"><pre>${vo.contents}</pre></td>
			</tr>
		</tbody>
	</table>
	
	<c:if test="${not empty session && session=='admin1'}">
		<button type="button" class="btn btn-dark"
		onClick="javascript:location.href='notice?cmd=notice_modify&idx=${vo.idx}'">수정</button>
		<button type="button" class="btn btn-dark" onClick="delcheck()">삭제</button>
	</c:if>
		<button type="button" class="btn btn-dark"
		onClick="javascript:location.href='notice?cmd=notice&page=${page}'">목록</button>
	</div>
</body>
<%@include file="../include/footer.jsp"%>
</html>
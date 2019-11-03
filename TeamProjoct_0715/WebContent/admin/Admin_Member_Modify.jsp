<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<title>JSL</title>
<script>
	function formcheck() {
		if (confirm("권한을 수정하시겠습니까?") == true) {
			input.action = "admin?cmd=admin_member_modify_pro";
			input.submit();
		} else {
			return;
		}
	}
</script>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<div class="container" style="margin-top: 50px;">
      <table class="table table-borderless" style="margin-top: 60px;">
         <thead>
         	<tr>
	            <td>
	            	<h3 style="color:#0a71b9; font-weight:bold;">권한설정 변경</h3>
	            </td>
         	</tr>
         	<tr class="current">
         		
         	</tr>
         </thead>
      </table>
      </div>
      <div class="container">
		<form name="input" method="post">
		<input type="hidden" name="memberid" value="${memberid}">
		<table class="table">
			<tr>
				<th>권한</th>
				<td>
					<select name="grade">
						<option value="A" <c:if test="${grade=='A'}">selected</c:if>>교수</option>
						<option value="B" <c:if test="${grade=='B'}">selected</c:if>>대학원생</option>
						<option value="C" <c:if test="${grade=='C'}">selected</c:if>>학부생</option>
						<option value="D" <c:if test="${grade=='D'}">selected</c:if>>지역주민</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<button type="button" class="btn btn-dark" onClick="formcheck();">수정</button>
					<button type="button" class="btn btn-dark" onClick="javascript:location.href='admin?cmd=admin_member_list'">돌아가기</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>
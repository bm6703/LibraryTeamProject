<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<title>도서관</title>
<script>
	function date_check(str) {
		/*str = str.split("-").join("");*/
		var date = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		return date.test(str);
	}
	function send() {
		if (!date_check(calendar.startdate.value)) {
			alert("시작일자 형식에 맞지 않습니다.");
			calendar.startdate.focus();
			return;
		}
		if (!date_check(calendar.enddate.value)) {
			alert("종료일자 형식에 맞지 않습니다.");
			calendar.enddate.focus();
			return;
		}
		//alert("입력");
		calendar.action = "admin?cmd=admin_calander_insert_pro";
		calendar.submit();
	}
</script>
</head>
<body>
	달력에 들어갈 일정을 입력하세요.
	<br> 날짜는 YYYY-MM-DD 순으로 입력하세요.
	<div class="container">
	<form name="calendar" method="post">
		<input type="hidden" name="id" value="${maxValue}" readonly>
		<table class="table">
			<tr>
				<th>예정 행사 :</th>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<th>시작 일자 :</th>
				<td>
					<input type="text" name="startdate" maxlength="10">
				</td>
			</tr>
			<tr>
				<th>종료 일자 :</th>
				<td>
					<input type="text" name="enddate" maxlength="10">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" class="btn btn-dark" onClick="send();">등록</button>
					<button type="button" class="btn btn-dark" onClick="javascript:self.close();">취소</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>
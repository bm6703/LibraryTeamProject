<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<title>JSL</title>
<script>
	function formcheck() {
		if (input.subject.value == "") {
			alert("제목을 입력하세요.");
			input.subject.focus();
			return;
		}
		if (input.contents.value == "") {
			alert("내용을 입력하세요.");
			input.contents.focus();
			return;
		}
		//alert("수정출력");
		input.action="notice?cmd=notice_modify_pro";
		input.submit();
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
	            	<h3 style="color:#0a71b9; font-weight:bold;">공지사항 수정</h3>
	            </td>
         	</tr>
         	<tr class="current">
         		
         	</tr>
         </thead>
      </table>
      </div>
	<div class="container">
	<form name="input" method="post">
		<input type="hidden" name="idx" value="${vo.idx}">
		<!-- idx를 hidden으로 지정하여 pro 클래스가 받을수있게 함. -->
		<br> <br>
		<table class="table">
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" style="width: 490px;"
					maxlength="100" value="${vo.subject}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="contents"
						style="width: 490px; height: 200px;" maxlength="4000">${vo.contents}
				</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" class="btn btn-dark" onClick="formcheck();">수정</button>
					<button type="button" class="btn btn-dark" onClick="javascript:input.reset();">초기화</button>
					<button type="button" class="btn btn-dark" onClick="javascript:history.back();">수정취소</button>
				</td>
			</tr>
			
		</table>
	</form>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>
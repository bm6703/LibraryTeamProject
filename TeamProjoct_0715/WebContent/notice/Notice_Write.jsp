<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSL</title>
<script>
function formcheck(){
	if(input.subject.value==""){
		alert("제목을 입력하세요.");
		input.subject.focus();
		return;
	}
	if(input.contents.value==""){
		alert("내용을 입력하세요.");
		input.contents.focus();
		return;
	}
	input.action="notice?cmd=notice_write_pro";
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
	            	<h3 style="color:#0a71b9; font-weight:bold;">공지사항 글쓰기</h3>
	            </td>
         	</tr>
         	<tr class="current">
         		
         	</tr>
         </thead>
	     	
      </table>
      </div>
	<div class="container" style="margin-bottom: 100px;">
		<form name="input" method="post">
		<table class="table">
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" style="width: 490px;"
					maxlength="100"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="contents" style="width: 490px; height: 200px;" maxlength="4000"></textarea></td>
			</tr>
				<tr>
				<td colspan="2">
				<button type="button" class="btn btn-dark" onClick="formcheck();">등록</button>
				<button type="button" class="btn btn-dark" onClick="javascript:input.reset();">초기화</button>
				<button type="button" class="btn btn-dark" onClick="javascript:location.href='notice?cmd=notice'">목록으로</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>
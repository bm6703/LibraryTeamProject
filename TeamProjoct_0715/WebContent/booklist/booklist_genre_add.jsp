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
	function genre_check(str) {
		var genre = /^([ㄱ-힣])$/;
		return genre.test(str);
	}
	function send() {
		var bookgenre = addbooktype.bookgenre.value;
		if (addbooktype.booktype.value == "") {
			alert("코드가 없습니다. 관리자에게 문의하세요.");
			addbooktype.bookgenre.focus();
			return;
		}
		if (addbooktype.bookgenre.value == "") {
			alert("장르를 입력하세요.");
			addbooktype.bookgenre.focus();
			return;
		}
		if (!genre_check(addbooktype.bookgenre.value)
				&& addbooktype.bookgenre.value.length > 6) {
			alert("장르는 한글 1~6자로 입력하세요.");
			addbooktype.bookgenre.focus();
			return;
		}
		//alert(bookgenre);
		addbooktype.action = "booklist?cmd=booklist_genre_add_pro";
		addbooktype.submit();
	}
</script>
</head>
<body>
	새로운 장르를 입력합니다.
	<br>코드는 자동으로 생성됩니다.
	<br>장르는 한글 1~6자까지만 허용됩니다.
	<div class="container">
	<form name="addbooktype" method="post">
		<table class="table">
			<tr>
				<th>코드 :</th>
				<td><input type="text" name="booktype" value="${newBooktype}"
					maxlength="1" readonly>
			<tr>
				<th>장르 :</th>
				<td><input type="text" name="bookgenre" maxlength="20"></td>
			</tr>
			<tr>
				<td><button type="button" class="btn btn-dark" onClick="send()">등록</button></td>
				<td><button type="button" class="btn btn-dark"
					onClick="javascript:self.close();">취소</button></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>
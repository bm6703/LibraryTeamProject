<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page language="java"
	contentType="application/vnd.ms-excel; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setHeader("Content-Type", "application/vnd.ms-xls");
	response.setHeader("Content-Disposition", "inline; filename=booklist.xls");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
function goExcel() {
document.excelData.method="POST";
document.excelData.action="excelDownData.do";
document.excelData.submit();
}
</script>
</head>
<body>
	<form name="excelData">
		<table>


			<tr>
				<td>도서번호</td>
				<td>도서명</td>
				<td>저자</td>
				<td>출판사</td>
				<td>등록일자</td>
			</tr>

			<c:if test="${book_list.size()!=0}">
				<c:forEach var="book" items="${book_list}">

					<tr>
						<td>${book.bookno}</td>
						<td>${book.bookname}</td>
						<td>${book.writer}</td>
						<td>${book.publisher}</td>
						<td>${book.regdate.substring(0,10)}</td>
						<td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${row==1}">
	<script>
		alert("예약 완료");
		location.href = "booklist?cmd=booklist";
	</script>
</c:if>
<c:if test="${row==-1}">
	<script>
		alert("최대 예약은 3번 입니다.");
		history.back();
	</script>
</c:if>
<c:if test="${row==-2}">
	<script>
		alert("이미 예약된 책입니다.");
		history.back();
	</script>
</c:if>
<c:if test="${row==-3}">
	<script>
		alert("이미 대여중인 책입니다.");
		history.back();
	</script>
</c:if>
<c:if test="${row==0}">
	<script>
		alert("예약 실패");
		history.back();
	</script>
</c:if>
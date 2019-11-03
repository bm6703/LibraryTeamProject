<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/include/header.jsp"%>
   <section>
   	<div class="container" style="margin-bottom: 100px;">
   	<h3 class="col-sm-3" style="margin-top: 100px; margin-bottom: 25px;">book modify</h3>
      <form name="blist_porm" method="post" action="booklist?cmd=booklist_modify" enctype="multipart/form-data">
         <input type="hidden" name="bookimg_copy" value="${vo.bookimg}">
         <table class="table table-striped">
         	<thead>
         		<tr>
	               <td>책사진</td>
	               <td><input type="file" name="bookimg"></td>
            	</tr>
            	<tr>
	               <td>책번호</td>
	               <td><input type="text" name="bookno" maxlength="5" value="${vo.bookno}"></td>
            	</tr>
            	<tr>
	               <td>책이름</td>
	               <td><input type="text" name="bookname" maxlength="30" value="${vo.bookname}"></td>
            	</tr>
           	 	<tr>
	               <td>타입</td>
	               <td>
	               	<select name="booktype">
	               		<c:forEach var="bt" items="${typeList}">
	               			<option value="${bt[0]}">${bt[1]}</option>
	               		</c:forEach>
	               	</select>
	               	</td>
            	</tr>
            	<tr>
	               <td>글쓴이</td>
	               <td><input type="text" name="writer" maxlength="20" value="${vo.writer}"></td>
            	</tr>
            	<tr>
	               <td>출판사</td>
	               <td><input type="text" name="publisher" maxlength="20" value="${vo.publisher}"></td>
            	</tr>
         	</thead>
            
         </table>
      </form>
      <button onclick="check()" type="button" class="btn btn-dark">수정</button>
      <button onclick="delete_check()" type="button" class="btn btn-dark">삭제</button>
      <button onclick="list_go()" type="button" class="btn btn-dark">취소</button>
      </div>
   </section>
   <footer>
      <div>
         <p>TeamProject</p>
      </div>
   </footer>
</body>
<script>   
   function check(){
      if(blist_porm.bookno.value==""){
         alert("책 번호는 필수입력사항");
         blist_porm.bookno.focus();
         return;
      }else if(blist_porm.bookname.value==""){
         alert("책 이름은 필수입력사항");
         blist_porm.bookname.focus();
         return;
      }
      blist_porm.submit();
   }   
   function delete_check(){
      var message = confirm("삭제하시겠습니까?");
      if(message==true){
         location.href="booklist?cmd=booklist_delete&bookno=${vo.bookno}";
      }else{
         return;
      }
   }   
   function list_go() {
      history.back();
   }
</script>
<%@ include file="/include/footer.jsp"%>
</html>
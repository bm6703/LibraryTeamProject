<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>

#shelf2{
	width: 70px;
	height: 100px;
	background-color: black;
}

table{
/* margin-right: 500px; */
width:400px;
height:600px;
border:2px;
display: inline;
float:right;

/* border: 1px solid #444444; */
  }
  th, td {
  /*   border: 1px solid #444444;  */
  }
 #table td:first-child{
background-color:transparent;
}
 
td{
width: 50PX;
}

#table tr:nth-child(odd){
	height: 30PX;
} 
tr:nth-child(even){
	height: 90PX;
}
.img1{
display: inline;
float:left;
margin-left : 30px;
display:inline-block;
} 
span {
    display:inline-block; 
    font-size:40px;
   
}
#book1{
float: left;
}
#locationtext{
 font-size:40px; 
 align-content: center;
}
</style>

<div id="locationtext"></div>

<div id="book1">
<c:set var="shelfcnt" value="0"></c:set>
<c:forEach var="tnc" items="${tNcList }" varStatus="vs">
	<c:forEach begin="1" end="${tnc[1] }" varStatus="invs">
	
		<span class="text" id="shelf${tnc[0] }${invs.index }">
		
		
		<div id="container" >
	<img id="image" src="booklocation/img/bookl.jpg" width="70px"></div>
		
		
<!-- 	<div id = "shelf2"></div> -->
		 &nbsp;${tnc[0] }${invs.index } &nbsp;</span>
		<c:set var="shelfcnt" value="${shelfcnt+1 }"></c:set>
		<c:if test="${shelfcnt%4==0 }"><br></c:if>
	</c:forEach>
 </c:forEach> 
 <br> 
</div>

<div class = "shelf" >

   <table id="table" align="right" width="400px" background = "booklocation\img\bookshelf.jpg"
   style="background-repeat: no-repeat; background-size: 400px">

      <c:forEach begin="1" end="9" varStatus="vsRow">
         <tr id="row" class="row${vsRow.index }" >

            <c:forEach begin="1" end="9" varStatus="vsCol">
               <td id="col" class="col${vsCol.index }" ></td>

            </c:forEach>

         </tr>
      </c:forEach>
   </table>

</div>

   <script>
//$("#table td:first-child").css('background-color','transparent !important');
// $("tr:odd").css('background-color','green');
  $("#col:odd").css('background-color','blue');
   
  var bookno = '${bookno}';
  var loc = bookno.substr(1);
  
  
  // 책장의 형태 정의
   var shelfMaxNum = 28;	// 한 책장에 들어갈 최대 책의 갯수
   var rowcnt = 4;	// 책장의 로우 갯수
   var colcnt = 7;	// 책장의 컬럼 갯수
  //script는 integer할 필요없이 자동으로 해준다
  
	// 책장의 타입
   var shelfType= bookno.substr(0,1);
   console.log(shelfType);
   
   // 몇번 째 책장인지
   var shelfNum = Math.floor(loc/shelfMaxNum)+1;
   
   // 책장에서의 위치. 이걸로 로우랑 컬럼 계산할꺼임
   var LinS = loc%shelfMaxNum;
   console.log(LinS);
   
   // 상대적인 위치
   var row = Math.floor(LinS/colcnt) + 1;
   var col = LinS%colcnt;
   
   // 테이블 상에서의 위치
  	var tbrow = row*2;
   var tbcol = col + 1;
   
   
   //선반 위치
   var shelfposition = '#shelf'+shelfType + shelfNum;
  
   console.log(shelfposition);
   $(shelfposition).css('color','red');
   $(shelfposition).css('font-weight','bold');

   
   // 책 위치
   var bookposition = '.row' + tbrow + " .col" + tbcol;
   $(bookposition).css('background-color','red');
   $('#locationtext').text(shelfType + shelfNum+'의 '+row+'행'+col+'열 입니다');
</script>

</body>
</html>
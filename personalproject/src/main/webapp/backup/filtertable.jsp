<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf" %>
<%@ include file="../include/login_check.jspf" %> 
<title>Post Review</title>
<script type="text/javascript">
$(function(){
	//selectCafe();
	$(".collapse").collapse();
});

$(document).ready(function(){
	  $("#myInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myTable tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
	
function selectCafe(){
	$.ajax({
		url: "${path}/reviewboard_servlet/selectCafe.do",
		success: function(result){
			$("#result").html(result);
		}
	});
}
</script>
</head>
<body>
<%@ include file="../include/navbar.jspf" %> 
<div class="container">
  <h2>리뷰 작성</h2>
  <p></p>
  <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#cafeList">
  <span class="glyphicon glyphicon-pencil"></span>카페목록 선택</button>
 <div id="cafeList" class="collapse in">
	<div id="result"></div>
  <br>  
  <input class="form-control" id="myInput" type="text" placeholder="카페명/주소/개업일로 검색해보세요.">
  <br>
  <div class="form-group" id="myTable">
      <label for="sel2">input-lg</label>
      <select class="form-control input-lg" id="sel2">
      <option>카페목록</option>
      <c:forEach var="dto" items="${list}">
        <option>${dto.cafe_name} | ${dto.address} | ${dto.open_date}</option>
       </c:forEach>
       
      </select>
  
  
  <table class="table table-bordered table-striped">
    <thead>
      <tr>
        <th>카페</th>
        <th>주소</th>
        <th>개업일(연)</th>
      </tr>
    </thead>
    <tbody >
     <c:forEach var="dto" items="${list}">
      <tr>
        <td><a href="" onclick="selectCafe()">${dto.cafe_name}</a></td>
        <td>${dto.address}</td>
        <td>${dto.open_date}</td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
    </div>
  <div class="form-group">
      <label for="inputdefault">선택한 카페 : </label>
      <input class="form-control" id="inputdefault" type="text" placeholder="${dto.cafe_name}" disabled>
    </div>
    <div class="form-group">
    <h3>평가하기</h3>
      	<select name="star" id="select-choice" data-native-menu="false">
	     <option value="1">☆</option>
	     <option value="2">☆☆</option>
	     <option value="3" selected>☆☆☆</option>
	     <option value="4">☆☆☆☆</option>
	     <option value="5">☆☆☆☆☆</option>
	     </select>
    </div>
    <div class="form-group">
      <label for="sel2">input-lg</label>
      <select class="form-control input-lg" id="sel2">
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>
    </div>
    <div class="form-group">
      <label for="sel3">input-sm</label>
      <select class="form-control input-sm" id="sel3">
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>
    </div>
</div>




<%@ include file="../include/footer.jspf" %> 
</body>
</html>
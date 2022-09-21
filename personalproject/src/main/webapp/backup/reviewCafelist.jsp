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
<script>
//필터
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});

</script>
<body>
<div class="container">
  <h2>카페목록</h2>
  <p>이름을 선택해주세요</p>
  <br>  
  <input class="form-control" id="myInput" type="text" placeholder="카페명/주소/개업일로 검색해보세요.">
  <br>
  <table class="table table-bordered table-striped">
    <thead>
      <tr>
        <th>카페</th>
        <th>주소</th>
        <th>개업일(연)</th>
      </tr>
    </thead>
    <tbody id="myTable">
     <c:forEach var="dto" items="${list}">
      <tr>
        <td><a href="postReview.jsp?cafe_name=${dto.cafe_name}" onclick="">${dto.cafe_name}</a></td>
        <td>${dto.address}</td>
        <td>${dto.open_date}</td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
  
</div>
</body>
</html>
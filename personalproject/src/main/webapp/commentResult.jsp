<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<%@ include file="../include/login_check.jspf"%>
<title>Review Detail</title>
<c:forEach var="cdto" items="${clist}">
	<c:forEach begin="1" end="${cdto.re_level}" step="1">&nbsp;&nbsp;</c:forEach>
	<h6>
		<b>${cdto.writer}</b>
	</h6>
	<h6>${cdto.content}</h6>
  ${cdto.reg_date}
  <c:if test="${userid == cdto.writer}">
		<button id="btnDelComm" type="button" class="btn btn-default">삭제</button>
	</c:if>
</c:forEach>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<%@ include file="../include/login_check.jspf"%>
<title>관리자 페이지</title>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>

	<div class="container">
		<h2>관리자 페이지</h2>
		<c:if test="${empty list}">
			<p>회원 목록 확인을 위해 관리자 키를 입력해주세요</p>
			<form class="form-inline" method="post"
				action="${path}/member_servlet/admin_check.do">
				<div class="form-group">
					<label for="adminkey">Admin Key :</label> <input type="password"
						id="adminkey" name="adminkey" class="form-control"
						placeholder="Enter Key">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<c:if test="${param.message == 'error'}">
				<span style="color: red;"><b>틀렸습니다. 다시 확인해주세요</b></span>
			</c:if>
		</c:if>
	</div>

	<%@ include file="../include/footer.jspf"%>
</body>
</html>

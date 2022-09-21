<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<%@ include file="../include/login_check.jspf"%>
<title>회원정보 수정</title>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>
	<div class="container">
		<h2>회원정보 수정</h2>
		<c:if test="${empty dto}">
			<p>회원정보 수정을 위해 비밀번호를 입력해주세요.</p>
			<form class="form-inline" method="post"
				action="${path}/member_servlet/member_detail.do">
				<div class="form-group">
					<label for="userid">UserId : </label> <input id="userid"
						name="userid" class="form-control" value="${sessionScope.userid}"
						readonly /> <label for="passwd">Passwd : </label> <input
						type="password" id="passwd" name="passwd" class="form-control"
						placeholder="Enter Password">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<c:if test="${param.message == 'error'}">
				<span style="color: red;"><b>틀렸습니다. 다시 확인해주세요</b></span>
			</c:if>
		</c:if>
		<c:if test="${not empty dto}">
			<form name=form3 method="post">
				<div class="form-group">
					<label for="userid">아이디</label> <input id="userid" name="userid"
						class="form-control" value="${dto.userid}" readonly />
				</div>
				<div class="form-group">
					<label for="passwd">비밀번호</label> <input
						class="form-control input-lg" id="passwd" name="passwd"
						type="password" value="${dto.passwd}">
				</div>
				<div class="form-group">
					<label for="passwd">비밀번호 확인</label> <input
						class="form-control input-lg" id="pwcheck" name="pwcheck"
						type="password" value="${dto.passwd}">
				</div>
				<div class="form-group">
					<label for="name">이름</label> <input class="form-control input-sm"
						id="name" name="name" value="${dto.name}">
				</div>
				<div class="form-group">
					<label for="email">이메일</label> <input class="form-control input-sm"
						id="email" name="email" type="email" value="${dto.email}">
				</div>
				<div class="form-group">
					<label for="email">전화번호</label> <input
						class="form-control input-sm" id="hp" name="hp" type="tel"
						value="${dto.hp}">
				</div>
				<div class="form-group">
					<label for="email">주소</label> <input class="form-control input-sm"
						id="address" name="address" value="${dto.address}">
				</div>
				<div class="form-group">
					<label for="email">생일</label> <input class="form-control input-sm"
						id="hp" name="birthday" type="date"
						value="${fn:substring(dto.birthday,0,10)}">
				</div>
				<button type="button" class="btn btn-default"
					onclick="memberUpdate()">수정하기</button>
				<button type="button" class="btn btn-default"
					onclick="memberDelete()">탈퇴하기</button>
				<input type="hidden" name="userid" value="${dto.userid}">
			</form>
		</c:if>

	</div>
	<%@ include file="../include/footer.jspf"%>
</body>
</html>
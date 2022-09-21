<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<%@ include file="../include/login_check.jspf"%>
<title>Post Review</title>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>
	<div class="container">
		<form name="form1" enctype="multipart/form-data" method="post">
			<h2>리뷰 수정</h2>
			<div class="form-group" align="right">
				<button class="btn btn-lg" onclick="reviewUpdate()">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;수정하기
				</button>
			</div>
			<div class="form-group">
				<label for="title">제목</label> <input name="title"
					class="form-control input-lg" id="title" value="${dto.title}">
			</div>
			<div class="form-group">
				<label for="cafe_num">카페 선택</label> <select name="cafe_num"
					class="form-control input-lg" id="cafe_num">
					<c:forEach var="list" items="${list}">
						<c:choose>
							<c:when test="${dto.cafe_num ==list.cafe_num}">
								<option value="${list.cafe_num}" selected>${list.cafe_name}
									| ${list.address} | ${list.open_date}</option>
							</c:when>
							<c:otherwise>
								<option value="${list.cafe_num}">${list.cafe_name}|
									${list.address} | ${list.open_date}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="stars">평점</label> <select name="stars"
					class="form-control input-lg" id="stars">
					<c:choose>
						<c:when test="${dto.stars == 1}">
							<option value="1" selected>☆</option>
							<option value="2">☆☆</option>
							<option value="3">☆☆☆</option>
							<option value="4">☆☆☆☆</option>
							<option value="5">☆☆☆☆☆</option>
						</c:when>
						<c:when test="${dto.stars == 2}">
							<option value="1">☆</option>
							<option value="2" selected>☆☆</option>
							<option value="3">☆☆☆</option>
							<option value="4">☆☆☆☆</option>
							<option value="5">☆☆☆☆☆</option>
						</c:when>
						<c:when test="${dto.stars == 3}">
							<option value="1">☆</option>
							<option value="2">☆☆</option>
							<option value="3" selected>☆☆☆</option>
							<option value="4">☆☆☆☆</option>
							<option value="5">☆☆☆☆☆</option>
						</c:when>
						<c:when test="${dto.stars == 4}">
							<option value="1">☆</option>
							<option value="2">☆☆</option>
							<option value="3">☆☆☆</option>
							<option value="4" selected>☆☆☆☆</option>
							<option value="5">☆☆☆☆☆</option>
						</c:when>
						<c:when test="${dto.stars == 5}">
							<option value="1">☆</option>
							<option value="2">☆☆</option>
							<option value="3">☆☆☆</option>
							<option value="4">☆☆☆☆</option>
							<option value="5" selected>☆☆☆☆☆</option>
						</c:when>
					</c:choose>
				</select>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea name="content" class="form-control input-lg" id="content"
					rows="20">${dto.content}</textarea>
			</div>
			<div class="form-group">
				<label for="files">첨부파일</label>
				<c:if test="${dto.filesize > 0}">
      ${dto.filename}(${dto.filesize / 1024} KB)
      <div class="checkbox">
						<label><input type="checkbox" name="fileDel">첨부파일
							삭제</label>
					</div>
				</c:if>
				<input type="file" name="files" class="form-control input-lg"
					id="files">
			</div>
			<div class="form-group">
				<label for="passwd">회원 비밀번호</label> <input type="password"
					name="passwd" class="form-control input-lg" id="passwd"
					placeholder="비밀번호를 입력해주세요.">
			</div>
			<div class="form-group" align="right">
				<button class="btn btn-lg" onclick="reviewUpdate()">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;수정하기
				</button>
			</div>
			<input name="userid" type="hidden" value="${sessionScope.userid}">
			<input name="num" type="hidden" value="${dto.num}">
		</form>
		<br>
	</div>



	<%@ include file="../include/footer.jspf"%>
</body>
</html>
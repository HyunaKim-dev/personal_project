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
		<form name="form8" enctype="multipart/form-data" method="post">
			<h2>리뷰 작성</h2>
			<div class="form-group" align="right">
				<button class="btn btn-lg" onclick="reviewPost()">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;등록
				</button>
			</div>
			<div class="form-group">
				<label for="title">제목</label> <input name="title"
					class="form-control input-lg" id="title" placeholder="제목을 입력해주세요.">
			</div>
			<div class="form-group">
				<label for="cafe_num">카페 선택</label> <select name="cafe_num"
					class="form-control input-lg" id="cafe_num">
					<c:forEach var="dto" items="${list}">
						<option value="${dto.cafe_num}">${dto.cafe_name}|
							${dto.address} | ${dto.open_date}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="stars">평점</label> <select name="stars"
					class="form-control input-lg" id="stars">
					<option value="1">☆</option>
					<option value="2">☆☆</option>
					<option value="3" selected>☆☆☆</option>
					<option value="4">☆☆☆☆</option>
					<option value="5">☆☆☆☆☆</option>
				</select>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea name="content" class="form-control input-lg" id="content"
					placeholder="내용을 입력해주세요." rows="20"></textarea>
			</div>
			<div class="form-group">
				<label for="files">첨부파일</label> <input type="file" name="files"
					class="form-control input-lg" id="files">
			</div>
			<div class="form-group" align="right">
				<button class="btn btn-lg" onclick="reviewPost()">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;등록
				</button>
			</div>
			<input name="userid" type="hidden" value="${sessionScope.userid}">
		</form>
		<br>
	</div>



	<%@ include file="../include/footer.jspf"%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<%@ include file="../include/login_check.jspf"%>
<title>Reply Review</title>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>
	<div class="container">
		<form name="form1" enctype="multipart/form-data" method="post">
			<h2>답글 작성</h2>
			<div class="form-group" align="right">
				<button class="btn btn-lg" onclick="replyPost()">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;등록
				</button>
			</div>
			<div class="form-group">
				<label for="title">제목</label> <input name="title"
					class="form-control input-lg" id="title" value="Re: ${dto.title}">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea name="content" class="form-control input-lg" id="content"
					rows="20">${dto.content}</textarea>
			</div>
			<label for="files">첨부파일</label> <input type="file" name="files"
				class="form-control input-lg" id="files"> <input
				name="userid" type="hidden" value="${sessionScope.userid}">
			<input name="num" type="hidden" value="${dto.num}">
		</form>
		<br>
	</div>

	<%@ include file="../include/footer.jspf"%>
</body>
</html>
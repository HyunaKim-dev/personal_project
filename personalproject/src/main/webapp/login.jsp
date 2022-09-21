<%@page import="utils.CookieManager1"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<title>로그인</title>
<script type="text/javascript">
	$(function() {
		$("#btnLogin").click(function() {
			if (loginCheck()) {
				document.form1.action = "${path}/member_servlet/login.do";
				document.form1.submit();
			}
		});
		$("#btnFind").click(function() {
			$("#myModal").modal();
		});
		$("#submit").click(function() {
			document.form2.action = "${path}/member_servlet/search_id.do";
			document.form2.submit();
		});
	});
</script>
</head>
<body>
	<%
	String save_id = CookieManager1.readCookie(request, "save_id");
	String cookieChk = "";
	if (!save_id.equals(""))
		cookieChk = "checked";
	%>
	<%@ include file="../include/navbar.jspf"%>

	<div class="container">
		<h2>로그인</h2>
		<form name="form1" method="post" class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-2" for="userid">Id:</label>
				<div class="col-sm-10">
					<input type="" class="form-control" id="userid" name="userid"
						placeholder="아이디" value="<%=save_id%>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="passwd">Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="passwd"
						name="passwd" placeholder="비밀번호">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label><input type="checkbox" name="save_id"
							<%=cookieChk%>> 아이디 기억하기</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" id="btnLogin" class="btn btn-default">로그인</button>
					<button type="button" onclick="location.href='join.jsp'"
						class="btn btn-default">회원가입</button>
					<button type="button" data-toggle="modal" id="btnFind"
						class="btn btn-default">아이디 찾기</button>
				</div>
			</div>
		</form>
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="padding: 35px 50px;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>아이디 찾기</h4>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
						<form role="form" name="form2">
							<div class="form-group">
								<label for="email"><span
									class="glyphicon glyphicon-user"></span> 이메일</label> <input type="text"
									class="form-control" name="email" id="email"
									placeholder="이메일을 입력하세요.">
							</div>
							<div class="form-group">
								<label for="name"><span class="glyphicon glyphicon-user"></span>
									이름</label> <input type="text" class="form-control" name="name"
									id="name" placeholder="이름을 입력하세요.">
							</div>
							<button type="submit" class="btn btn-success btn-block"
								id="submit">제출</button>
						</form>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-danger btn-default pull-left"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span> Cancel
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/footer.jspf"%>
</body>
</html>
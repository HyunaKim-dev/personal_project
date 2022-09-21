<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<title>회원가입</title>
<script type="text/javascript">
$(function(){
	$("#btnJoin").click(function(){
		if(check_join()){
			join();
		}	
	});
});
</script>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>
	<div class="container">
		<h2>회원가입</h2>
		<form name="form2" method="post" class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-2" for="userid">Id:</label>
				<div class="col-sm-10">
					<input type="" class="form-control" id="userid" name="userid"
						placeholder="아이디">
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
				<label class="control-label col-sm-2" for="pwcheck">Password
					Check:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="pwcheck"
						name="pwcheck" placeholder="비밀번호 확인">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Name:</label>
				<div class="col-sm-10">
					<input class="form-control" id="name" name="name" placeholder="이름">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="email" name="email"
						placeholder="이메일">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="address">Address:</label>
				<div class="col-sm-10">
					<input class="form-control" id="address" name="address"
						placeholder="주소">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="birthday">Birthday:</label>
				<div class="col-sm-10">
					<input type="date" class="form-control" id="birthday"
						name="birthday">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="hp">Phone Number:</label>
				<div class="col-sm-10">
					<input type="tel" class="form-control" id="hp" name="hp"
						placeholder="010-0000-0000">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for=""></label>
				<div class="col-sm-10">
					<label for="check1">만 14세 이상</label> <input type="checkbox"
						name="check" id="check1"> <label for="check2">서비스
						이용약관 확인</label> <input type="checkbox" name="check" id="check2"> <label
						for="check3">개인정보 수집 및 이용 동의</label> <input type="checkbox"
						name="check" id="check3">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" id="btnJoin" class="btn btn-default">가입하기</button>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="../include/footer.jspf"%>
</body>
</html>
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
<script>
$(document).ready(function(){
	$("#deletepwd").hide();
	
	$("#btnReply").click(function(){
		location.href="{path}/reviewboard_servlet/reply_page.do?num="+${dto.num};
	});
	$("#btnUpdate").click(function(){
		location.href="{path}/reviewboard_servlet/update_page.do?num="+${dto.num};
	});
	$("#btnDelete").click(function(){
		$("#deletepwd").show();
		if($("#deletepwd").val()==""){
			$("#deletepwd").show();
			$("#deletepwd").focus();
			return;
		}
		document.form7.action="{path}/reviewboard_servlet/delete_review.do";
		document.form7.submit();
	});
});

</script>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>
	<br>
	<br>
	<div class="container">
		<form name="form7" method="post">
			<div class="panel panel-default">
				<div class="panel-body">
					<h4>
						${dto.cafe_name}
						<c:forEach begin="1" end="${dto.stars}" step="1">
							<span class="glyphicon glyphicon-star"></span>
						</c:forEach>
					</h4>
					<div class="panel panel-default">
						<div class="panel-body" style="height: 600px;">
							<h2>${dto.title}</h2>
							<hr>
							<h6>
								작성자 <b>${dto.writer}</b>
							</h6>
							<h6>
								작성일
								<fmt:formatDate value="${dto.reg_date}" type="date"
									pattern="yyyy-MM-dd" />
								조회 ${dto.read_count}
							</h6>
							<hr>
							${dto.content}
						</div>
					</div>
					<button type="button" class="btn btn-default btn-sm"
						onclick="goodCount(${dto.num})">
						<span class="glyphicon glyphicon-thumbs-up"></span> 좋아요
						${dto.good_count}
					</button>
				</div>
				<!-- panel-body -->
				<div class="panel-heading">첨부파일</div>
				<div class="panel-body">
					<c:if test="${dto.filesize > 0}">
						<button type="button" class="btn btn-default btn-sm"
							onclick="download('${dto.num}')">
							<span class="glyphicon glyphicon-save-file"></span> Download File
						</button>
    다운로드 수 ${dto.download} 
    <h6>${dto.filename}</h6>
					</c:if>
				</div>
				<!-- 첨부파일 -->
			</div>

			<div class="panel panel-default">
				<div class="panel-body">
					<h4>
						<b><span class="glyphicon glyphicon-edit"></span>&nbsp;댓글</b>(${dto.comment_count})
					</h4>
					<c:forEach var="cdto" items="${clist}">
						<h6>
							<c:forEach begin="1" end="${cdto.re_level}" step="1">&nbsp;&nbsp;</c:forEach>
							<span class="glyphicon glyphicon-minus"></span>&nbsp;&nbsp;<b>${cdto.writer}</b>
							(
							<fmt:formatDate value="${cdto.reg_date}" type="date"
								pattern="yyyy-MM-dd kk	:mm" />
							)
							<c:if test="${userid == cdto.writer}">
								<a href="#" onclick="deleteComment(${cdto.comment_num})">삭제</a>
							</c:if>
							<a href="#" onclick="replyComment('${cdto.comment_num}')">답글</a>
						</h6>
						<h6>
							<c:forEach begin="1" end="${cdto.re_level}" step="1">&nbsp;&nbsp;</c:forEach>
							${cdto.content}
						</h6>
						<hr>
					</c:forEach>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label for="content">${sessionScope.userid}</label>
						<textarea name="content" class="form-control input-lg"
							id="content" placeholder="내용을 입력하세요." rows="5"></textarea>
					</div>
					<button type="button" class="btn btn-default"
						onclick="javascript:insertComment();">제출</button>
				</div>
			</div>
			<!-- comment -->
			<button type="button" class="btn btn-default"
				onclick="location.href='${path}/reviewboard_servlet/select_cafe.do'">
				<span class="glyphicon glyphicon-pencil"></span>&nbsp;글쓰기
			</button>
			&nbsp;
			<button id="btnReply" type="button" class="btn btn-default">
				답글</button>
			<c:if test="${sessionScope.userid == dto.writer}">
				<button id="btnUpdate" type="button" class="btn btn-default">
					수정</button>
				<span><input type="password" name="passwd" id="deletepwd"
					placeholder="비밀번호를 입력해주세요."></span>
				<button id="btnDelete" type="button" class="btn btn-default">
					삭제</button>
			</c:if>
			<div align="right">
				<button type="button" class="btn btn-default"
					onclick="boardList('1')">목록</button>
				<c:if test="${result != 'min'}">
					<button type="button" class="btn btn-default"
						onclick="reviewDetail('${dto.num-1}')">이전글</button>
				</c:if>
				<c:if test="${result != 'max'}">
					<button type="button" class="btn btn-default"
						onclick="reviewDetail('${dto.num+1}')">다음글</button>
				</c:if>
			</div>
			<input type="hidden" name="userid" value="${sessionScope.userid}">
			<input type="hidden" name="num" value="${dto.num}">
		</form>
		<br>
		<br>
	</div>
	<%@ include file="../include/footer.jspf"%>
</body>
</html>
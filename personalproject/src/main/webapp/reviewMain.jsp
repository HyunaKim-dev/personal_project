<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<title>Review Main</title>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>
	<div class="container">
		<br>
		<h2>리뷰 게시판</h2>
		<h5>모두의 즐거운 커피생활을 위해 회원님의 의견을 남겨주세요.</h5>
		<br>
		<form name="form7" class="form-inline" method="post">
			<div class="form-group">
				<label for="searchOption"></label>
				<c:choose>
					<c:when test="${searchOption =='cafe_name'}">
						<select name="searchOption" class="form-control" id="searchOption">
							<option value="cafe_name" selected>카페명</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="all">카페/제목/내용</option>
						</select>
					</c:when>
					<c:when test="${searchOption =='title'}">
						<select name="searchOption" class="form-control" id="searchOption">
							<option value="cafe_name">카페명</option>
							<option value="title" selected>제목</option>
							<option value="content">내용</option>
							<option value="all">카페/제목/내용</option>
						</select>
					</c:when>
					<c:when test="${searchOption =='content'}">
						<select name="searchOption" class="form-control" id="searchOption">
							<option value="cafe_name">카페명</option>
							<option value="title">제목</option>
							<option value="content" selected>내용</option>
							<option value="all">카페/제목/내용</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="searchOption" class="form-control" id="searchOption">
							<option value="cafe_name">카페명</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="all" selected>카페/제목/내용</option>
						</select>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${orderOption =='read_count'}">
						<select name="orderOption" class="form-control" id="searchOption">
							<option value="read_count" selected>조회수</option>
							<option value="stars">평점순</option>
							<option value="reg_date">최신순</option>
						</select>
					</c:when>
					<c:when test="${orderOption =='stars'}">
						<select name="orderOption" class="form-control" id="searchOption">
							<option value="read_count">조회수</option>
							<option value="stars" selected>평점순</option>
							<option value="reg_date">최신순</option>
						</select>
					</c:when>
					<c:when test="${orderOption =='reg_date'}">
						<select name="orderOption" class="form-control" id="searchOption">
							<option value="read_count">조회수</option>
							<option value="stars">평점순</option>
							<option value="reg_date" selected>최신순</option>
						</select>
					</c:when>
				</c:choose>
			</div>
			<div class="input-group">
				<input type="text" class="form-control" name="searchKeyword"
					value="${searchKeyword}">
				<div class="input-group-btn">
					<button class="btn btn-default" type="button"
						onclick="boardList('1')">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
			<table class="table table-hover" style="margin-top: 2%;">
				<thead>
					<tr>
						<th>번호</th>
						<th>카페</th>
						<th>평점</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
					</tr>
				</thead>
				<c:forEach var="dto" varStatus="loop" items="${list}">
					<tbody>
						<tr>
							<td>${dto.num}</td>
							<td><a
								href="${path}/cafe_servlet/cafe_detail.do?cafe_num=${dto.cafe_num}">${dto.cafe_name}</a></td>
							<td><c:forEach begin="1" end="${dto.stars}" step="1">
									<span class="glyphicon glyphicon-star"></span>
								</c:forEach></td>
							<td><c:forEach begin="1" end="${dto.re_level}" step="1">&nbsp;&nbsp;</c:forEach>
								<c:choose>
									<c:when test="${dto.show_op == 'n'}">${dto.title}<span
											class="badge">삭제된 게시물</span>
									</c:when>
									<c:otherwise>
										<a href="#" onclick="javascript: reviewDetail('${dto.num}')">${dto.title}
											<span class="badge" style="background-color: olive;">${dto.comment_count}</span>
										</a>
									</c:otherwise>
								</c:choose></td>
							<td>${dto.writer}</td>
							<td>${dto.read_count}</td>
							<td><fmt:formatDate value="${dto.reg_date}" type="date"
									pattern="yyyy-MM-dd" /></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<div class="row" align="center">
				<div class="col-sm-12">
					<ul class="pagination pagination">
						<c:if test="${pager.curPage > 1}">
							<li><a href="#" onclick="boardList('1')">첫 페이지</a></li>
						</c:if>
						<c:if test="${pager.curBlock > 1}">
							<li><a href="#" onclick="boardList('${pager.prevPage}')">이전</a></li>
						</c:if>
						<c:forEach var="page" begin="${pager.blockStartPage}"
							end="${pager.blockEndPage}">
							<c:choose>
								<c:when test="${page == pager.curPage}">
									<li><a href="#" style="text-decoration: underline;">${page}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#" onclick="boardList('${page}')">${page}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${pager.curBlock < pager.totBlock}">
							<li><a href="#" onclick="boardList('${pager.nextPage}')">다음</a></li>
						</c:if>
						<c:if test="${pager.curPage < pager.totPage}">
							<li><a href="#" onclick="boardList('${pager.totPage}')">마지막
									페이지</a></li>
						</c:if>
					</ul>
				</div>
			</div>
			<button type="button" class="btn btn-default"
				onclick="location.href='${path}/reviewboard_servlet/select_cafe.do'">
				<span class="glyphicon glyphicon-pencil"></span>&nbsp;글쓰기
			</button>
		</form>
	</div>
	<%@ include file="../include/footer.jspf"%>
</body>
</html>
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
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>
	<div class="container">
		<h2>관리자 페이지</h2>
		<p>아이디를 클릭하면 회원이 작성한 리뷰 목록을 볼 수 있습니다.</p>
		<form name="form4" class="form-inline" method="post">
			<div class="form-group">
				<label for="searchOption">검색옵션 : </label>
				<c:choose>
					<c:when test="${requestScope.searchOption =='userid'}">
						<select name="searchOption" class="form-control" id="searchOption">
							<option value="userid" selected>아이디</option>
							<option value="name">이름</option>
							<option value="all">아이디/이름</option>
						</select>
					</c:when>
					<c:when test="${requestScope.searchOption =='name'}">
						<select name="searchOption" class="form-control" id="searchOption">
							<option value="userid">아이디</option>
							<option value="name" selected>이름</option>
							<option value="all">아이디/이름</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="searchOption" class="form-control" id="searchOption">
							<option value="userid">아이디</option>
							<option value="name">이름</option>
							<option value="all" selected>아이디/이름</option>
						</select>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="input-group">
				<input type="text" class="form-control" name="searchKeyword"
					value="${searchKeyword}">
				<div class="input-group-btn">
					<button class="btn btn-default" type="button"
						onclick="adminList('1')">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>num</th>
						<th>userid</th>
						<th>name</th>
						<th>email</th>
						<th>hp</th>
						<th>address</th>
						<th>birthday</th>
						<th>join date</th>
					</tr>
				</thead>
				<c:forEach var="dto" varStatus="loop" items="${list}">
					<tbody>
						<tr>
							<td><input name="checkbox" type="checkbox"
								value="${dto.userid}"></td>
							<td>${dto.rn}</td>
							<td><a href="#">${dto.userid}</a></td>
							<td>${dto.name}</td>
							<td>${dto.email}</td>
							<td>${dto.hp}</td>
							<td>${dto.address}</td>
							<td>${fn:substring(dto.birthday,0,10)}</td>
							<td><fmt:formatDate value="${dto.join_date}" type="date"
									pattern="yyyy-MM-dd" /></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<div class="row" align="center">
				<div class="col-sm-12">
					<ul class="pagination pagination">
						<c:if test="${pager.curPage > 1}">
							<li><a href="#" onclick="list('1')">첫 페이지</a></li>
						</c:if>
						<c:if test="${pager.curBlock > 1}">
							<li><a href="#" onclick="list('${pager.prevPage}')">이전</a></li>
						</c:if>
						<c:forEach var="page" begin="${pager.blockStartPage}"
							end="${pager.blockEndPage}">
							<c:choose>
								<c:when test="${page == pager.curPage}">
									<li><a href="#" style="text-decoration: underline;">${page}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#" onclick="list('${page}')">${page}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${pager.curBlock < pager.totBlock}">
							<li><a href="#" onclick="list('${pager.nextPage}')">다음</a></li>
						</c:if>
						<c:if test="${pager.curPage < pager.totPage}">
							<li><a href="#" onclick="list('${pager.totPage}')">마지막
									페이지</a></li>
						</c:if>
					</ul>
				</div>
			</div>
			<button type="button" class="btn btn-default" onclick="adminDelete()">삭제하기</button>
		</form>
	</div>
	<%@ include file="../include/footer.jspf"%>
</body>
</html>
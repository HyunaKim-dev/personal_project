<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<title>brown stars</title>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>


	<div class="jumbotron">
		<div class="container text-center">
			<h1>Brown Stars</h1>
			<p>당신이 찾던, 신뢰할 수 있는 커피 플레이스</p>
		</div>
	</div>
	<div class="container-fluid bg-3 text-center">
		<form name="form5" class="form-inline" method="post">
			<h3></h3>
			<br>
			<div style="margin: 3% 5% 5% 5%;">
				<div class="form-group">
					<label for="searchOption"></label>
					<c:choose>
						<c:when test="${searchOption =='cafe_name'}">
							<select name="searchOption" class="form-control"
								id="searchOption">
								<option value="cafe_name" selected>이름</option>
								<option value="menu">메뉴</option>
								<option value="all">이름/메뉴</option>
							</select>
						</c:when>
						<c:when test="${searchOption =='menu'}">
							<select name="searchOption" class="form-control"
								id="searchOption">
								<option value="cafe_name">이름</option>
								<option value="menu" selected>메뉴</option>
								<option value="all">이름/메뉴</option>
							</select>
						</c:when>
						<c:otherwise>
							<select name="searchOption" class="form-control"
								id="searchOption">
								<option value="cafe_name">이름</option>
								<option value="menu">메뉴</option>
								<option value="all" selected>이름/메뉴</option>
							</select>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${orderOption =='stars_avg'}">
							<select name="orderOption" class="form-control" id="searchOption">
								<option value="cafe_name">이름순</option>
								<option value="stars_avg" selected>평점순</option>
								<option value="stars_count" data-toggle="tooltip"
									title="평가횟수 기준">인기순</option>
							</select>
						</c:when>
						<c:when test="${orderOption =='stars_count'}">
							<select name="orderOption" class="form-control" id="searchOption">
								<option value="cafe_name">이름순</option>
								<option value="stars_avg">평점순</option>
								<option value="stars_count" data-toggle="tooltip"
									title="평가횟수 기준" selected>인기순</option>
							</select>
						</c:when>
						<c:otherwise>
							<select name="orderOption" class="form-control" id="searchOption">
								<option value="cafe_name" selected>이름순</option>
								<option value="stars_avg">평점순</option>
								<option value="stars_count" title="평가횟수 기준"
									data-toggle="tooltip">인기순</option>
							</select>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="input-group">
					<input type="text" class="form-control" name="searchKeyword"
						value="${searchKeyword}">
					<div class="input-group-btn">
						<button class="btn btn-default" type="button"
							onclick="cafeList('1')">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>

				<c:forEach var="dto" items="${list}" varStatus="loop">
					<c:if test="${loop.count==1 or (loop.count/4) == 1}">
						<div class="row" id="myTable">
					</c:if>
					<div class="col-sm-4" style="padding: 3% 3% 3% 3%;">
						<a
							href="${path}/cafe_servlet/cafe_detail.do?cafe_num=${dto.cafe_num}">
							<img src="${dto.img_src}" class="img-rounded"
							style="width: 100%; height: 350px;" alt="CafeImage">
						</a>
						<table class="cafetable" style="margin-top: 5%;">
							<tr>
								<td class="star" colspan="2"><c:forEach begin="1"
										end="${dto.stars_avg}" step="1">
										<span class="glyphicon glyphicon-star"></span>
									</c:forEach></td>
							</tr>
							<tr>
								<td class="cafaname" colspan="2"><a
									href="${path}/cafe_servlet/cafe_detail.do?cafe_num=${dto.cafe_num}">
										${dto.cafe_name}</a>
									<hr></td>
							</tr>
							<tr>
								<td class="cafeinfo">전화</td>
								<td class="cafeinfo">${dto.tel}</td>
							</tr>
							<tr>
								<td class="cafeinfo">주소</td>
								<td class="cafeinfo">${dto.address}</td>
							</tr>
							<tr>
								<td class="cafeinfo2" colspan="2"><hr>${dto.short_review}</td>
							</tr>
						</table>
					</div>
					<c:if test="${(loop.count/3) == 1 or loop.last}">
			</div>
			</c:if>
			</c:forEach>

			<div class="row" align="center">
				<div class="col-sm-12">
					<ul class="pagination pagination">
						<c:if test="${pager.curPage > 1}">
							<li><a href="#" onclick="cafeList('1')">첫 페이지</a></li>
						</c:if>
						<c:if test="${pager.curBlock > 1}">
							<li><a href="#" onclick="cafeList('${pager.prevPage}')">이전</a></li>
						</c:if>
						<c:forEach var="page" begin="${pager.blockStartPage}"
							end="${pager.blockEndPage}">
							<c:choose>
								<c:when test="${page == pager.curPage}">
									<li><a href="#" style="text-decoration: underline;">${page}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#" onclick="cafeList('${page}')">${page}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${pager.curBlock < pager.totBlock}">
							<li><a href="#" onclick="cafeList('${pager.nextPage}')">다음</a></li>
						</c:if>
						<c:if test="${pager.curPage < pager.totPage}">
							<li><a href="#" onclick="cafeList('${pager.totPage}')">마지막
									페이지</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="../include/footer.jspf"%>
</body>
</html>
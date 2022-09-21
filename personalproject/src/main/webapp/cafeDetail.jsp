<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<title>cafe Detail</title>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>
	<%@ include file="../include/login_check.jspf"%>
	<div class="container" style="margin-top: 5%;">
		<h1>${dto.cafe_name}<c:forEach begin="1" end="${dto.stars_avg}"
				step="1">
				<span class="glyphicon glyphicon-star"></span>
			</c:forEach>
		</h1>${dto.cafe_name_sub}<hr>
		<form name="form6" class="form-inline" method="post" action="">
			<table width="100%" align="center">
				<tr>
					<td><h4>메뉴</h4></td>
					<td><p style="width: 70%;">${dto.menu}</p></td>
				<tr>
					<td><h4>가격대</h4></td>
					<td><p>${dto.price}</p></td>
				</tr>
				<tr>
					<td><h4>개업일(연)</h4></td>
					<td><p>${dto.open_date}</p></td>
				</tr>
				<tr>
					<td><h4>전화</h4></td>
					<td><p>${dto.tel}</p></td>
				</tr>
				<tr>
					<td><h4>주소</h4></td>
					<td><p>${dto.address}</p></td>
				</tr>
				<tr>
					<td><h4>주차</h4></td>
					<td><p>${dto.parking}</p></td>
				</tr>
				<tr>
					<td><h4>영업시간</h4></td>
					<td><p>${dto.opening_hours}</p></td>
				</tr>
				<tr>
					<td style="width: 10%;"><h4>인스타그램</h4></td>
					<td><p>
							&nbsp;&nbsp;
							<button type="button" class="btn btn-default"
								onclick="location.href='${dto.instagram}'">
								<span class="fa-brands fa-instagram"></span>인스타그램
							</button>
						</p></td>
				</tr>
				<tr>
					<td><h4>웹사이트</h4></td>
					<td>&nbsp;&nbsp;
						<button type="button" class="btn btn-default"
							onclick='location.href="${dto.website}"'>
							<span class="fa-solid fa-square-arrow-up-right"></span>웹사이트
						</button>
				</tr>
				<tr>
					<td><h4>평가하기</h4></td>
					<td>
						<div class="form-group">
							<label for="star"></label> <select name="star" id="star"
								class="form-control">
								<option value="1">☆</option>
								<option value="2">☆☆</option>
								<option value="3" selected>☆☆☆</option>
								<option value="4">☆☆☆☆</option>
								<option value="5">☆☆☆☆☆</option>
							</select>
						</div>
						<button type="button" class="btn btn-default"
							onclick="updateStars('${dto.cafe_num}')">제출</button>
					</td>
				</tr>
			</table>
			<div>
				<div>
					<hr>
					<img src="${dto.img_src}" width="60%;">
				</div>
				<hr>
				<table id="cafetable3">
					<tr>
						<td>
							<h4>
								리뷰&nbsp;&nbsp;
								<button type="button" class="btn btn-default"
									onclick="location.href='${path}/reviewboard_servlet/board_list.do'">
									<span class="glyphicon glyphicon-pencil"></span>모든 리뷰보기
								</button>
							</h4>
							<p>${dto.review_main}</p>
					</tr>
					<tr>
						<td>
							<h4>독자 평</h4>
							<p>${dto.review_sub}</p>
						</td>
					</tr>
				</table>
				<p>
					<br>
					<iframe src="${dto.addr_map}" width="600" height="450"
						style="border: 0;" allowfullscreen="" loading="lazy"
						referrerpolicy="no-referrer-when-downgrade"></iframe>
				</p>
			</div>
		</form>
	</div>
	<%@ include file="../include/footer.jspf"%>
</body>
</html>
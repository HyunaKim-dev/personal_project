<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../include/head_tag.jspf"%>
<title>홈</title>
</head>
<body>
	<%@ include file="../include/navbar.jspf"%>

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img
					src="https://bigseventravel.com/wp-content/uploads/2019/08/rocket-coffee-bangkok.jpg"
					alt="MainImage" style="width: 100%;">
				<div class="carousel-caption">
					<h3></h3>
					<p></p>
				</div>
			</div>
			<div class="item">
				<img
					src="https://th.bing.com/th/id/R.20e6c37ffef76ce3afaf14b3e14f88d8?rik=9n8U2UoyPiws%2bg&riu=http%3a%2f%2fpostfiles1.naver.net%2f20160626_160%2fsee8n5m_1466932789983vlKo6_JPEG%2fDSC04141.JPG%3ftype%3dw966&ehk=oSPU5oOEy%2f3o1blvBXe%2f9t8P049d3X8f7CiSaBovemw%3d&risl=&pid=ImgRaw&r=0"
					alt="ImagMainImagee" style="width: 100%">
				<div class="carousel-caption">
					<h3></h3>
					<p></p>
				</div>
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>


	<div class="container text-center" style="margin-top: 5%;">
		<h2>Recently Opened</h2>
		<hr>
		<div class="row">
			<div class="col-sm-4">
				<img src="${cdto.img_src}" style="width: 100%" alt="Image">
				<h4>[${cdto.cafe_name}]</h4>
			</div>
			<div style="width: 90%;">
				<p>${cdto.review_main}</p>
				<div style="padding-top: 3%;">
					<p>주소: ${cdto.address}</p>
					<p>영업시간: ${cdto.opening_hours}</p>
					<p>전화: ${cdto.tel}</p>
					<p>주차: ${cdto.parking}</p>
				</div>
			</div>
		</div>

		<h2>Recent Reviews</h2>
		<hr>
		<c:forEach var="bdto" items="${list}" begin="1" end="6"
			varStatus="loop">
			<div class="media">
				<div class="media-body">
					<h5 class="media-heading">${bdto.writer}&nbsp;&nbsp;<small><i>Posted
								on <fmt:formatDate value="${bdto.reg_date}"
									pattern="yyyy년 MM월 dd일" />
						</i></small>
					</h5>
					<p>
						${fn:substring(bdto.content, 0, 80)}&nbsp;···&nbsp;[${bdto.cafe_name}]&nbsp;
						<c:forEach begin="1" end="${bdto.stars}" step="1">
							<span class="glyphicon glyphicon-star"></span>
						</c:forEach>
					</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<br>

	<%@ include file="../include/footer.jspf"%>
</body>
</html>

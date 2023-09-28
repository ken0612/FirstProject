<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.carousel-image {
	width: 900px; /* 设置宽度 */
	height: 600px; /* 设置高度 */
}
</style>
</head>
<body>
	<jsp:include page="home.jsp"></jsp:include>
	<div class="container mt-3">
		<%
		Integer count = (Integer) request.getAttribute("searchCount");
		if (count != null) {
		%>
		<h1>共找到 ${searchCount} 項產品</h1>
		<%
		}
		%>

		<div id="carouselExampleControls" class="carousel slide"
			data-bs-ride="carousel" >
			<div class="carousel-inner">
				<div class="carousel-item active" data-bs-interval="5000">
					<img
						src="https://cms.cdn.91app.com/images/compress/41620/3d24bf77-a062-448b-9cc8-2718662548c2-1695293395-bi3qm9ces8_m_1920x1000.webp"
						class="d-block w-100 carousel-image" alt="...">
				</div>
				<div class="carousel-item" data-bs-interval="5000">
					<img
						src="https://cms.cdn.91app.com/images/original/41620/3d24bf77-a062-448b-9cc8-2718662548c2-1690184345-171z0anmh2_m_1200x628_800x418_400x209.jpg"
						class="d-block w-100 carousel-image" alt="...">
				</div>
				<div class="carousel-item" data-bs-interval="5000">
					<img
						src="https://cms.cdn.91app.com/images/original/41620/3d24bf77-a062-448b-9cc8-2718662548c2-1694073551-anu86l3m79_m_1920x1080_1280x720_1124x632_800x450_600x338_400x225.jpg"
						class="d-block w-100 carousel-image" alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleControls" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleControls" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>



		<div class="row">
			<c:forEach items="${productList}" var="product">
				<div class="col-md-3">
					<!-- 使用col-md-3将每个card分为4个等宽的列 -->
					<div class="card" style="width: 18rem;">
						<form action=addtocart method="get">
							<img src="${product.purl}" class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title">${product.pname}</h5>
								<p class="card-text">${product.pdesc}</p>
								<p class="card-text">價格: ${product.pprice}</p>
								<input type="hidden" name="tocart" value="${product.pid}">
								<input type="submit" class="btn btn-primary" value="加入購物車">
							</div>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>



</body>
</html>
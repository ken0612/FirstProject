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
</head>
<body>
	<jsp:include page="home.jsp"></jsp:include>
	
	
	
	
	
	
	
	
	<div class="container mt-3">
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
						<input type="submit" class="btn btn-primary" value="加入購物車" >
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
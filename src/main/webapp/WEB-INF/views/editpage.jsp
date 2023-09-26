<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>編輯商品</title>
<!-- 引入Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<jsp:include page="home.jsp"></jsp:include>
	<div class="container mt-4">
		<h1>編輯商品</h1>
		<!-- 商品編輯表單 -->
		<form action="editconfirm" method="post">
			<div class="form-group">
				<label for="productName">商品名稱:</label> <input type="text"
					class="form-control" id="productName" name="productName" value="${productbean.pname}" required>
			</div>
			<div class="form-group">
				<label for="productPrice">價格:</label> <input type="number"
					class="form-control" id="productPrice" name="productPrice" value="${productbean.pprice}" required>
			</div>
			<div class="form-group">
				<label for="productDescription">描述:</label>
				<textarea class="form-control" id="productDescription"
					name="productDescription" >${productbean.pdesc}</textarea>
			</div>
			<div class="form-group">
				<label for="productImageUrl">圖片URL:</label> <input type="text"
					class="form-control" id="productImageUrl" name="productImageUrl" value="${productbean.purl}">
			</div>
			<input type="hidden" name="productId" value="${productbean.pid}">
			<button type="submit" class="btn btn-primary">確定修改</button>
			
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
<%@page import="com.beans.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>商品管理</title>
<!-- 引入Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="home.jsp"></jsp:include>
	<div class="container mt-4">
		<h1>商品管理</h1>

		<!-- 商品上架表單 -->
		<h2>上架新商品</h2>
		<form action="addProduct" method="post">
			<div class="form-group">
				<label for="productName">商品名稱:</label> <input type="text"
					class="form-control" id="productName" name="productName" required>
			</div>
			<div class="form-group">
				<label for="productPrice">價格:</label> <input type="number"
					class="form-control" id="productPrice" name="productPrice" required>
			</div>
			<div class="form-group">
				<label for="productDescription">描述:</label>
				<textarea class="form-control" id="productDescription"
					name="productDescription"></textarea>
			</div>
			<div class="form-group">
				<label for="productImageUrl">圖片URL:</label> <input type="text"
					class="form-control" id="productImageUrl" name="productImageUrl">
			</div>

			<button type="submit" class="btn btn-primary"
				style="margin-top: 10px;">上架商品</button>
		</form>

		<!-- 商品列表 -->
		<h2>商品列表</h2>
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>商品名稱</th>
					<th>價格</th>
					<th>描述</th>
					<th>圖片</th>
					<th>是否可用</th>
					<th>選項</th>
				</tr>
			</thead>
			<tbody>
				<!-- 這裡使用後端模板引擎（如JSP、Thymeleaf等）來填充商品列表 -->
				<!-- 你的後端代碼應該根據數據庫內容動態生成這些行 -->
				<c:forEach items="${productlist}" var="product">
					<tr>
						<td>${product.pid}</td>
						<td>${product.pname}</td>
						<td>${product.pprice}</td>
						<td>${product.pdesc}</td>
						<td><img src="${product.purl}" alt="商品"
							style="max-width: 100px; max-height: 100px;"></td>
						<td>${product.pavalible}</td>
						<td>
							<div class="row">
								<div class="col-md-4">
									<form action="deleteproduct" method="post">
									<button type="submit" class="btn btn-danger"
										data-bs-toggle="modal" data-bs-target="#exampleModal"
											name="delete" value="${product.pid}">刪除</button>
									</form>
								</div>
								<div class="col-md-4">
									<form action="editproduct">
										<button type="submit" class="btn btn-primary" name="edit"
											value="${product.pid}">編輯</button>
									</form>
								</div>
							</div>

						</td>
					</tr>
				</c:forEach>



			</tbody>
		</table>


	</div>



	<!-- 引入Bootstrap JavaScript（包括jQuery） -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
	
</body>
</html>

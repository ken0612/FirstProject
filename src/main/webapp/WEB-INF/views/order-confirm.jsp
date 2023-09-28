<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.dao.ProductManageDao"%>
<%@page import="com.beans.ProductBean"%>
<%@page import="com.beans.CartDetailBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>確認您的訂單</title>
<!-- 引入Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 引入Bootstrap JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

</head>
<body>
	<jsp:include page="home.jsp"></jsp:include>
	<!-- 購物車內容 -->
	<div class="container mt-4">
		<h1>訂單內容</h1>
		<table class="table">
			<thead>
				<tr>
					<th>商品名稱</th>
					<th>價格</th>
					<th>數量</th>
					<th>小計</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<!-- 商品1 -->
				<c:forEach items="${cartdetailbeans}" var="detail">
					<tr>
						<td>${detail.productName}</td>
						<td>${detail.price}</td>
						<td>${detail.quantity}</td>
						<td>${detail.price * detail.quantity}</td>
					
					</tr>
				</c:forEach>

				<!-- 其他商品 -->
				<!-- 在此循環顯示購物車中的其他商品 -->
			</tbody>

		</table>
		<div class="text-center mt-4">
			<h1>總金額： ${totalPrice}</h1>
		</div>
		<h2>填寫交貨地址</h2>
		<form action="submitorder" method="post">
			<div class="mb-3">
				<label for="fullName" class="form-label">全名</label> <input
					type="text" class="form-control" id="fullName" name="fullName" value="${userbean.name}"
					required>
			</div>
			<div class="mb-3">
				<label for="address" class="form-label">地址</label>
				<input type="text" class="form-control" id="address" name="address" value="${userbean.address }" required>
			</div>
			<div class="mb-3">
				<label for="phoneNumber" class="form-label">聯絡電話</label> <input
					type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="${userbean.phone}"
					required>
			</div>
			<div class="text-center mt-4">
			<button type="submit" class="btn btn-primary">提交訂單</button>
			</div>
		</form>
		<br/>
		<br/>
	</div>


</body>
</html>

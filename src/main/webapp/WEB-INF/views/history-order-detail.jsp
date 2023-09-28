<%@page import="org.springframework.ui.Model"%>
<%@page import="com.beans.UserLoginBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>訂單記錄</title>
<!-- 引入 Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="home.jsp"></jsp:include>
	<div class="container mt-4">
	<h1>訂單詳細</h1>
	<table class="table">
		<thead>
			<tr>
				<th>商品名稱</th>
				<th>商品縮圖</th>
				<th>購買數量</th>
				<th>單價</th>
				<th>總金額</th>
				<th>訂單狀態</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orderDetail}" var="order">
				<tr>
					<td>${order.productName}</td>
					<td><img src="${order.productUrl}" style="max-width: 100px; max-height: 100px; class="card-img-top" alt="..."></td>
					<td>${order.quantity}</td>
					<td>${order.price}</td>
					<td>${order.quantity * order.price}</td>
					<td>待出貨</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<!-- 引入 Bootstrap JavaScript -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
</body>
</html>

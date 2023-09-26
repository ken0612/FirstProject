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
<title>購物車</title>
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
		<h1>購物車</h1>
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
					<tr>
						<td>${cartdetailbeans.productName}</td>
						<td>${cartdetailbeans.price}</td>
						<td><input type="number" value="${cartdetailbeans.quantity}"></td>
						<td>${cartdetailbeans.price * cartdetailbeans.quantity}</td>
						<td>
							<form action="deleteformcart">
								<input type="hidden" name="del" value="${cartdetailbeans.productId}">
								<input type="submit" class="btn btn-danger" value="刪除">
							</form>
						</td>
						<td>
							<form action="editcart">
								<input type="hidden" name="edit" value="${cartdetailbeans.productId}">
								<input type="submit" class="btn btn-primary" value="編輯">
							</form>
						</td>
					</tr>

				<!-- 其他商品 -->
				<!-- 在此循環顯示購物車中的其他商品 -->
			</tbody>

		</table>
		
	</div>


</body>
</html>

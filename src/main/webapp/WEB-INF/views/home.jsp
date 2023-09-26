<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物商城</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
	<!-- 引入 Bootstrap 的 JavaScript 檔案 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
	
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="backtotop">線上商店</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">商品分類</a></li>
					<li class="nav-item"><a class="nav-link" href="#">聯絡客服</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Dropdown </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">Action</a></li>
							<li><a class="dropdown-item" href="#">Another action</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">Something else
									here</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link disabled">Disabled</a>
					</li>
				</ul>
				<form class="d-flex">
					<input class="form-control me-2" type="search"
						placeholder="輸入商品名稱..." aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
				<ul class="navbar-nav ">
					<%
					if (session.getAttribute("loggedInUser") != null) {
					%>
					<li class="nav-item"><a class="nav-link">你好！<%=session.getAttribute("UserName")%></a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">選項 </a>
						<ul class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="memberpage">會員中心</a></li>
							<li><a class="dropdown-item" href="cart">購物車</a></li>
							<li><a class="dropdown-item" href="productmanager">管理賣場</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="logout">登出</a></li>
						</ul></li>
				</ul>
				
				<%
				} else {
				%>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="Login">登入</a></li>
					<li class="nav-item"><a class="nav-link" href="SignUp">註冊</a></li>
					<%
					}
					%>

				</ul>
			</div>
		</div>
	</nav>


</body>





</html>
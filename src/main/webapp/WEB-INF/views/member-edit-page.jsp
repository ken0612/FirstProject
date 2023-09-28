<%@page import="org.springframework.ui.Model"%>
<%@page import="com.beans.UserLoginBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">

<title>編輯會員資訊</title>
</head>
<body>
<jsp:include page="home.jsp"></jsp:include>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
			<h1>編輯個人資料</h1>
				<form method="post" action="membereditconfirm">
					<div class="form-group">
						<label for="name">姓名</label> <input type="text"
							class="form-control" id="name" name="name" value="${user.name}" required>
					</div>
					<div class="form-group">
						<label for="birthday">生日</label> <input type="date"
							class="form-control" id="birthday" name="birthday" value="${user.birthday}">
					</div>
					<div class="form-group">
						<label for="email">電子信箱</label> <input type="email"
							class="form-control" id="email" name="email" value="${user.email}"  required>
					</div>
					<div class="form-group">
						<label for="phone">手機號碼</label> <input type="tel"
							class="form-control" id="phone" name="phone" value="${user.phone}" required>
					</div>
					<div class="form-group">
						<label for="address">交貨地址</label> <input type="tel"
							class="form-control" id="address" name="address" value="${user.address}" required>
					</div>
					<button type="submit" class="btn btn-primary btn-block">確認修改</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
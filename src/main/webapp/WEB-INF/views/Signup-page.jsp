<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<!-- 引入Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.error {
	color: red
}
</style>
</head>
<body class="bg-light">
	<jsp:include page="home.jsp"></jsp:include>
	<div class="container mt-5">
		<h2>會員註冊</h2>
		<form:form action="signUpSubmit" method="post" modelAttribute="memberbean" >
			<div class="form-group">
				<label for="account">帳號</label> 
				<input type="text" placeholder="請輸入帳號"
					class="form-control" id="account" name="account" required>
				<form:errors path="account" cssClass="error" />
			</div>
			<div class="form-group">
				<label for="password">密碼</label> 
				<input type="password" placeholder="請輸入密碼(大小寫英文及至少一個特殊符號)"
					class="form-control" id="password" name="password" required>
				<form:errors path="password" cssClass="error" />
			</div>
			<div class="form-group">
				<label for="name">姓名</label> 
				<input type="text" class="form-control"
					id="name" name="name" required>
			</div>

			<div class="form-group">
				<label for="birthday">生日</label> 
				<input type="date"
					class="form-control" id="birthday" name="birthday">
			</div>
			<div class="form-group">
				<label for="email">電子信箱</label> 
				<input type="email"
					class="form-control" id="email" name="email" required>
			</div>
			<div class="form-group">
				<label for="phone">手機號碼</label> 
				<input type="tel"
					class="form-control" id="phone" name="phone" required>
			</div>
			<button type="submit" class="btn btn-primary">註冊</button>
		</form:form>>
	</div>


	<!-- 引入Bootstrap JS（放在body底部以加速頁面載入） -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

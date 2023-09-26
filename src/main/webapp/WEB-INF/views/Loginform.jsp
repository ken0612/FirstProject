<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- 引入Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
	.ermsgcolor {
	
		color:red
	}
	</style>
</head>
<body class="bg-gray">
<jsp:include page="home.jsp"></jsp:include>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">登入</div>
                <div class="card-body">
                    <form:form action="UserLogin" method="post" modelAttribute="userloginbean" >
						<p class="ermsgcolor">${errormsg}</p>
                        <div class="form-group">
                            <label for="account">使用者名稱</label>
                            <input type="text" class="form-control" id="account" name="account" placeholder="輸入使用者名稱">
                        </div>
                        <div class="form-group">
                            <label for="password">密碼</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="輸入密碼">
                        </div>
                        <button type="submit" class="btn btn-primary">登入</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 引入Bootstrap JS（放在body底部以加速頁面載入） -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

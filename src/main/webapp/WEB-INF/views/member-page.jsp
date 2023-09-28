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
    <title>會員中心</title>
    <!-- 引入 Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="home.jsp"></jsp:include>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4">
                <!-- 側邊欄 -->
                <div class="card">
                    <div class="card-header">
                        會員資訊
                    </div>
                    <div class="card-body">
                        <!-- 這裡加入會員的個人資訊 -->
                        <p>姓名：${memberbean.name} </p>
                        <p>電子郵件：${memberbean.email}</p>
                        <a href="memberinfoedit" class="btn btn-primary">編輯個人資料</a>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <!-- 主要內容區域 -->
                <h2>歡迎回來,${memberbean.name}！</h2>
                <p>這是您的會員中心，您可以在這裡編輯您的個人資料，查看訂單歷史，和更多功能。</p>
                <!-- 其他會員中心功能可以在這裡添加 -->
            </div>
        </div>
    </div>
	<div class="container mt-4">
    <h1>歷史訂單</h1>
    <table class="table">
        <thead>
            <tr>
                <th>訂單編號</th>
                <th>日期</th>
                <th>總金額</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${orderHistory}" var="order">
                <tr>
                    <td>${order.oId}</td>
                    <td>${order.createDate}</td>
                    <td>${order.totalPrice}</td>
                    <td>
                        <a href="orderDetails?orderId=${order.oId}" class="btn btn-primary">查看詳細</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
    <!-- 引入 Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>登录</title>
<link rel="stylesheet" type="text/css" href="asset/css/login.css"/>
</head>

<body>
<div class="login-form">
    <span class="tip" style="display:none"></span>
    <div class="form">
        <div><label>用户名：</label><input type="text" class="elm" id="username" maxlength="32" autocomplete="off" /></div>
        <div><label>密码：</label><input type="password" class="elm" id="password" maxlength="32" /></div>
        <div><input type="button" class="btn" id="login" value="登录" /></div>
    </div>
    <span class="icon"></span>
</div>
<p>痰热清上市后临床再评价系统</p>
<input type="hidden" id="BasePath" value="<%=basePath %>/" />

<script src="src/lib/jquery.js"></script>
<script src="src/login.js"></script>
</body>
</html>
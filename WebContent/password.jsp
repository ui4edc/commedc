<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="asset/css/login.css"/>
</head>

<body>
<div class="login-form password-form">
    <span class="tip" style="display:none"></span>
    <div class="form">
        <div><label>原密码：</label><input type="password" class="elm" id="oldPassword" maxlength="32" /></div>
        <div><label>新密码：</label><input type="password" class="elm" id="newPassword" maxlength="32" /></div>
        <div><label>确认密码：</label><input type="password" class="elm" id="repeatPassword" maxlength="32" /></div>
        <div><input type="button" class="btn" id="change" value="修改" /></div>
    </div>
</div>
<p>痰热清上市后临床再评价系统</p>
<input type="hidden" id="BasePath" value="<%=basePath %>/" />

<script src="src/lib/jquery.js"></script>
<script src="src/password.js"></script>
</body>
</html>
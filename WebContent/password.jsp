<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <form method="post" action="">
        <div><label>原密码：</label><input type="password" class="elm" name="oldPassword" id="oldPassword" maxlength="32" /></div>
        <div><label>新密码：</label><input type="password" class="elm" name="newPassword" id="newPassword" maxlength="32" /></div>
        <div><label>确认密码：</label><input type="password" class="elm" name="repeatPassword" id="repeatPassword" maxlength="32" /></div>
        <div><input type="submit" class="btn" value="确定" /></div>
    </form>
</div>
<p>痰热清上市后临床再评价系统</p>

<script>var errorMsg = "${errorMsg}";</script>
<script src="src/lib/jquery.js"></script>
<script src="src/password.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>痰热清上市后临床再评价系统</title>
<link rel="stylesheet" type="text/css" href="asset/css/all.css"/>
</head>

<body>
<div id="Head"></div>
<div id="Nav">
    <a href="#index/" class="index">首页</a>
    <!--药师-->
    <a href="#list/" class="list">观察表</a>
    <!--CRM-->
    <a href="#supervise/" class="supervise">数据监察</a>
    <a href="#dict/" class="dict">数据字典</a>
    <a href="#upload/" class="upload">图片上传</a>
    <!--DM-->
    <a href="#stat/" class="stat">全局统计</a>
    <a href="#data/" class="data">数据管理</a>
    <a href="#dict/" class="dict">数据字典</a>
    <a href="#account/" class="account">账户管理</a>
</div>
<div id="Main"></div>
<div id="Foot"></div>
<div id="Mask" style="display:none"></div>
<div id="Message" style="display:none"></div>

<input type="hidden" id="BasePath" value="<%=basePath %>/" />
<input type="hidden" id="RoleName" value="${role}" />
<input type="hidden" id="RoleId" value="${roleId} }" />
<input type="hidden" id="UserName" value="${user}" />
<input type="hidden" id="UserId" value="${userId}" />
<input type="hidden" id="HospitalName" value="${organization}" />

<script src="asset/js/lib.js"></script>
<script src="asset/js/app.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户注册&nbsp;&nbsp;|&nbsp;&nbsp;鲸鱼聊天室</title>
		<!-- 引入样式表 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/regist.css" />
		<!-- 引入jQuery库  -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
		<!-- 引入我们自己的js文件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Regist.js"></script>
	</head>
	<body>
		<div id="top">
			<h1>用户注册</h1>
			<hr/>
		</div>
		<div id="center">
			<br/>
			<form action="" method="post">
			<table>
				<tr>
					<td colspan="3">
						<p>提示：用户名和密码只能由 字母、_或数字组成,且长度在3到10之间！</p>
					</td>
				</tr>
				<tr>
					<td class="td_right">用户名：</td>
					<td>
						<input id="userName" type="text" name="userName" placeholder="请输入用户名" onblur="checkName(this);">
					</td>
					<td>
						<p id="userNameTip">*&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td class="td_right">登入密码：</td>
					<td>
						<input id="password1" type="password" name="password1" placeholder="请输入登入密码" onblur="checkPwd1(this);">
					</td>
					<td>
						<p id="password1Tip">*&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td class="td_right">确认密码：</td>
					<td>
						<input id="password2" type="password" name="password2" placeholder="请输入确认密码" onblur="checkPwd2(this);">
					</td>
					<td>
						<p id="password2Tip">*&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
						<input id="reset" type="button" value="重置" onclick="formReset();"/>&nbsp;&nbsp;
						<input id="regist" type="button" value="注册" onclick="formSubmit();"/>
					</td>
				</tr>
			</table>
			</form>
		</div>
		
	</body>
</html>
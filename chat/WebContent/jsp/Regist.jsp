<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>用户注册&nbsp;&nbsp;|&nbsp;&nbsp;鲸鱼聊天室</title>
		<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/regist.css"/>
	</head>
	<body>
		<header>
			<a href="Login.jsp">
				<img alt="logo" src="${pageContext.request.contextPath}/imgs/logo.png" class="logo">
			</a>
			<div class="desc">欢迎注册</div>
			 <h1 align="center" style="position: absolute;left: 42%;top: 40%; color: #9999CC;">Whale-Chat</h1>
		</header>
		<section>
			<form action="" onsubmit="return false;">
				<div class="register-box">
					<label for="username" class="username_label">用 户 名
						<input maxlength="20" type="text" placeholder="您的用户名和登录名"/>
					</label>
					<div class="tips">
					</div>
				</div>
				<div class="register-box">
					<label for="username" class="other_label">设 置 密 码
						<input maxlength="20" type="password" placeholder="建议至少使用两种字符组合"/>
					</label>
					<div class="tips">
						
					</div>
				</div>
				<div class="register-box">
					<label for="username" class="other_label">确 认 密 码
						<input maxlength="20" type="password" placeholder="请再次输入密码"/>
					</label>
					<div class="tips">
						
					</div>
				</div>
				<div class="submit_btn">
					<button type="submit" id="submit_btn">立 即 注 册</button>
				</div>
			</form>
		</section>
		<script src="${pageContext.request.contextPath}/js/Regist.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
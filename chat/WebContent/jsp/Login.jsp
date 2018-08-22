<%@page import="net.hncu.chat.user.javabean.UserModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>用户登录&nbsp;&nbsp;|&nbsp;&nbsp;鲸鱼聊天室</title>
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<!-- 引入样式表 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
	</head>
	<body>
		<% 
			UserModel user = (UserModel)session.getAttribute("user");
			if( user != null ){
				response.sendRedirect("Main.jsp");
			}
		%>
		<div class="sucaihuo-container ">
			<div class="form-bg" style="padding: 20px 0;">
		        <div class="container">
		            <div class="row">
		                <div class="col-md-offset-3 col-md-6">
		                	<div class="col-md-offset-1 col-md-10">
			                	<h1 style="color: #9999CC; margin-bottom: 40px;margin-top: 40px;">Whale-Chat</h1>
			                    <form class="form-horizontal" onsubmit="return false;">
			                        <span class="heading">用户登录</span>
			                        <div class="form-group popover-options">
			                            <input type="text" class="form-control" id="userName" placeholder="用户名" data-container="body" data-toggle="popover" onblur="checkCommon(this,'用户名');">
			                            <i class="glyphicon glyphicon-user"></i>
			                        </div>
			                        <div class="form-group">
			                            <input type="password" class="form-control" id="password1" placeholder="密　码" data-container="body" data-toggle="popover" onblur="checkCommon(this,'密码');">
			                            <i class="glyphicon glyphicon-lock"></i>
			                        </div>
			                        <div class="form-group">
			                            <button class="btn btn-default" onclick="formSubmit();">登录</button>
			                            <a href="Regist.jsp" class="center-block" style="position:absolute;top:4px;right:10%; font-size: 16pt; color: #DD0000;" >注册</a>
			                        </div>
			                    </form>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		
		<!-- 引入jQuery库  -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<!-- 引入我们自己的js文件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Login.js" charset="utf-8"></script>
	</body>
</html>
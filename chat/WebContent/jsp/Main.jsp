<%@page import="net.hncu.chat.user.javabean.UserModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>鲸鱼聊天室</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css" >
		<style type="text/css">
			html,body {
				width: 100%;
				height: 100%;
				margin: 0px;
				font-family: 宋体;
				font-size: 16pt;
			}
			div{
				border-radius:10px;
			}
			#user,#exit{
				float: right;
				margin: 10px;
			}
			#div_online_out{
				margin:0px;
				width: 100%;
				height: 250px;
				overflow: hidden;
			}
			#div_online{
				padding-right: 50%;
				width: 150%;
				height: 250px;
				overflow-x: hidden;
				overflow-y: scorll; 
			}
			#div_user_info{
				position: absolute;
				left: 100%; 
				width: 80%; 
				border: 1px solid #000; 
			}
			#div_user_info p{
				padding-left:10px;
				font-family: 宋体;
				font-size: 12pt;
				text-align: left;
			}
			#div_msg p {
				font-family: 宋体;
				font-size: 12pt;
			}
			#div_msg pre{
				margin:0px 25px;
				padding:0px 5px;
				font-family: 宋体;
				font-size: 14pt;
			}
		</style>
	</head>
	<body>
	<% 
		//用户必须登入
		@SuppressWarnings("unchecked")
		ArrayList<UserModel> onlineUsers = (ArrayList<UserModel>)application.getAttribute("onlineUsers");
		if( session.getAttribute("user") == null || onlineUsers == null ){
			response.sendRedirect("Login.jsp");
		}
	%>
		<!-- 下面是导航栏 -->
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="container-fluid">
			    <div class="navbar-header">
			        <a class="navbar-brand " href="#">鲸鱼聊天室</a>
			    </div>
			    <div>
			        <ul class="nav navbar-nav">
			            <li class="active"><a id="nav_home" href="#" class="glyphicon glyphicon-home"></a></li>
			            <li><a id="nav_personal" href="#" class="glyphicon glyphicon-star-empty">个人中心</a></li>
			        </ul>
			        <ul class="nav navbar-nav navbar-right">
			         	<li class="active" ><a href="#"><span class="glyphicon glyphicon-user"></span>${user.petName}</a></li>
			         	<li style="margin-right: 80px;"><a href="#" onclick="exit();"><span class="glyphicon glyphicon-remove"></span>注销</a></li>
			        </ul>
			    </div>
			</div>
		</nav>
		<!-- 上面面是导航栏 -->
		<!-- 下面是 home 部分 -->
		<div id="div_container_home" class="container">
			<div class="col-md-8 col-md-offset-2">
				<div class="row" style="height: 360px;">
					<div class="col-md-8 text-info bg-info" style="border-right: 3px solid #ffffff;border-bottom: 3px solid #ffffff; box-sizing: border-box;">
						<div class="col-md-10  col-md-offset-1">
							<div id="div_msg_out" style="width: 100%; height: 360px; overflow-y: hiddent;">
								<div id="div_msg" style="width: 130%; height: 360px; padding-right: 30%; overflow-y: scroll;">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4" style="background-color: #C4E1FF; text-align: center; height: inherit;">
						<h3>在线用户:</h3>
						<div id="div_online_out">
							<div id="div_online">
								<div id="div_user_info" >
								</div>
								<div id="div_users">
							
								</div>
							</div>
						</div>
					<span id="onlineNumber" class="badge">在线人数:<%=onlineUsers==null?0:onlineUsers.size() %></span>
					</div>
				</div>
				<div class="row bg-info" >
					<div class="col-md-8">
						<form action="" role="form">
							<div class="form-group">
								<marquee class="text-center" style="margin-top: 20px;">请文明聊天，切勿刷屏。</marquee>
								<div class="col-md-10 col-sm-10 col-xs-10">
									<textarea id="message" rows="4" class="form-control" style="font-family: 宋体; font-size: 16pt;"></textarea>
								</div>
							</div>
						</form>
						<div class="col-md-2">
							<br /> <br />
							<button id="btn_send" class="btn btn-success" onclick="send();">发送</button>
						</div>
					</div>
					<div class="col-md-12">
						<br />
					</div>
				</div>
			</div>
		</div>
		<!-- 上面是 home 部分 -->
		<!-- 下面是 个人中心 部分 -->
		<div id="div_container_personal" class="container"  style="display: none;">
			<div class="col-md-8 col-md-offset-2"  style="border: 1px solid #000; padding:10px; ">
				<!-- 下面是个人中心的导航 -->
				<div class="col-md-3" style="height: 500px; border: 1px solid #000;">
					<div id="btn_personal_info" class="btn btn-lg btn-block btn-primary active" style="margin-top:50px; ">个人信息</div>
					<div id="btn_personal_pwd" class="btn btn-lg btn-block btn-primary">修改密码</div>
				</div>
				<!-- 上面面是个人中心的导航 -->
				
				<!-- 下面是个人信息 -->
				<div id="div_personal_info" class="col-md-9" >
					<form action="" class="form-horizontal" role="form">
						<div class="form-group" style="margin-top: 50px;">
							<label for="petName" class="col-md-2 control-label col-md-offset-2" style="padding-top: 4px;">昵称</label>
							<div class="col-md-6">				
								<input id="petName" class="form-control" type="text" value="${user.petName}" onblur="checkPetName();"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label col-md-offset-2" style="padding-top: 4px;">性别</label>
							<div class="radio col-md-6">
								<label>
									<input type="radio" name="sex" value="1" />男
								</label>
								<label>
									<input type="radio" name="sex" value="2" />女
								</label>
								<label>
									<input type="radio" name="sex" value="0" checked="checked" />保密
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="profile" class=" control-label col-md-2 col-md-offset-2">简介</label>
							<div class=" col-md-6">
								<textarea id ="profile" name="profile" class="form-control" rows="3" onblur="checkProfile();">无</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2 col-md-offset-2" style="padding-top: 4px;">邮箱</label>
							<div class="col-md-6">
								<input id="email" class="form-control" type="email" name="email" value="无" onblur="checkEmail();" />
							</div>
						</div>
					</form>
					<br/>
					<br/>
					<div class="btn btn-warning col-md-2 col-md-offset-4" onclick="reset()">重置</div>
					<div class="btn btn-warning col-md-2 col-md-offset-1" onclick="updateInfo()">修改</div>
				</div>
				<!-- 上面是个人信息 -->
				
				<!-- 下面是修改密码 -->
				<div id="div_personal_pwd"  class="col-md-9" style=" display: none;">
					<form action="" class="form-horizontal" role="form">
						<div class="form-group" style="margin-top: 50px;">
							<label for="password" class="col-md-3 control-label col-md-offset-1" style="padding-top: 4px;">原&nbsp;密&nbsp;码</label>
							<div class="col-md-6">				
								<input id="password" class="form-control" type="password" placeholder="请输入原来的密码" onblur="checkPassword();"/>
							</div>
						</div>
						<div class="form-group">
							<label for="newPassword" class="col-md-3 control-label col-md-offset-1" style="padding-top: 4px;">新&nbsp;密&nbsp;码</label>
							<div class="col-md-6">				
								<input id="newPassword" class="form-control" type="password" placeholder="请输入新的密码" onblur="checkNewPassword();"/>
							</div>
						</div>
						<div class="form-group">
							<label for="newPasswordAgain" class="col-md-3 control-label col-md-offset-1" style="padding-top: 4px;">确认密码</label>
							<div class="col-md-6">				
								<input id="newPasswordAgain" class="form-control" type="password" placeholder="请输入确认密码" onblur="checkNewPasswordAgain();"/>
							</div>
						</div>
					</form>
					<div class="btn btn-danger col-md-2 col-md-offset-6 " style="margin-top: 25px;" onclick="updatePwd()">修改</div>
				</div>
				<!-- 上面是修改密码 -->
			</div>
		</div>
		<!-- 上面是 个人中心 部分 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			var lastMessageId = 0;
			$(document).ready(function() {
				validate();
				window.setInterval("validate()",2000);
				$("#div_user_info").hide();
			});
			
			/*下面这一块 处理  主导航栏 事件*/
			$("#nav_personal").click(function () {
				$("#nav_home").parent().removeClass("active");
				$(this).parent().addClass("active");
				$("#div_container_home").css("display","none");
				$("#div_container_personal").css("display","block");
				reset();
				$("#div_user_info").hide();
				
				return false;//关闭跳转
			});
			$("#nav_home").click(function () {
				$("#nav_personal").parent().removeClass("active");
				$(this).parent().addClass("active");
				$("#div_container_home").css("display","block");
				$("#div_container_personal").css("display","none");
				
				return false;//关闭跳转
			});
			/*上面这块 是处理 导航栏 函数*/
			
			//发送消息函数
			function send() {
				var url = "../MainServlet?action=send";
				var args = { 
							"message":$("#message").val(),
							"time":new Date() };
				$.getJSON(url,args,function(data) {
					$("#btn_send").popover({
						html:true,
						container:'body',
						content:data.res
					});
					$("#btn_send").popover('show');
					$("#message").val("");
					setTimeout(function () {
						$("#btn_send").popover("destroy");
					},2000);
				});
			}
			
			//退出函数
			function exit() {
				window.location.href="../ExitServlet";
				alert("欢迎您下次光临！");
			}
			
			//更新在线用户列表 和 聊天内容
			function validate(){
				var url = "../MainServlet?action=validate";
				var args = { 
							"lastMassageId":lastMessageId,
							"time":new Date()/*时间戳*/
							};
				$.getJSON( url, args, function(data){
					//更新在线用户列表
					$("#div_users").empty();
					if( data.onlineUsers.length != 0 ){
						for( var i = 0; i < data.onlineUsers.length; i++ ){
							$("#div_users").append("<p style='cursor: pointer;'><span onclick='queryUserInfo(this);'>" + data.onlineUsers[i].petName + "<input type='hidden' value='" + data.onlineUsers[i].username + "'/></span></p>");
							if( displayUsername == data.onlineUsers[i].username ){
								showUserInfo();
							}
						}
					}
					//更新在线用户人数
					$("#onlineNumber").text("在线人数:"+data.onlineUsers.length);
					//更新消息内容
					if ( data.messages.length > 0 ){
						for ( var i = 0; i < data.messages.length; i++ ) {
							if ( data.messages[i].username == "${user.username}" ) {
								$("#div_msg").append("<p align='right'>" + data.messages[i].petName + "&nbsp;<small>"+ data.messages[i].date + "</small></p>")
								 			 .append("<div align='right'><pre style='display: inline-block;padding:5px 10px;background:#ffffff;'>" + data.messages[i].message + "</pre></div>");
							} else {
								$("#div_msg").append("<p>" + data.messages[i].petName + "&nbsp;<small>"+ data.messages[i].date + "</small></p>")
								             .append("<pre style='display: inline-block;padding:5px 10px;background:#ffffff;'>" + data.messages[i].message + "</pre>");
							}
							lastMessageId = data.messages[i].id;
						}
						$("#div_msg").scrollTop($("#div_msg")[0].scrollHeight);
					}
				});
			}
			var displayUsername;
			//查询用户
			function queryUserInfo( obj ) {
				//alert( $(obj).children('input').val() );
				//向后台请求查询
				displayUsername = $(obj).children('input').val();
				var url = "../MainServlet?action=query";
				var args = { 
							"username":$(obj).children('input').val(),
							"time":new Date()/*时间戳*/
							};
				$.getJSON( url, args, function (data) {
					if ( data != null ){
						//alert(data.username +"," +data.petName + ","+data.sex +","+data.profile+","+data.email);
						$("#div_user_info").empty();
						$("#div_user_info").append("<p>用户信息<span class='glyphicon glyphicon-remove' style='float:right;cursor: pointer;' onclick=hideUserInfo();></span></p>");
						$("#div_user_info").append("<p>用户名：" + data.username + "</p>");
						$("#div_user_info").append("<p>昵&nbsp;&nbsp;称：" + data.petName + "</p>");
						$("#div_user_info").append("<p>性&nbsp;&nbsp;别：" + data.sex + "</p>");
						$("#div_user_info").append("<p>简&nbsp;&nbsp;介：" + data.profile + "</p>");
						$("#div_user_info").append("<p>邮&nbsp;&nbsp;箱：" + data.email + "</p>");
						showUserInfo();
					}
					
				});
			}
			function showUserInfo() {
				$("#div_user_info").show();
				$("#div_users").find("input[type='hidden']").parent().css("background-color","");
				if(displayUsername!=""){
					$("#div_users").find("input[type='hidden'][value='"+displayUsername+"']").parent().css("background-color","#ccc");
				}
			}
			function hideUserInfo() {
				$("#div_user_info").hide();
				$("#div_users").find("input[type='hidden']").parent().css("background-color","");
				displayUsername = "";
			}
			
			//处理 个人中心的导航功能
			$("#btn_personal_info").click(function () {
				$("#btn_personal_info").addClass("active");
				$("#btn_personal_pwd").removeClass("active");
				$("#div_personal_info").css("display","inline-block");
				$("#div_personal_pwd").css("display","none");
				destroyPopover();
			});
			$("#btn_personal_pwd").click(function () {
				$("#btn_personal_pwd").addClass("active");
				$("#btn_personal_info").removeClass("active");
				$("#div_personal_pwd").css("display","inline-block");
				$("#div_personal_info").css("display","none");
				destroyPopover();
			});
			//销毁所有 popover提示框
			function destroyPopover() {
				$("#petName").popover('destroy');
				$("#profile").popover('destroy');
				$("#email").popover('destroy');
				$("#password").popover('destroy');
				$("#newPassword").popover('destroy');
				$("#newPasswordAgain").popover('destroy');
			}
			
			//重置个人信息
			function reset() {
				$("#petName").val( "${user.petName}" );
				$("input[name=sex]").val( ["${user.sex}"] );
				$("#profile").val( "${user.profile}" );
				$("#email").val( "${user.email}" );
			}
			
			//检查昵称：为空时以用户名替代 并且在 10个字符以内
			function checkPetName() {
				var petName = $("#petName").val();
				if ( petName == null || petName.trim() == ""  ){
					$("#petName").val( "${user.username}" );
					$("#petName").popover({
						html:true,
						container:'body',
						content:"昵称为空时，以用户名替代"
					});
					$("#petName").popover('show');
					setTimeout(function() {
						$("#petName").popover('destroy');
					},2000);
					return true;
				} else {
					if( petName.length > 10 ){
						$("#petName").popover({
							html:true,
							container:'body',
							content:"昵称必须在 十个 字符以内！"
						});
						$("#petName").popover('show');
						return false;
					} else {
						$("#petName").popover('destroy');
						return true;
					}
				}
			}
			
			//检查简介： 50个字符以内
			function checkProfile() {
				var profile = $("#profile").val();
				if( profile != null && profile.length <= 50 ){
					$("#profile").popover('destroy');
					return true;
				} else {
					$("#profile").popover({
						html:true,
						container:'body',
						content:"简介必须在 五十 个字符以内！"
					});
					$("#profile").popover('show');
					return false;
				}
			}
			
			//检查邮箱：要么空 要么必须 为 xxx@xxx.xxx 这种格式且长度在 30个字符以内
			function checkEmail() {
				var email = $("#email").val();
				if( email== null || email == "" || email == "无" ){
					$("#email").val( "无" );
					return true;
				} else {
					if( !email.match("^\\w+@\\w+\\.\\w+$") ){
						$("#email").popover({
							html:true,
							container:'body',
							content:"邮箱格式：x@x.x 其中x只能是 字母、_或数字。"
						});
						$("#email").popover('show');
						return false;
					} else {
						if ( email.length > 30 ){
							$("#email").popover({
								html:true,
								container:'body',
								content:"邮箱长度只能在 三十 个字符以内"
							});
							$("#email").popover('show');
							return false;
						} else {
							$("#email").popover('destroy');
							return true;
						}
					}
				}
			}
			
			//修改个人信息
			function updateInfo(){
				//测试获取值
				/* alert( $("#petName").val() );
				alert( $(":radio:checked").val() );
				alert( $("#profile").val() );
				alert( $("#email").val() ); */
				var check = checkPetName();
				if( !check ){
					return ;
				}
				check = checkProfile();
				if( !check ){
					return ;
				}
				check = checkEmail();
				if( !check ){
					return ;
				}
				//能到这里说明检查通过可以修改 向后台请求修改
				var url = "../MainServlet?action=update";
				var agrs = {
							 "time":new Date(),
							 "petName":$("#petName").val(),
							 "sex":$(":radio:checked").val(),
							 "profile":$("#profile").val(),
							 "email":$("#email").val()
							};
				$.get( url, agrs, function(data){
					alert(data);
					location.reload();//刷新页面
				});
			}
			//检查密码（原密码、新密码）
			function checkCommon( obj ){
				var check = false;
				if(obj.val().length==0){
					obj.popover({
						html:true,
						container:'body',
						content:"密码不能为空"
					});
					obj.popover('show');
				}else if(obj.val().length>10 || obj.val().length<3){
					obj.popover({
						html:true,
						container:'body',
						content:"长度只能在3-10个字符之间"
					});
					obj.popover('show');
				}else if( !obj.val().match( "^\\w{3,10}$" ) ){
					obj.popover({
						html:true,
						container:'body',
						content:"密码只能由字母、_或数字组成！"
					});
					obj.popover('show');
				}else{
					obj.popover('destroy');
					check = true;
				}
				return check;
			}
			
			//检查 原密码 
			function checkPassword() {
				return checkCommon( $("#password") );
			}
			//检查 新密码
			function checkNewPassword(){
				return checkCommon( $("#newPassword") );
			}
			//检查 确认密码
			function checkNewPasswordAgain() {
				if ( $("#newPasswordAgain").val() != $("#newPassword").val() ){
					$("#newPasswordAgain").popover({
						html:true,
						container:'body',
						content:"确认密码与新密码不一致"
					});
					$("#newPasswordAgain").popover("show");
					return false;
				}
				$("#newPasswordAgain").popover("destroy");
				return true;
			}
			//修改密码
			function updatePwd() {
				var check = false;
				check = checkPassword();
				if( !check ){
					return;
				}
				check = checkNewPassword();
				if( !check ){
					return;
				}
				check = checkNewPasswordAgain();
				if( !check ){
					return;
				}
				//能到这里说明过了基本检查，向后台发送 修改密码 请求 
				var url = "../MainServlet?action=updatePwd";
				var agrs = {
							 "time":new Date(),
							 "password":$("#password").val(),
							 "newPassword":$("#newPassword").val(),
							 "newPasswordAgain":$("#newPasswordAgain").val()
							};
				$.get( url, agrs, function(data) {
					alert(data);
					$("#password").val("");
					$("#newPassword").val("");
					$("#newPasswordAgain").val("");
				});
				
			}
			
		</script>
	</body>
</html>
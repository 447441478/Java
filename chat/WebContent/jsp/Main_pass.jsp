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
		<link rel="stylesheet" type="text/css" href="../bootstrap-3.3.7-dist/css/bootstrap.min.css" >
		<style type="text/css">
			html,body {
				width: 100%;
				height: 100%;
				margin: 0px;
				font-family: 宋体;
				font-size: 16pt;
			}
			#div_container{
				min-height: 660px;
				border: 1px solid #000;
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
		
		<div id="div_container" class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<a id="exit" onclick="exit();"><span class="close">&times;</span></a>
					<a id="user">${user.username}</a>
				</div>
			</div>
			<div class="row" style="">
				<div class="col-md-5 col-sm-5 col-xs-5 text-right">
					<img width="100px" alt="logo" src="../imgs/logo.png"/>
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3">
					<h1 class="text-center text-info">鲸鱼聊天室</h1>
				</div>
			</div>
			<div class="row" style="height:360px;">
				<div class="col-md-8 col-sm-8 col-xs-8 text-info bg-info">
					<div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2">
						<div id="div_msg_out" style="width: 100%; height: 360px; overflow-y: hiddent; ">
							<div id="div_msg" style="width: 200%; height: 360px; padding-right:100%; overflow-y: scroll; " >
							</div>
						</div>
					</div>
					<div class="col-md-2 col-sm-2 col-xs-2" style="height:360px;">
					</div>
				</div>
				<div class="col-md-4 col-sm-4 col-xs-4" style="background-color: #C4E1FF; text-align: center; height: inherit;">
					<h3>在线用户:</h3>
					<div id="div_online_out" >
						<div id="div_online" >
							<div id="div_users" >
								<% 
									@SuppressWarnings("unchecked")
									ArrayList<String> onlineUsers = (ArrayList<String>)application.getAttribute("onlineUsers");
									if ( onlineUsers != null ){
										for ( int i = 0; i < onlineUsers.size(); i++ ) {
											out.println( onlineUsers.get( i )+"<br/>" );
										} 
									} else {
										response.sendRedirect("Login.jsp");
									}
								%>
							</div>
						</div>
					</div>
					<span id="onlineNumber" class="badge">在线人数:<%=onlineUsers==null?0:onlineUsers.size() %></span>
				</div>
			</div>
			<div class="row bg-info" >
				<div class="col-md-8 col-sm-8 col-xs-8" >
					<form action="" role="form">
						<div class = "form-group">
							<p class="text-center">请文明聊天，切勿刷屏。</p>
							<div class="col-md-10 col-sm-10 col-xs-10">
								<textarea id="message" rows="4" class="form-control" style="font-family: 宋体; font-size: 16pt;"></textarea>
							</div>
						</div>
					</form>
					<div class="col-md-2 col-sm-2 col-xs-2">
						<br/>
						<br/>
						<button id="btn_send" class="btn btn-success" onclick="send();">发送</button>
					</div>
				</div>
				<div class="col-md-12 col-sm-12 col-xs-12">
					<br/>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			var lastMessageId = 0;
			$(document).ready(function() {
				validate();
				window.setInterval("validate()",2000);
			});
			
			function send() {
				var url = "../MainServlet?action=send";
				var args = { 
							"message":$("#message").text(),
							"time":new Date() };
				$.getJSON(url,args,function(data) {
					$("#btn_send").popover({
						html:true,
						container:'body',
						content:data.res
					});
					$("#btn_send").popover('show');
					$("#message").text("");
					setTimeout(function () {
						$("#btn_send").popover("destroy");
					},2000);
				});
			}
			
			function exit() {
				window.location.href="../ExitServlet";
				alert("欢迎您下次光临！");
			}
			
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
							$("#div_users").append(data.onlineUsers[i]+"<br/>");
						}
					}
					//更新在线用户人数
					$("#onlineNumber").text("在线人数:"+data.onlineUsers.length);
					//更新消息内容
					if ( data.messages.length > 0 ){
						for ( var i = 0; i < data.messages.length; i++ ) {
							$("#div_msg").append("<p>" + data.messages[i].username + "&nbsp;("+ data.messages[i].date + ")说：</p>")
										 .append("<pre>" + data.messages[i].message + "</pre>");
							lastMessageId = data.messages[i].id;
						}
						$("#div_msg").scrollTop($("#div_msg")[0].scrollHeight);
					}
					
				});
				
			}
		</script>
	</body>
</html>
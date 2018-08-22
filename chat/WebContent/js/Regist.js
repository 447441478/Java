/**
 * 用户注册脚本
 * 访问后台 采用的是相对路径 更改当前文件路径是需注意
 */

$(function(){
	//聚焦input
	$('input').eq(0).focus(function(){
		if($(this).val().length==0){
			$(this).parent().next("div").css("color",'gray');
			$(this).parent().next("div").text("支持字母，'_'或数字的多种组合");
		}
	});
	$('input').eq(1).focus(function(){
		if($(this).val().length==0){
			$(this).parent().next("div").css("color",'gray');
		    $(this).parent().next("div").text("建议使用字母、数字和符号两种以上的组合，3-10个字符");
		}
	});
	$('input').eq(2).focus(function(){
		if($(this).val().length==0){
			$(this).parent().next("div").css("color",'gray');
			$(this).parent().next("div").text("请再次输入密码");
		}
	});
	//input各种判断
	//用户名：
	$('input').eq(0).blur(function(){
		checkName(this);
	});
	/**
	 * 校验用户名，并通过jQuery发送到后台查询是否存在
	 * @param obj 被校验的标签
	 * @returns true-该用户可用，false-该用户名不可用。
	 */
	function checkName(obj) {
		var check = false;
		if($(obj).val().length==0){
			$(obj).parent().next("div").text("用户名不能为空");
			$(obj).parent().next("div").css("color",'red');
		}else if($(obj).val().length>10 || $(obj).val().length<3){
			$(obj).parent().next("div").text("长度只能在3-10个字符之间");
			$(obj).parent().next("div").css("color",'red');
		}else if( !$(obj).val().match( "^\\w{3,10}$" ) ){
			$(obj).parent().next("div").text("用户名只能由字母、_或数字组成！");
			$(obj).parent().next("div").css("color",'red');
		}else{
			check = true;
			//向后台发送用户名，查看当前用户是否存在
			var url = "../RegistServlet"; 
			var data ={	
						action:"checkName",
						username:$(obj).val()
					   };
			$.post( url, data, function(str){
				//返回的数据是有带空格的 需要 调用jQuery函数 去掉前面空格，
				//不然  str == "ok" 永远不成立
				var str = $.trim( str );
				if( str == "ok" ){
					$(obj).parent().next("div").text("");
					check = true;
				} else {
					$(obj).parent().next("div").text("用户名已存在.");
					$(obj).parent().next("div").css("color",'red');
					check = false;
				}
			});
			return check;
		}
		return check;
	};
	//密码
	$('input').eq(1).blur(function(){
		checkPwd(this);	
	});
	/**
	 * 校验 登入密码
	 * @param obj 被校验的标签
	 * @returns true-密码可用，false-密码不可用。
	 */
	function checkPwd(obj) {
		var check = false;
		if($(obj).val().length==0){
			$(obj).parent().next("div").text("密码不能为空");
			$(obj).parent().next("div").css("color",'red');
		}else if($(obj).val().length>10 || $(obj).val().length<3){
			$(obj).parent().next("div").text("长度只能在3-10个字符之间");
			$(obj).parent().next("div").css("color",'red');
		}else if( !$(obj).val().match( "^\\w{3,10}$" ) ){
			$(obj).parent().next("div").text("密码只能由字母、_或数字组成！");
			$(obj).parent().next("div").css("color",'red');
		}else{
			$(obj).parent().next("div").text("");
			check = true;
		}
		return check;
	}
	//确认密码
	$('input').eq(2).blur(function(){
		checkAgainPwd(this);
	});
	/**
	 * 校验 确认密码
	 * @param obj 被校验的标签
	 * @returns true-两次密码一致，false-两次密码不一致。
	 */
	function checkAgainPwd(obj) {
		var check = false;
		if($(obj).val().length==0){
			$(obj).parent().next("div").text("确认密码不能为空");
			$(obj).parent().next("div").css("color",'red');
		}else if($(obj).val()!=$('input').eq(1).val()){
			$(obj).parent().next("div").text("两次密码不匹配");
			$(obj).parent().next("div").css("color",'red');
		}else{
			$(obj).parent().next("div").text("");
			check = true;
		}
		return check;
	}
	
	//提交按钮
	$("#submit_btn").click(function(e){	
		var check = false;
		check = checkName($('input').eq(0));
		if ( !check ) {
			$('input').eq(0).focus();
			return ;
		}
		check = checkPwd($('input').eq(1));
		if ( !check ) {
			$('input').eq(1).focus();
			return ;
		}
		check = checkAgainPwd($('input').eq(2));
		if ( !check ) {
			$('input').eq(2).focus();
			return ;
		}
		//能到这里说明符合要求向后台发送请求
		//向后台请求注册
		var url = "../RegistServlet";
		//提交数据 json 格式
		var data = { 
					 action:"regist",
					 username:$('input').eq(0).val(),
					 password:$('input').eq(1).val() 
					};
		$.post( url, data, function( str ) {
			str = $.trim( str );
			if( str == "ok" ){
				alert( "注册成功！" );
				window.location.href="../index.jsp";
			} else {
				$('input').eq(0).focus();
			}
		});
		
	});
});

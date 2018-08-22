/**
 * 用户登入脚本
 * 访问后台 采用的是相对路径 更改当前文件路径是需注意
 */
function checkCommon( obj, str){
	var check = false;
	//获取标签的value值
	value = obj.value;
	//如果 value 为空 则弹框 并且 提示 ×
	if ( value=="" ) {
		$(obj).popover({ html:true,
						 content: str + "不能为空！"
					   });
		$(obj).popover("show");
		return check;
	}
	//如果长度 不在 3~10个字符之间 则弹框 并且 提示×
	if ( value.length<3 || value.length>10 ) {
		$(obj).popover({ html:true,
			 			 content:str + "长度必须在3~10个字符之间！"
		   			   });
		$(obj).popover("show");
		return check;
	}
	//如果不是由字母、_或数字组成 则弹框 并且 提示×
	if ( !value.match( "^\\w{3,10}$" ) ){
		$(obj).popover({ html:true,
			 			 content:str + "只能由字母、_或数字组成！"
				 	  });
		$(obj).popover("show");
		return check;
	}
	$(obj).popover("destroy");
	//能到这里说明 过了基本检查
	return !check;
}

/**
 * 重置表单数据
 * @returns
 */
function formReset() {
	$("#userName").val("");
	$("#password1").val("");
}
/**
 * 提交表单数据
 * @returns
 */
function formSubmit() {
	var isOk = false;
	isOk = checkCommon( document.getElementById( "userName" ), "用户名");
	if ( !isOk ) {
		document.getElementById( "userName" ).focus();
		return isOk;
	}
	isOk = checkCommon( document.getElementById( "password1" ),"密码" );
	if( !isOk ){
		document.getElementById( "password1" ).focus();
		return isOk;
	}
	if ( isOk ){//如果符合就进行提交
		//向后台请求注册
		var url = "../LoginServlet";
		//提交数据 json 格式
		var data = { action:"login",
					 username:$("#userName").val(),
					 password:$("#password1").val() };
		$.post( url, data, function( str ) {
			str = $.trim( str );
			if( str == "ok" ){
				alert( "登入成功！" );
				window.location.href="Main.jsp";
			} else {
				alert( str );
				document.getElementById( "userName" ).focus();
			}
		});
	}
}

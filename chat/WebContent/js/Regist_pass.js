/**
 * 用户注册脚本
 * 访问后台 采用的是相对路径 更改当前文件路径是需注意
 */

/**
 * 用户名和密码都需要的最基本校验
 * @param obj 被校验的标签对象
 * @param str 提示是用户名还是密码出错了
 * @param tip 显示提示信息的标签的id如："#userNameTip"
 * @returns
 */
function checkCommon( obj, str, tip ){
	var check = false;
	//获取标签的value值
	value = obj.value;
	//如果 value 为空 则弹框 并且 提示 ×
	if ( value == null || value=="" ) {
		//alert( str + "不能为空！" );  //火狐浏览器有bug
		$(tip).html("×");
		return check;
	}
	//如果长度 不在 3~10个字符之间 则弹框 并且 提示×
	if ( value.length<3 || value.length>10 ) {
		alert( str + "长度必须在3~10个字符之间！" );
		$(tip).html("×");
		return check;
	}
	//如果不是由字母、_或数字组成 则弹框 并且 提示×
	if ( !value.match( "^\\w{3,10}$" ) ){
		alert( str + "只能由字母、_或数字组成！" );
		$(tip).html("×");
		return check;
	}
	//能到这里说明 过了基本检查
	return !check;
}
/**
 * 校验用户名，并通过jQuery发送到后台查询是否存在
 * @param obj 被校验的标签
 * @returns 
 */
function checkName(obj) {
	var check = checkCommon( obj, "用户名", "#userNameTip" );
	if ( check ){//能到这里 说明是过了基本的校验
		//向后台发送用户名，查看当前用户是否存在
		var url = "../RegistServlet"; 
		var data ={	action:"checkName",
					username:obj.value};
		
		$.post( url, data, function(str){
			//返回的数据是有带空格的 需要 调用jQuery函数 去掉前面空格，
			//不然  str == "ok" 永远不成立
			var str = $.trim( str );
			if( str == "ok" ){
				$("#userNameTip").html("√");
			} else {
				alert( str );
				$("#userNameTip").html("×");
				check = false;
			}
		});
	} 
	return check;
}
/**
 * 校验 登入密码
 * @param obj 被校验的标签
 * @returns
 */
function checkPwd1(obj) {
	var isOk = checkCommon( obj, "登入密码", "#password1Tip" );
	if ( isOk ) {
		$("#password1Tip").html("√");
		return isOk;
	}
	return !isOk;
}
/**
 * 校验 确认密码
 * @param obj 被校验的标签
 * @returns
 */
function checkPwd2(obj) {
	//先判断 登入密码 是否合法，如果不合法 应先修改 登入密码
	var isOk = checkCommon( document.getElementById( "password1" ), "登入密码", "#password1Tip" );
	if ( !isOk ) {
		return isOk;
	}
	//能到这里说明登入密码是合法的
	isOk = checkCommon( obj, "确认密码", "#password2Tip" );
	if ( !isOk ) {
		return isOk;
	}
	//能到这里说明 确认密码也是合法的
	//接下进行两个字符串比较
	if ( obj.value == $("#password1").val() ) {
		$("#password2Tip").html("√");
		return isOk;
	} else {
		alert("两次密码不一致！");
		$("#password2Tip").html("×");
		return !isOk;
	}
}
/**
 * 重置表单数据
 * @returns
 */
function formReset() {
	$("#userName").val("");
	$("#password1").val("");
	$("#password2").val("");
	$("#userNameTip").html("*&nbsp;");
	$("#password1Tip").html("*&nbsp;");
	$("#password2Tip").html("*&nbsp;");
}

/**
 * 提交表单数据
 * @returns
 */
function formSubmit() {
	var isOk = false;
	isOk = checkName( document.getElementById( "userName" ) ) &&
				checkPwd2( document.getElementById( "password2" ) ) ;
	if ( isOk ){//如果符合就进行提交
		//向后台请求注册
		var url = "../RegistServlet";
		//提交数据 json 格式
		var data = { action:"regist",
					 username:$("#userName").val(),
					 password:$("#password1").val() };
		$.post( url, data, function( str ) {
			str = $.trim( str );
			if( str == "ok" ){
				alert( "注册成功！" );
				window.location.href="../index.jsp";
			} else {
				/*alert( "服务器繁忙，请稍等..." );*/
			}
		});
	}
}
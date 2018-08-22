package net.hncu.chat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.hncu.chat.user.business.ebi.UserEbi;
import net.hncu.chat.user.business.factory.UserEbiFactory;
import net.hncu.chat.user.javabean.UserModel;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")//采用注解配置servlet
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//注入UserEbi
	private UserEbi userEbi = UserEbiFactory.getUserEbi();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html" ); //设置返回数据类型
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter( "action" );
		System.out.println(action);
		//如果是非法的动作指令 直接return;
		if( action == null || "".equals( action.trim() ) ) {
			out.println( "警告：不法行为已标记" );
			out.flush();
			return;
		}
		String username = request.getParameter( "username" );
		//能到这里说明指令是正确的
		if ( "checkName".equals( action ) ) {
			checkName( username, out );
		}
		String password = request.getParameter( "password" );
		if ( "regist".equals( action ) ) {
			//这里就简单判断一下
			boolean isOk = checkCommon( username ) && checkCommon( password ) ;
			if ( isOk ) {
				UserModel user = new UserModel();
				user.setUsername( username );
				user.setPassword( password );
				boolean boo = userEbi.regist( user );
				if (boo) {
					out.println( "ok" );
				}
				out.flush();
			}
		}
		
	}
	/**
	 * 处理checkName指令
	 * @param username 被查询的用户名
	 * @param out 打印流对象
	 */
	private void checkName(String username, PrintWriter out) {
		
		//继续对 username 进行校验 防黑，如果有人黑应该做个记号，超过规定次数就限制该ip注册
		//这里就简单的处理下
		boolean isOk = checkCommon( username );
		if ( !isOk ) {//如果数据不合法 就 不进行处理
			//做好点 这里 应该 对 相应的 ip 进行标记 。
			out.println( "警告：不法行为已标记" );
			out.flush();
			return;
		}
		//调用逻辑层 
		UserModel user = userEbi.FindUserByUserName( username );
		if ( user == null ) { //如果 返回为 null 说明没有该用户
			out.println( "ok" );
		} else { //能进来 说明 该用户已存在
			out.println( "该用户名已存在！" );
		}
		//刷缓存
		out.flush();
	}
	
	/**
	 * 对  data 进行 校验
	 * @param data 被校验的数据
	 * @return true-合法，false-非法
	 */
	private boolean checkCommon( String data ) {
		//校验 是否为空
		if( data == null || "".equals( data.trim() ) ) {
			return false;
		}
		//校验 是否在 3~10个字符之间
		if( data.length()<3 || data.length()>10 ) {
			return false;
		}
		//校验 是否符合格式要求
		if ( !data.matches( "^\\w{3,10}$" ) ) {
			return false;
		}
		//能到这里说明 数据 合法
		return true;
	}
}

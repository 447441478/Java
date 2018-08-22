package net.hncu.chat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.hncu.chat.user.business.ebi.UserEbi;
import net.hncu.chat.user.business.factory.UserEbiFactory;
import net.hncu.chat.user.javabean.UserModel;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
		
		//如果是非法的动作指令 直接return;
		if( action == null || "".equals( action.trim() ) ) {
			out.println( "警告：不法行为已标记" );
			out.flush();
			return;
		}
		String username = request.getParameter( "username" );
		String password = request.getParameter( "password" );
		if ( "login".equals( action ) ) {
			//简单校验 username 和  password
			boolean isOk = checkCommon( username ) && checkCommon( password );
			if ( isOk ) {
				//登入 就相当于查询 
				UserModel user = userEbi.FindUserByUserName( username );
				if ( user == null ) {
					out.println( "用户名不正确,请从新输入。" );
				} else if ( user.getPassword().equals( password ) ) { //如果密码一致，则说明登入成功
					//则在 session 中 添加 user 属性
					request.getSession().setAttribute("user", user);
					ServletContext servletContext = request.getServletContext();
					@SuppressWarnings("unchecked")
					ArrayList<UserModel> onlineUsers = (ArrayList<UserModel>)servletContext.getAttribute("onlineUsers");
					if ( onlineUsers == null ) {
						onlineUsers = new ArrayList<UserModel>();
					}
					//需要注意 要UserModel中重写 equals方法
					if ( onlineUsers.contains( user ) ) { 
						out.println( "该用户已登入，请检查用户名。" );
					} else {
						onlineUsers.add( user );
						servletContext.setAttribute("onlineUsers", onlineUsers);
						out.println( "ok" );
					}
					
				} else { //进入到这里 说明 密码不正确
					out.println( "登入密码不正确,请从新输入。" );
				}
				out.flush();
			}
		}
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

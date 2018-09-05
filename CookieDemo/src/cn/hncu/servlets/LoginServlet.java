package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Cookie[] cookies = request.getCookies();
		boolean isFirst = true;
		if( cookies != null ) {
			for (Cookie cookie : cookies) {
				if ( "loginTime".equals( cookie.getName() ) ) {
					isFirst = false;
					String val = cookie.getValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String format = sdf.format( new Date( Long.valueOf( val ) ) );
					out.println("上一次访问时间："+format);
				}
			}
		}
		if ( isFirst ) {
			out.println("第一次访问该网页");
		}
		long time = new Date().getTime();
		Cookie cookie = new Cookie("loginTime", ""+time);
		//最经常就是设置cookie的path为当前项目根路径。
		cookie.setPath( request.getContextPath() );
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

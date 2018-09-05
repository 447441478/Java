package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieDemoServlet")
public class CookieDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Random random = new Random(System.currentTimeMillis());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//遍历cookie
		Cookie[] cookies = request.getCookies();
		if ( cookies != null ) {
			for (Cookie cookie : cookies) {
				out.println(cookie.getName()+"="+cookie.getValue()+"<br/>");
			}
		}
		// 添加/修改 cookie 类似map，key值已存在的话就用新的value代替旧的value
		Cookie cookie = new Cookie("name", "java"+random.nextInt(10));
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

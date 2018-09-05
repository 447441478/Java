package cn.hncu.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieDelServlet")
public class CookieDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Cookie保存的时间通过设置setMaxAge来设置 (默认值为-1)
			    如果大于0,就表示在客户机的硬盘上保存N秒。
			    如果小于0,就表示不将Cookie保存到客户机的硬盘上，当浏览器关闭时，Cookie当即消失。
			    如果等于0，就表示删除保存在客户机上的Cookie。
		 */
		//setMaxAge函数，默认值为-1。
		//利用setMaxAge(0)来删除一个name为"name"的cookie
		Cookie cookie = new Cookie("name", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

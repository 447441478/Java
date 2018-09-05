package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/CookieDemoServlet2")
public class CookieDemoServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Random random = new Random(System.currentTimeMillis());
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//遍历cookie
		Cookie[] cookies = request.getCookies();
		if ( cookies != null ) {
			for (Cookie cookie : cookies) {
				out.println(cookie.getName()+"="+URLDecoder.decode(cookie.getValue(), "utf-8")+"<br/>");
			}
		}
		/* 通过URLEncoder.encode("岁", "utf-8") 
		 * 和 URLDecoder.decode(cookie.getValue(), "utf-8")
		 * 解决中文乱码问题。
		 */
		
		// 添加/修改 cookie 类似map，key值已存在的话就用新的value代替旧的value
		Cookie cookie = new Cookie("age", random.nextInt(100)+URLEncoder.encode("岁", "utf-8"));
		response.addCookie(cookie);
		/* 观察第一个超链接和第三个超连接可以发现：
		 * /servlet/CookieDemoServlet2中可以获取到/CookieDemoServlet添加的cookie
		 * 而/CookieDemoServlet中却获取不到/servlet/CookieDemoServlet2中添加的cookie
		 * 原因：
		 * 		每个cookie默认设置Path为当前servlet所在目录，
		 * 	cookie获取时会根据cookie所设置的path和当前servlet进行比较，
		 *  如果当前servlet的目录包涵了cookie的Path,则当前servlet就可以获取到cookie。
		 *  否则就无法获取。
		 *  
		 *  可以通过setPath方法设置一个cookie是哪个目录下的servlet能够获取的。
		 *  
		 */
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

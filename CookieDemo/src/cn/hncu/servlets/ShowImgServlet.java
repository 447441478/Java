package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowImgServlet")
public class ShowImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String img = request.getParameter("img");
		Cookie[] cookies = request.getCookies();
		String imgs = img;
		if ( cookies != null ) {
			for (Cookie cookie : cookies) {
				if ( "imgs".equals( cookie.getName() ) ) {
					imgs = cookie.getValue();
					break;
				}
			}
		}
		String[] split = imgs.split("!");
		int len = split.length<3?split.length:3;
		imgs = img;
		int count = 1;
		for ( int i = 0; i < len; i++ ) {
			if ( split[i].equals( img ) ) {
				continue;
			}
			if( count == 3 )break;
			imgs += "!"+split[i];
			count++;
		}
		Cookie cookie = new Cookie("imgs", imgs);
		cookie.setPath( request.getContextPath() );
		response.addCookie(cookie);
		out.println("<img src='"+request.getContextPath()+"/imgs/"+img+"' /><br/><a href='"+request.getContextPath()+"/jsps/show.jsp'>返回</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package net.hncu.chat.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.hncu.chat.user.javabean.UserModel;

/**
 * Servlet implementation class ExitServlet
 */
@WebServlet("/ExitServlet")
public class ExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		if ( user != null ) {
			@SuppressWarnings("unchecked")
			ArrayList<UserModel> onlineUsers = (ArrayList<UserModel>)request.getServletContext().getAttribute("onlineUsers");
			onlineUsers.remove( user );
			request.getSession().setAttribute("user", null);
		}
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

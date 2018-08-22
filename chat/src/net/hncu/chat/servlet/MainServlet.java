package net.hncu.chat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.hncu.chat.message.business.ebi.MessageEbi;
import net.hncu.chat.message.business.factory.MessageEbiFactory;
import net.hncu.chat.message.javabean.MessageModel;
import net.hncu.chat.user.business.ebi.UserEbi;
import net.hncu.chat.user.business.factory.UserEbiFactory;
import net.hncu.chat.user.javabean.UserModel;
import net.hncu.chat.utils.DealMessage;
import net.hncu.chat.utils.MyDateTool;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //注入MessageEbi
	private MessageEbi messageEbi = MessageEbiFactory.getMessageEbi();
	
	//注入UserEbi
	private UserEbi userEbi = UserEbiFactory.getUserEbi();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if( "validate".equals( action ) ) {
			validate( request, response );
		}
		if ( "send".equals( action ) ) {
			send( request, response );
		}
		if( "query".equals( action ) ) {
			query( request, response );
		}
		if( "update".equals( action ) ) {
			update( request, response );
		}
		if( "updatePwd" .equals( action ) ) {
			updatePwd( request, response );
		}
	}
	/**
	 * 响应修改密码请求
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		response.setContentType( "tetx/html" );
		PrintWriter out = response.getWriter();
		UserModel user = (UserModel) session.getAttribute( "user" );
		if ( user == null ) {
			return;
		}
		String password = request.getParameter("password");
		if ( !user.getPassword().equals( password ) ) {
			out.println( "原密码不正确，请从新输入。" );
			out.flush();
			return;
		}
		String newPassword = request.getParameter("newPassword");
		String newPasswordAgain = request.getParameter("newPasswordAgain");
		//只要新密码 为null 或者新密码 不等null时但两次密码不一样时 就 返回
		if ( newPassword == null || !newPassword.equals( newPasswordAgain ) ) {
			out.println("新密码与确认密码不一致");
			out.flush();
			return;
		}
		//能到这里说明 是符合要求的
		//组织参数
		user.setPassword( newPassword );
		//调用逻辑层
		boolean boo = userEbi.updateUserInfo( user );
		//导向结果页面
		if ( boo ) {
			out.println("修改成功，请牢记新密码。");
		} else {
			//还原 密码
			user.setPassword( password );
			//输出结果
			out.println("服务繁忙，请稍后再试...");
		}
		out.flush();
	}

	/**
	 * 响应更新用户信息请求
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		UserModel currentUser = (UserModel) session.getAttribute("user");
		String petName = request.getParameter("petName");
		if( petName == null || "".equals(petName.trim()) ) {
			petName = currentUser.getUsername();
		} else if( petName.length() > 10 ) {
			out.println("信息异常！");
			out.flush();
			return;
		}
		String sex = request.getParameter("sex");
		byte byteSex = 0;
		try {
			byteSex = Byte.parseByte( sex );
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if( byteSex < 0 || byteSex > 3 ) {
			out.println("信息异常！");
			out.flush();
			return;
		}
		String profile = request.getParameter("profile");
		if ( profile != null && profile.length() > 50 ) {
			out.println("信息异常！");
			out.flush();
			return;
		}
		String email = request.getParameter("email");
		if( email== null || "".equals( email ) || "无".equals( email ) ){
			email = "无";
		} else {
			if( !email.matches("^\\w+@\\w+\\.\\w+$") || email.length() > 30  ){
				out.println("信息异常！");
				out.flush();
				return;
			}
		}
		
		//组织参数
		UserModel newUser = new UserModel();
		newUser.setUsername( currentUser.getUsername() );
		newUser.setPassword( currentUser.getPassword() );
		newUser.setPetName( petName );
		newUser.setSex( byteSex );
		newUser.setProfile( profile );
		newUser.setEmail( email );
		newUser.setPower( 0 );
		//调用逻辑层
		boolean boo = userEbi.updateUserInfo( newUser );
		//导向结果页面
		if( boo ) {
			//因为 currentUser 不仅存储在 session 中 也存储 在list 中
			//所以 修改 引用 应该是比较慢的，直接 set 快些
			currentUser.setPetName( petName );
			currentUser.setSex( byteSex );
			currentUser.setProfile( profile );
			currentUser.setEmail( email );
			
			out.println("修改成功，如果没有及时更新请刷新一下页面。");
		} else {
			out.println("数据库繁忙，请稍后再试。");
		}
		out.flush();
		
	}

	/**
	 * 响应用户查询请求
	 * @param request
	 * @param response
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript");
		String username = request.getParameter("username");
		if( username != null ) {
			UserModel resUser = userEbi.FindUserByUserName( username );
			// 返回结果
			String res = null;
			if (  resUser != null ) {
				String[] strSex = {"保密","男","女"};
				res = "{"+
						 "\"username\":\"" + resUser.getUsername()+
						 "\",\"petName\":\"" + resUser.getPetName()+
						 "\",\"sex\":\"" + strSex[ resUser.getSex() ]+
						 "\",\"profile\":\"" + resUser.getProfile()+
						 "\",\"email\":\"" + resUser.getEmail()+
					  "\"}";
			}
			response.getWriter().println( res );
			response.getWriter().flush();
		}
		
	}

	/**
	 * 接收用户发送过来的数据，并更新数据库
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void send(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserModel user = (UserModel)request.getSession().getAttribute("user");
		if ( user != null ) {
			// 收集参数(username,content)
			String message = request.getParameter("message");
			boolean boo = false;
			//消息不为 null 并且长度要大于0 才进行数据存储
			if( message != null && message.length() > 0 ) {
				//调用工具类处理 消息中的特殊字符
				message = DealMessage.change( message );
				
				String username = user.getUsername();
				// 组织参数
				MessageModel msg = new MessageModel();
				msg.setUsername( username );
				msg.setContent( message );
				System.out.println(msg.getUsername() + "," + msg.getContent() );
				// 调用 业务
				boo = messageEbi.addMessage(msg);
			}
			// 返回结果
			String res = null;
			if ( boo ) {
				res = "{"+
						 "\"res\":\"发送成功。\","+
						 "\"nocache\":\"" + new Date() + "\"" +
					   "}";
			} else {
				res = "{"+
						 "\"res\":\"发送失败。\","+
						 "\"nocache\":\"" + new Date() + "\"" +
					   "}";
			}
			response.setContentType("text/javascript");
			response.getWriter().println( res );
			response.getWriter().flush();
		}
		
	}

	/**
	 * 响应用户请求更新页面信息
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws ServletException
	 * @throws IOException
	 */
	private void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = request.getServletContext();
		@SuppressWarnings("unchecked")
		ArrayList<UserModel> onlineUsers = (ArrayList<UserModel>)servletContext.getAttribute("onlineUsers");
		if ( onlineUsers != null ) {
			StringBuilder jsonStr = new StringBuilder();
			////////////组织在线用户信息////////////
			jsonStr.append("{")
				   .append("\"onlineUsers\":[");
			for (int i = 0; i < onlineUsers.size(); i++) {
				jsonStr.append("{")
				   	   .append( "\"username\":\"" + onlineUsers.get(i).getUsername() )
				       .append( "\",\"petName\":\"" + onlineUsers.get(i).getPetName() );
				if ( onlineUsers.get(i).getSex() == null ) {
					jsonStr.append( "\",\"sex\":\"" + "保密" );
				} else {
					if( onlineUsers.get(i).getSex() == 0 ) {
						jsonStr.append( "\",\"sex\":\"" + "女" );
					}else {
						jsonStr.append( "\",\"sex\":\"" + "男" );
					}
				}
				if ( onlineUsers.get(i) == null) {
					jsonStr.append( "\",\"profile\":\"" + "无" );
				} else {
					jsonStr.append( "\",\"profile\":\"" + onlineUsers.get(i).getProfile() );
				}
				if ( onlineUsers.get(i) == null) {
					jsonStr.append( "\",\"email\":\"" + "无" );
				} else {
					jsonStr.append( "\",\"email\":\"" + onlineUsers.get(i).getEmail() );
				}
				jsonStr.append( "\"}" );
				if( i != onlineUsers.size()-1 ) {
					jsonStr.append(",");
				}
			}
			jsonStr.append("]");
			
			/////////组织聊天内容////////
			//1先获取当前用户 最后一条消息的 id号
			String lastMassageId = request.getParameter("lastMassageId");
			int id = Integer.MAX_VALUE;
			try {
				id = Integer.parseInt( lastMassageId );
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			//2调用逻辑层
			ArrayList<MessageModel> messages = messageEbi.getByIdAfter(id);
			jsonStr.append(",\"messages\":[");
			for( int i = 0; i < messages.size(); i++ ) {
				jsonStr.append( "{" )
					   .append( "\"id\":\"" + messages.get(i).getId() )
					   .append( "\",\"username\":\"" + messages.get(i).getUsername() )
					   .append( "\",\"petName\":\"" + messages.get(i).getPetName() )
					   .append( "\",\"date\":\"" + MyDateTool.toLocalStrint( messages.get(i).getDate() ) )
					   .append( "\",\"message\":\"" + messages.get(i).getContent() )
					   .append( "\"}" );
				if ( i != messages.size()-1 ) {
					jsonStr.append(",");
				}
			}
			jsonStr.append("]");
			jsonStr.append("}");   
			response.setContentType("text/javascript");
			response.getWriter().println(jsonStr.toString());
			response.getWriter().flush();
		}
	}

}

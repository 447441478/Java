package net.hncu.chat.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CreateTime: 2018年5月27日 上午10:27:55	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	连接数据库的工具
 */
public class DBUtil {
	private static String driverName = "com.mysql.jdbc.Driver"; //驱动类全名
	private static String url = "jdbc:mysql://127.0.0.1:3306/db_chat"; //连接具体的数据库的资源定位
	private static String username = "root"; //数据库管理员用户名
	private static String password = "1234"; //数据库管理员密码
	//工具类 私有化构造函数 
	private DBUtil() {}
	/**
	 * 获得访问数据的一个链接
	 * @return null--没有获取到，否则就是获取到一个链接
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		//加载驱动类
		Class.forName( driverName );
		//通过驱动管理获得连接
		Connection connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}
}

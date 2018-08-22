package net.hncu.testchat.testUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.hncu.chat.utils.DBUtil;

public class TestDBUtil {
	
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null; 
		try {
			//获得连接
			connection = DBUtil.getConnection();
			//获取状态集
			statement = connection.createStatement();
			//执行查询语句
			resultSet = statement.executeQuery( "SELECT * FROM tb_user" );
			//打印结果
			while ( resultSet.next() ) {
				System.out.println( resultSet.getString( 1 ) + "," + resultSet.getString( 2 ) );
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//关闭resultSet
				if ( resultSet != null ) {
					resultSet.close();
				}
				//关闭statement
				if ( statement != null ) {
					statement.close();
				}
				//关闭connection
				if ( connection != null ) {
					connection.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

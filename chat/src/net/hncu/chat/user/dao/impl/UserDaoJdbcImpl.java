package net.hncu.chat.user.dao.impl;
/**
 * CreateTime: 2018年5月27日 上午10:57:53	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	UserDAO实现类，通过JDBC实现
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import net.hncu.chat.user.dao.dao.UserDAO;
import net.hncu.chat.user.javabean.UserModel;
import net.hncu.chat.utils.DBUtil;
/**
 * CreateTime: 2018年5月27日 上午11:01:54	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	UserDAO接口的实现类 UserDaoJdbcImpl
 */
public class UserDaoJdbcImpl implements UserDAO {
	private Connection con = null;
	private PreparedStatement ps = null;
	public UserDaoJdbcImpl(){
	}
	
	@Override
	public boolean creat(UserModel user) throws RuntimeException {
		//预防空指针
		if ( user == null ) {
			return false;
		}
		//先查询，如果有该用户就不能添加
		UserModel u = getUserByUserName( user.getUsername() );
		if ( u != null) {
			return false;
		}
		//能到这里说明该用户不存在， 进行数据插入
		
		//书写 sql 语句
		String sql = "INSERT INTO tb_user(username,password,petName) VALUES(?,?,?)";
		int i = 0;
		try {
			//获取链接
			con = DBUtil.getConnection();
			//获取预编译状态集
			ps = con.prepareStatement(sql);
			//补全sql语句
			ps.setString( 1, user.getUsername() );
			ps.setString( 2, user.getPassword() );
			ps.setString( 3, user.getPetName() );
			i = ps.executeUpdate();
			return i > 0;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException( "数据库链接失败", e);
		} catch (SQLException e) {
			throw new RuntimeException( "数据库繁忙", e );
		} finally {
			try {
				if ( ps != null ) {
					ps.close();
				}
				if ( con != null ) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException( "数据出现异常，请联系数据库管理员。",e );
			}
		}
	}
	@Override
	public boolean delete(String username) {
		//预防空指针
		if ( username == null ) {
			return false;
		}
		//书写sql语句
		String sql = "DELETE FROM tb_user WHERE username = ?";
		int i = 0;
		try {
			//获取 连接
			con = DBUtil.getConnection();
			//获取预编译状态集
			ps = con.prepareStatement( sql );
			//补全sql语句
			ps.setString( 1, username );
			i = ps.executeUpdate();
			return i > 0;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException( "数据库连接失败！", e);
		} catch (SQLException e) {
			throw new RuntimeException( "数据库繁忙...", e );
		} finally {
			try {
				if ( ps != null ) {
					ps.close();
				}
				if ( con != null ) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException( "数据出现异常，请联系数据库管理员。",e );
			}
		}
	}
	@Override
	public boolean update(UserModel user) {
		//预防空指针
		if ( user == null ) {
			return false;
		}
		//先查询，如果没有有该用户就不能修改
		UserModel u = getUserByUserName( user.getUsername() );
		if ( u == null) {
			return false;
		}
		//能到这里说明该用户存在， 进行数据修改
		
		//书写 sql 语句
		String sql = "UPDATE tb_user SET password=?, petName=?, sex=?, profile=?, Email=?, power=? WHERE username = ?";
		int i = 0;
		try {
			//获取链接
			con = DBUtil.getConnection();
			//获取预编译状态集
			ps = con.prepareStatement(sql);
			//补全sql语句
			ps.setString( 1, user.getPassword() );
			ps.setString( 2, user.getPetName() );
			ps.setByte( 3, user.getSex() );
			ps.setString( 4, user.getProfile() );
			ps.setString( 5, user.getEmail() );
			ps.setInt( 6, user.getPower() );
			ps.setString( 7, user.getUsername() );
			i = ps.executeUpdate();
			return i > 0;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException( "数据库链接失败", e);
		} catch (SQLException e) {
			throw new RuntimeException( "数据库繁忙", e );
		} finally {
			try {
				if ( ps != null ) {
					ps.close();
				}
				if ( con != null ) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException( "数据出现异常，请联系数据库管理员。",e );
			}
		}
	}
	@Override
	public UserModel getUserByUserName(String username) {
		//预防空指针
		if ( username == null ) {
			return null;
		}
		//书写sql语句
		String sql = "SELECT * FROM tb_user WHERE username = ?";
		ResultSet resultSet = null;
		try {
			//获取链接
			con = DBUtil.getConnection();
			//获取预编译状态集
			PreparedStatement ps = con.prepareStatement( sql );
			//补全sql语句
			ps.setString( 1, username );
			//执行
			resultSet = ps.executeQuery();
			//如果结果集有数据 则为true
			if ( resultSet.next() ) {
				UserModel user = new UserModel();
				user.setUsername( resultSet.getString( 1 ) );
				user.setPassword( resultSet.getString( 2 ) );
				user.setPetName( resultSet.getString( 3 ) );
				user.setSex( resultSet.getByte( 4 ) );
				user.setProfile( resultSet.getString( 5 ) );
				user.setEmail( resultSet.getString( 6 ) );
				user.setPower( resultSet.getInt( 7 ) );
				return user;
			}
			return null;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException( "数据库连接失败！", e);
		} catch (SQLException e) {
			throw new RuntimeException( "数据库繁忙...", e );
		} finally {
			try {
				if ( resultSet != null ) {
					resultSet.close();
				}
				if ( ps != null ) {
					ps.close();
				}
				if ( con != null ) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException( "数据出现异常，请联系数据库管理员。",e );
			}
		}
	}
	@Override
	public Collection<UserModel> getAll() {
		Collection<UserModel> col = new ArrayList<UserModel>();
		//书写sql语句
		String sql = "SELECT * FROM tb_user";
		ResultSet resultSet = null;
		try {
			//获取链接
			con = DBUtil.getConnection();
			//获取预编译状态集
			PreparedStatement ps = con.prepareStatement( sql );
			//执行
			resultSet = ps.executeQuery();
			//如果结果集有数据 则为true
			while ( resultSet.next() ) {
				UserModel user = new UserModel();
				//封装user
				user.setUsername( resultSet.getString(1) );
				user.setPassword( resultSet.getString(2) );
				user.setPetName( resultSet.getString( 3 ) );
				user.setSex( resultSet.getByte( 4 ) );
				user.setProfile( resultSet.getString( 5 ) );
				user.setEmail( resultSet.getString( 6 ) );
				user.setPower( resultSet.getInt( 7 ) );
				//把用户添加到集合中
				col.add( user );
			}
			return col;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException( "数据库连接失败！", e);
		} catch (SQLException e) {
			throw new RuntimeException( "数据库繁忙...", e );
		} finally {
			try {
				if ( resultSet != null ) {
					resultSet.close();
				}
				if ( ps != null ) {
					ps.close();
				}
				if ( con != null ) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException( "数据出现异常，请联系数据库管理员。",e );
			}
		}
	}
	
}

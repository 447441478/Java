package net.hncu.chat.message.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import net.hncu.chat.message.dao.dao.MessageDAO;
import net.hncu.chat.message.javabean.MessageModel;
import net.hncu.chat.utils.DBUtil;

/**
 * CreateTime: 2018年6月1日 上午10:15:07	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	数据库操作接口的实现类
 */
public class MessageDaoJdbcImpl implements MessageDAO {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet res = null;
	@Override
	public boolean create(MessageModel msg) {
		//预防空指针
		if ( msg == null ) {
			return false;
		}
		//书写sql 语句
		String sql = "INSERT INTO tb_message VALUES(null,?,now(),?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement( sql );
			ps.setString( 1, msg.getUsername() );
			ps.setString( 2, msg.getContent() );
			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if ( ps != null ) {
					ps.close();
				}
				if ( con != null ) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public MessageModel getById(int id) {
		//非法数据直接返回null
		if( id < 0 ) {
			return null;
		}
		String sql = "SELECT m.*,u.petName FROM tb_message AS m,tb_user AS u WHERE m.id = ? AND m.username = u.username";
		try {
		    con = DBUtil.getConnection();
		    ps = con.prepareStatement( sql );
		    ps.setInt( 1, id );
		    res = ps.executeQuery();
		    if ( res.next() ) {
		    	MessageModel msg = new MessageModel();
		    	msg.setId(id);
		    	msg.setUsername( res.getString( 2 ) );
		    	msg.setDate( res.getTimestamp( 3 ) );
		    	msg.setContent( res.getString( 4 ) );
		    	msg.setPetName( res.getString( 5 ) );
		    	return msg;
		    }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if ( res != null ) {
					res.close();
				}
				
				if ( ps != null ) {
					ps.close();
				}
				
				if ( con != null ) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Collection<MessageModel> getAll() {
		//先创建一个结果集
		Collection<MessageModel> col = new ArrayList<MessageModel>();
		String sql = "SELECT m.*,u.petName FROM tb_message AS m,tb_user AS u WHERE m.username = u.username ORDER BY m.id ASC";
		try {
		    con = DBUtil.getConnection();
		    ps = con.prepareStatement( sql );
		    res = ps.executeQuery();
		    while ( res.next() ) {
		    	//创建一个对象
		    	MessageModel msg = new MessageModel();
		    	//封装数据
		    	msg.setId( res.getInt( 1 ) );
		    	msg.setUsername( res.getString( 2 ) );
		    	msg.setDate( res.getTimestamp( 3 ) );
		    	msg.setContent( res.getString( 4 ) );
		    	msg.setPetName( res.getString( 5 ) );
		    	//添加到集合中
		    	col.add( msg );
		    }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if ( res != null ) {
					res.close();
				}
				
				if ( ps != null ) {
					ps.close();
				}
				
				if ( con != null ) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return col;
	}

	@Override
	public Collection<MessageModel> getByCondition(String sql) {
		//预防空指针
		if ( sql == null ) {
			return null;
		}
		//先创建一个结果集
		Collection<MessageModel> col = new ArrayList<MessageModel>();
		try {
		    con = DBUtil.getConnection();
		    ps = con.prepareStatement( sql );
		    res = ps.executeQuery();
		    while ( res.next() ) {
		    	//创建一个对象
		    	MessageModel msg = new MessageModel();
		    	//封装数据
		    	msg.setId( res.getInt( 1 ) );
		    	msg.setUsername( res.getString( 2 ) );
		    	msg.setDate( res.getTimestamp( 3 ) );
		    	msg.setContent( res.getString( 4 ) );
		    	msg.setPetName( res.getString( 5 ) );
		    	//添加到集合中
		    	col.add( msg );
		    }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if ( res != null ) {
					res.close();
				}
				
				if ( ps != null ) {
					ps.close();
				}
				
				if ( con != null ) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return col;
	}
	
}

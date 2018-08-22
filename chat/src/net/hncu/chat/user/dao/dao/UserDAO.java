package net.hncu.chat.user.dao.dao;

import java.util.Collection;

import net.hncu.chat.user.javabean.UserModel;

/**
 * 
 * CreateTime: 2018年5月27日 上午10:14:42	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description:
 *	UserDAO接口，对数据库的 增、删、改、查
 */
public interface UserDAO {
	/**
	 * 创建一个用户
	 * @param user 新用户
	 * @return true--创建成功， false -- 创建失败
	 */
	public abstract boolean creat(UserModel user);
	/**
	 * 根据 username 来删除一个用户
	 * @param username 被删除的用户的用户名
	 * @return true--删除成功， false -- 删除失败
	 */
	public abstract boolean delete(String username);
	/**
	 * 根据新的 值对象 去更新原来的 数据
	 * @param user 新的 值对象
	 * @return true--修改成功， false -- 修改失败
	 */
	public abstract boolean update(UserModel user);
	/**
	 * 根据 username 查询一个用户
	 * @param username 被查询的用户的用户名
	 * @return null--没有该用户，否则就是被查询的用户
	 */
	public abstract UserModel getUserByUserName(String username);
	/**
	 * 查询所有用户
	 * @return 所有用户的集合
	 */
	public abstract Collection<UserModel> getAll();
}

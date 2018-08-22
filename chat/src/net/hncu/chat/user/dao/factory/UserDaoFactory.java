package net.hncu.chat.user.dao.factory;
/**
 * CreateTime: 2018年5月27日 下午4:08:34	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	获得UserDAO实现类 的工厂
 */

import net.hncu.chat.user.dao.dao.UserDAO;
import net.hncu.chat.user.dao.impl.UserDaoJdbcImpl;

public class UserDaoFactory {
	/**
	 * 私有化构造函数
	 */
	private UserDaoFactory() {}
	/**
	 * 获取一个UserDAO 接口的实现类的实例
	 * @return 一个UserDAO 接口的实现类的实例
	 */
	public static UserDAO getUserDao() {
		return new UserDaoJdbcImpl();
	}
}

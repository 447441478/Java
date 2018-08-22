package net.hncu.chat.message.dao.factory;
/**
 * CreateTime: 2018年6月1日 上午10:43:15	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	生产   数据库操作接口的实现类
 */

import net.hncu.chat.message.dao.dao.MessageDAO;
import net.hncu.chat.message.dao.impl.MessageDaoJdbcImpl;

public class MessageDaoFactory {
	/**
	 * 私有化构造方法
	 */
	private MessageDaoFactory() {}
	/**
	 * 获得一个 MessageDAO接口的实现类
	 * @return MessageDAO接口的实现类
	 */
	public static MessageDAO getMessageDao() {
		return new MessageDaoJdbcImpl();
	}
}

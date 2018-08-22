package net.hncu.chat.message.dao.dao;

import java.util.Collection;

import net.hncu.chat.message.javabean.MessageModel;

/**
 * CreateTime: 2018年6月1日 上午10:07:00	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	操作数据库的接口
 */
public interface MessageDAO {
	/**
	 * 添加一条消息记录
	 * @param msg 被添加的记录值对象
	 * @return true--成功，false--失败
	 */
	public abstract boolean create(MessageModel msg);
	/**
	 * 根据id 查询对应的id号的消息记录
	 * @param id 消息记录的id
	 * @return null--没有对应的消息记录，否则就是对应的消息记录。
	 */
	public abstract MessageModel getById(int id);
	/**
	 * 获得所有消息记录
	 * @return 所有消息记录的集合
	 */
	public abstract Collection<MessageModel> getAll();
	/**
	 * 根据 sql 语句查询
	 * @param sql 查询语句
	 * @return null--sql语句有问题,否则就是符合sql语句的所有消息记录的集合
	 */
	public abstract Collection<MessageModel> getByCondition(String sql);
}

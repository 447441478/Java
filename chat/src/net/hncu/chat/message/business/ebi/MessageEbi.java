package net.hncu.chat.message.business.ebi;

import java.util.ArrayList;

import net.hncu.chat.message.javabean.MessageModel;

/**
 * CreateTime: 2018年6月1日 上午10:45:36	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	处理业务的接口
 */
public interface MessageEbi {
	/**
	 * 处理添加一条消息的业务
	 * @param msg 被添加的消息对象
	 * @return true--添加成功，false--添加失败。
	 */
	public abstract boolean addMessage(MessageModel msg);
	/**
	 * 获得 第id条消息之后的所有消息的集合
	 * @param id 消息记录对应的id
	 * @return null--id非法,否则就是第id条消息之后的所有消息的集合
	 */
	public abstract ArrayList<MessageModel> getByIdAfter( int id );
}

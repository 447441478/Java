package net.hncu.chat.message.business.ebo;

import java.util.ArrayList;

import net.hncu.chat.message.business.ebi.MessageEbi;
import net.hncu.chat.message.dao.dao.MessageDAO;
import net.hncu.chat.message.dao.factory.MessageDaoFactory;
import net.hncu.chat.message.javabean.MessageModel;

/**
 * CreateTime: 2018年6月1日 上午10:51:25	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	处理业务的接口的实现类
 */
public class MessageEbo implements MessageEbi {
	//注入MessageDAO
	private MessageDAO messageDao = MessageDaoFactory.getMessageDao();
	
	@Override
	public boolean addMessage(MessageModel msg) {
		if( msg == null ) {
			return false;
		}
		return messageDao.create( msg );
	}

	@Override
	public ArrayList<MessageModel> getByIdAfter(int id) {
		if( id < 0 ) {
			return null;
		}
		//这里是要进行条件查询，所以 要根据当前处理的业务 进行定制sql 语句
		String sql = "SELECT m.*,u.petName FROM tb_message AS m,tb_user AS u WHERE m.id >" + id + " AND m.username = u.username ORDER BY m.id ASC";
		return (ArrayList<MessageModel>)messageDao.getByCondition(sql);
	}

}

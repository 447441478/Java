package net.hncu.chat.message.business.factory;

import net.hncu.chat.message.business.ebi.MessageEbi;
import net.hncu.chat.message.business.ebo.MessageEbo;

/**
 * CreateTime: 2018年6月1日 上午10:58:20	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	生产 MessageEbi接口的实现类
 */
public class MessageEbiFactory {
	/**
	 * 私有化构造函数
	 */
	private MessageEbiFactory() {}
	/**
	 * 获得MessageEbi接口的实现类
	 * @return MessageEbi接口的实现类
	 */
	public static MessageEbi getMessageEbi() {
		return new MessageEbo();
	}
}

package net.hncu.chat.utils;
/**
 * CreateTime: 2018年6月1日 下午2:35:15	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	处理客户端发送过来的消息
 */
public class DealMessage {
	/**
	 * 工具类直接私有化构造函数
	 */
	private DealMessage() {}
	/**
	 * 转换msg中的特殊字符 如： & < > " \n
	 * @param message 消息内容
	 * @return null--消息内容为null,否则就是处理完的消息内容
	 */
	public static String change(String message) {
		//预防空指针
		if ( message == null ) {
			return null;
		}
		//转换&字符
		message = message.replaceAll("&", "&amp;");
		//转换<字符
		message = message.replaceAll("<", "&lt;");
		//转换>字符
		message = message.replaceAll(">", "&gt;");
		
		message = message.replace("\"", "\\\"");
		
		message = message.replace("\n", "\\r\\n");
		
		return message;
	}
}

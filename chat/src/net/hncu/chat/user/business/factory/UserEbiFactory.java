package net.hncu.chat.user.business.factory;

import net.hncu.chat.user.business.ebi.UserEbi;
import net.hncu.chat.user.business.ebo.UserEbo;

/**
 * CreateTime: 2018年5月27日 下午4:20:41	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	生产UserEbi接口的实现类的工厂
 */
public class UserEbiFactory {
	/**
	 * 私有化构造函数
	 */
	private UserEbiFactory() {}
	
	public static UserEbi getUserEbi() {
		return new UserEbo();
	}
}

package net.hncu.chat.user.business.ebi;

import net.hncu.chat.user.javabean.UserModel;

/**
 * CreateTime: 2018年5月27日 下午4:12:26	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	UserEbi 接口，处理业务逻辑的接口
 */
public interface UserEbi {
	/**
	 * 处理注册业务
	 * @param user 封装数据的值对象
	 * @return true--注册成功，false--注册失败
	 */
	public abstract boolean regist(UserModel user);
	/**
	 * 根据username查询用户
	 * @param username 用户名
	 * @return null--没有该用户，否则就是有
	 */
	public abstract UserModel FindUserByUserName(String username);
	/**
	 * 修改 用户信息
	 * @param newUser 新的数据
	 * @return true-成功，false-失败
	 */
	public abstract boolean updateUserInfo(UserModel newUser);
	
}

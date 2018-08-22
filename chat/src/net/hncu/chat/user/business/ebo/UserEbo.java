package net.hncu.chat.user.business.ebo;

import net.hncu.chat.user.business.ebi.UserEbi;
import net.hncu.chat.user.dao.dao.UserDAO;
import net.hncu.chat.user.dao.factory.UserDaoFactory;
import net.hncu.chat.user.javabean.UserModel;

public class UserEbo implements UserEbi {
	//注入 UserDAO
	private UserDAO userDao = UserDaoFactory.getUserDao();
	
	@Override
	public boolean regist(UserModel user) {
		//预防空指针
		if ( user == null ) {
			return false;
		}
		//注册时 用户昵称默认为 用户名
		user.setPetName( user.getUsername() );
		return userDao.creat( user );
	}

	@Override
	public UserModel FindUserByUserName(String username) {
		//预防空指针
		if ( username == null ) {
			return null;
		}
		UserModel user = userDao.getUserByUserName( username );
		if ( user != null ) {
			if ( user.getProfile() == null ) {
				user.setProfile( "无" );
			}
			if ( user.getEmail() == null ) {
				user.setEmail( "无" );
			}
		}
		return user;
	}

	@Override
	public boolean updateUserInfo(UserModel newUser) {
		if ( newUser == null ) {
			return false;
		}
		return userDao.update( newUser );
	}

}

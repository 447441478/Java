package net.hncu.testchat.testUser.dao.impl;


import java.sql.SQLException;
import java.util.Collection;

import org.junit.Test;

import net.hncu.chat.user.dao.impl.UserDaoJdbcImpl;
import net.hncu.chat.user.javabean.UserModel;

public class TestUserDaoJdbcImpl {
	UserDaoJdbcImpl udji = new UserDaoJdbcImpl();
	@Test
	public void testCreate() throws ClassNotFoundException, SQLException {
		UserModel user = new UserModel();
		user.setUsername( "1234" );
		user.setPassword( "1234" );
		boolean boo = udji.creat( user );
		System.out.println( boo );
	}
	@Test
	public void testDelete() {
		boolean boo = udji.delete("1234");
		System.out.println( boo );
	}
	@Test
	public void testUpdate() {
		UserModel user = new UserModel();
		user.setUsername( "12345" );
		user.setPassword( "12345" );
		boolean boo = udji.update( user );
		System.out.println( boo );
	}
	@Test
	public void testGetUserByUserName() {
		UserModel user = udji.getUserByUserName( "1234" );
		System.out.println( user.getUsername() );
	}
	
	@Test
	public void testGetAll() {
		Collection<UserModel> all = udji.getAll();
		for (UserModel userModel : all) {
			System.out.println( userModel.getUsername() );
		}
	}
}

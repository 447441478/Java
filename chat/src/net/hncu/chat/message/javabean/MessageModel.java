package net.hncu.chat.message.javabean;

import java.io.Serializable;
import java.util.Date;

public class MessageModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private int id; //消息编号 -- 主键
	private String username; //发送消息者的用户名 -- 外键
	private String petName; //为外键补的用户昵称 不改 message表结构
	
	
	private Date date; //发送消息的时间
	private String content; //消息内容
	
	public MessageModel() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}

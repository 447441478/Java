package net.hncu.chat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CreateTime: 2018年6月1日 下午4:52:44	
 * @author 宋进宇  Email:447441478@qq.com
 * @Description
 *	处理时间的工具
 */
public class MyDateTool {
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss"); 
	
	private MyDateTool() {}
	
	public static String toLocalStrint( Date date) {
		return sdf.format(date);
	}
}

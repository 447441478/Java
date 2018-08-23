package cn.hncu.dom4j;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Dom4jDemo {
	
	private Document dom;

	@Before
	public void Before() throws Exception  {
		//获取org.dom4j.io.SAXReader对象
		SAXReader reader = new SAXReader();
		//读取xml文件到内存中
		dom = reader.read("./xml/users.xml");
	}
	@After
	public void After() throws Exception {
		/*
		//获取org.dom4j.io.XMLWriter对象
		XMLWriter writer = new XMLWriter( new FileWriter("./xml/users.xml") );
		//把dom对象序列化（持久化）
		writer.write(dom);
		//注意关流---不仅考虑到安全性，字符流关流前还会刷一下缓存。
		writer.close();
		*/
	}
	
	@Test
	public void helloDom4j() {
		//执行这里时会先执行被@Before注解的函数
		
		Element root = dom.getRootElement();
		System.out.println(root.getName()); //users  //ok
		
		//这里执行完毕后会执行被@After注解的函数
	}
	
	//查询:查询每个user的信息
	@Test
	public void query() {
		Element root = dom.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> eUserList = root.elements("user");
		for (Element eUser : eUserList) {
			String id = eUser.attributeValue("id");
			//获取元素的文本内容 
			//法1
			String name = eUser.element("name").getTextTrim(); 
			//法2 
			String age = eUser.elementTextTrim("age");
			
			System.out.println(id+","+name+","+age);
		}
		System.out.println("------------------");
		//下面演示Iterator遍历
		@SuppressWarnings("unchecked")
		Iterator<Element> iterator = root.elementIterator("user");
		while(iterator.hasNext()) {
			Element eUser = iterator.next();
			String id = eUser.attributeValue("id");
			//获取元素的文本内容 
			//法1
			String name = eUser.element("name").getTextTrim(); 
			//法2 
			String age = eUser.elementTextTrim("age");
			
			System.out.println(id+","+name+","+age);
		}
	}
	
	//添加:添加一个学生信息
	@Test
	public void add() throws Exception {
		Element root = dom.getRootElement();
		
		//相对于标准的dom 方法 dom4j第三方工具就简单多了
		Element eStudent = root.addElement("student");
		//给eStudent元素添加属性
		eStudent.addAttribute("id", "s001");
		//给eStudent添加子元素<name>
		Element eName = eStudent.addElement("name");
		//设置<name>元素的文本内容
		eName.setText("宋同学");
		
		//给eStudent添加子元素<age>并且设置文本内容
		eStudent.addElement("age").setText("18");  //一步到位
		
		//注意上面只是对内存中dom树的操作,并不是修改xml文件
		//所以需要把内存的dom树序列化（持久化）
		//获取org.dom4j.io.XMLWriter对象
		XMLWriter writer = new XMLWriter( new FileWriter("./xml/users.xml") );
		//把dom对象序列化（持久化）
		writer.write(dom);
		//注意关流---不仅考虑到安全性，字符流关流前还会刷一下缓存。
		writer.close();
	}
	
	//添加:刪除根元素<users>的最后一个子元素
	@Test
	public void delLast() throws Exception {
		Element root = dom.getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		if ( elements.size() <= 0 ) {
			return;
		}
		Element element = elements.get(elements.size()-1);
		//找父亲删除自己
		element.getParent().remove(element);
		//注意上面只是对内存中dom树的操作,并不是修改xml文件
		//所以需要把内存的dom树序列化（持久化）
		//获取org.dom4j.io.XMLWriter对象
		XMLWriter writer = new XMLWriter( new FileWriter("./xml/users.xml") );
		//把dom对象序列化（持久化）
		writer.write(dom);
		//注意关流---不仅考虑到安全性，字符流关流前还会刷一下缓存。
		writer.close();
	}
	
	//演示一下格式化输出
	@Test
	public void format() throws Exception {
		//紧凑型输出
		//OutputFormat format = OutputFormat.createCompactFormat();
		//漂亮型输出 -- 会有换行
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter( 
							new PrintWriter("./xml/users.xml"),
							format );
		writer.write(dom);
		writer.close();
		
		//查看一下xml文件观察哦
	}
	
	//添加:修改根元素<users>的最后一个子元素
	@Test
	public void updateLast() throws Exception {
		Element root = dom.getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		if( elements.size() <= 0 ) {
			return;
		}
		Element element = elements.get(elements.size()-1);
		//注意dom4j中修改和添加属性都是采用addAttribute方法
		element.addAttribute("id", "s007"); 
		
		element.element("name").setText("王同学");
		//注意addText与addAttribute是不同的
		//addText是在原有的基础上进行追加
		//而addAttribute如果有内容就是修改，如果没有就是添加
		//element.element("age").addText("66");
		element.element("age").setText("21");
		
		//注意上面只是对内存中dom树的操作,并不是修改xml文件
		//所以需要把内存的dom树序列化（持久化）
		//获取org.dom4j.io.XMLWriter对象
		XMLWriter writer = new XMLWriter( new FileWriter("./xml/users.xml") );
		//把dom对象序列化（持久化）
		writer.write(dom);
		//注意关流---不仅考虑到安全性，字符流关流前还会刷一下缓存。
		writer.close();
	}
	
	////////dom4j+Xpath技术演示,注意要导一个支持jar包:jaxen////////////////////
	/*
	 * 使用xpath时，dom4j的代码套路:
	 *  1) 通过dom调用Node中的如下方法进行节点选择(有时也选择对象如属性值)，参数用xpath表达式:
	 *  2) 一点小细节: 以上3个方法是Node的，因此任意节点对象都可以调用，但结果
	 *   却是dom下符合xpath的所有节点或对象，不是当前调用节点的子树范围(局部),
	 *   而是整棵树(全局)，因此为避免歧义，建议使用dom调用
	 */
	
	@Test
	public void xpathHello() {
		String xpath = "//user"; //获取dom树上所有<user>元素 
		@SuppressWarnings("unchecked")
		List<Element> selectNodes = dom.selectNodes(xpath);
		for (Element element : selectNodes) {
			String id = element.attributeValue("id");
			//获取元素的文本内容 
			String name = element.elementTextTrim("name"); 
			String age = element.elementTextTrim("age");
			
			System.out.println(id+","+name+","+age);
		}
	}
	@Test
	public void xpathDemo() throws Exception {
		dom = new SAXReader().read("./xml/xpathDemo.xml");
		
		// xpath中   祖先//后代   //前后是 "祖先与后代" 关系
		// xpath中   父亲/孩子   //前后是直接 "父子" 关系
		
		//String xpath = "//bbb"; //dom树下的所有bbb元素
		String xpath = "//aaa/bbb"; //dom树下的所有aaa元素下的bbb直接子元素
		
		@SuppressWarnings("unchecked")
		List<Element> selectNodes = dom.selectNodes(xpath);
		for (Element element : selectNodes) {
			System.out.println( element.attributeValue("id") );
		}
		
	}
	
}

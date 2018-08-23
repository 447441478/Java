package cn.hncu.dom;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * 技术入口：
 * 	org.w3c.dom.Document接口
 * 具体实现：
 * 	javax.xml.parsers.DocumentBuilder类的parse方法解析一个xml文件
 * 返回一个org.w3c.dom.Document的实现类
 * 
 * javax.xml.parsers.DocumentBuilder
 * 需要javax.xml.parsers.DocumentBuilderFactory对象的newDocumentBuilder()方法
 * javax.xml.parsers.DocumentBuilderFactory对象
 * 需要javax.xml.parsers.DocumentBuilderFactory.newInstance()获取
 */
public class DomURUD {
	
	private static Document dom ;
	private static Node root;
	
	@BeforeClass
	public static void before()throws Exception {
		//加载文档树
		dom = DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse("./xml/users.xml");
		//获取dom文档树中的第一个孩子，也就是<users>元素
		root = dom.getFirstChild();
	}
	
	//测试查询
	@Test
	public void query() {
		Element eUsers = (Element) root;
		NodeList colUser = eUsers.getElementsByTagName("user");
		for(int i = 0; i < colUser.getLength(); i++) {
			String id = ((Element)colUser.item(i)).getAttribute("id");
			String name = ((Element)colUser.item(i))
							.getElementsByTagName("name")
							.item(0)
							.getTextContent().trim();
			String age = ((Element)colUser.item(i))
					.getElementsByTagName("age")
					.item(0)
					.getTextContent().trim();
			System.out.println("id:"+id+",name:"+name+","+age);				
		}
	}
	
	//测试修改:修改最后一个<user>
	@Test
	public void update() throws Exception {
		Element eUsers = (Element)root;
		NodeList colUser = eUsers.getElementsByTagName("user");
		Element eUser = ((Element) colUser.item(colUser.getLength()-1));
		Node nAge = eUser.getElementsByTagName("age").item(0);
		nAge.setTextContent("20");
		
		//改完节点只是修改了内存中的dom树数据
		//要修改xml文件需要进行持久化操作
		Transformer transformer = TransformerFactory
									.newInstance()
									.newTransformer();
		transformer.transform(new DOMSource(dom), new StreamResult("./xml/users.xml"));
	}
	//测试添加：在users.xml中<users>元素追加一个子元素<user>
	@Test
	public void add() throws Exception {
		//先创建<user>元素
		Element eUser = dom.createElement("user");
		eUser.setAttribute("id", "u007");
		
		//创建<name>元素
		Element eName = dom.createElement("name");
		eName.setTextContent("李四");
		
		//创建<age>元素
		Element eAge = dom.createElement("age");
		eAge.setTextContent("66");
		
		//组装user子树
		eUser.appendChild(eName);
		eUser.appendChild(eAge);
		
		//把user子树添加到users元素中
		root.appendChild(eUser);
		
		//改完节点只是修改了内存中的dom树数据
		//要修改xml文件需要进行持久化操作
		//一步到位
		TransformerFactory
			.newInstance()
			.newTransformer()
			.transform(	new DOMSource(dom), new StreamResult("./xml/users.xml") );
	}
	
	//测试删除：删除最后一个<user>元素
	@Test
	public void delete() throws Exception {
		Element eUsers = (Element)root;
		NodeList colUser = eUsers.getElementsByTagName("user");
		Node nUser = colUser.item(colUser.getLength()-1);
		//找父节点删自己
		nUser.getParentNode().removeChild(nUser);
		
		//改完节点只是修改了内存中的dom树数据
		//要修改xml文件需要进行持久化操作
		//一步到位
		TransformerFactory
			.newInstance()
			.newTransformer()
			.transform(	new DOMSource(dom), new StreamResult("./xml/users.xml") );
		
	}
}

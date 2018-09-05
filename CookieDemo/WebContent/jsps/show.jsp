<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
       <title>商品浏览</title>
       <style type="text/css">
       	 img{
       	   border: 0px;
       	   width:150px;
       	   height:150px;
       	 }
       	 
       	 .small{
       	   border: 0px;
       	   width:50px;
       	   height:50px;
       	 }
       	 
       </style>
    </head>
  
  <body>
  	<a href="<%=request.getContextPath()%>/index.jsp">返回主页</a>
    <h3>浏览历史</h3>
    <%
       String str = null;
       Cookie cs[] = request.getCookies();
       if(cs!=null){
    	   for(Cookie c:cs){
    		   if(c.getName().equals("imgs")){
    			   str=c.getValue(); //4.jpg, 2.jpg, 3.jpg
    			   break;
    		   }
    	   }
       }
       if(str!=null){//有历史浏览信息
    	   String strs[] = str.split("!"); //4.jpg, 2.jpg, 3.jpg
       		for(String s:strs){
    %>
       			<img class=".small" src="<%=request.getContextPath()%>/imgs/<%=s%>" />
    <%
       		}
       }
    %>
    
    
	<hr/>
    <h2>商品浏览</h2>
     <a href="<%=request.getContextPath()%>/ShowImgServlet?img=1.jpg">
       <img src="<%=request.getContextPath()%>/imgs/1.jpg">
     </a>
     <a href="<%=request.getContextPath()%>/ShowImgServlet?img=2.jpg">
       <img src="<%=request.getContextPath()%>/imgs/2.jpg">
     </a>
     <a href="<%=request.getContextPath()%>/ShowImgServlet?img=3.jpg">
       <img src="<%=request.getContextPath()%>/imgs/3.jpg">
     </a>
     <a href="<%=request.getContextPath()%>/ShowImgServlet?img=4.jpg">
       <img src="<%=request.getContextPath()%>/imgs/4.jpg">
     </a>
     <a href="<%=request.getContextPath()%>/ShowImgServlet?img=5.jpg">
       <img src="<%=request.getContextPath()%>/imgs/5.jpg">
     </a>
     <a href="<%=request.getContextPath()%>/ShowImgServlet?img=6.jpg">
       <img src="<%=request.getContextPath()%>/imgs/6.jpg">
     </a>
     <a href="<%=request.getContextPath()%>/ShowImgServlet?img=7.jpg">
       <img src="<%=request.getContextPath()%>/imgs/7.jpg">
     </a>
     <a href="<%=request.getContextPath()%>/ShowImgServlet?img=8.jpg">
       <img src="<%=request.getContextPath()%>/imgs/8.jpg">
     </a>    
  </body>
</html>

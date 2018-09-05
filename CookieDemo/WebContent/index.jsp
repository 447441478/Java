<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>cookie,session技术演示</title>
  </head>
  
  <body>
    <h1>cookie,session技术演示</h1>
    
    <a href="CookieDemoServlet">cookie技术演示1-1: 创建、修改、遍历</a> <br>
    <a href="CookieDelServlet">cookie技术演示1-2: 删除</a> <br>
    <a href="servlet/CookieDemoServlet2">cookie技术演示2: 通过path实现权限控制</a> <br>
    <!-- 权限助记:servlet的映射路径必须比cookie的path路径
                   更长或至少相同，即只有path或它的子路径的servlet才可以
                   访问到该cookie  -->
   
    <h2>cookie技术使用示例</h2>
    <a href="LoginServlet">登录时能够显示上次登录的时间</a> <br>
    <a href="jsps/show.jsp">显示用户最近的浏览历史</a> <br>
    
  </body>
</html>

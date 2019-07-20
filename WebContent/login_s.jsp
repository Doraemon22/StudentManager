<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import=" java.util.List "%>
     <%@ page import=" java.util.ArrayList "%>
      <%@ page import=" com.dt.hrbu.vo.* "%>
      
      <!-- 类型转换 -->   
   <%User username=(User)session.getAttribute("username");%>  
   <%List<User> userList=(ArrayList<User>)session.getAttribute("userList"); %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

登录成功！欢迎<%=username.getUsername()%>  <br/>

<table border="1">
<tr><td>用户名</td><td>年龄</td></tr>
<%
		for(User user:userList){
			%>
			<tr><td><%=user.getUsername() %></td>  <td><%=user.getAge() %></td>   </tr>
			<% 
		}
%>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
List User!!<br>


<s:form action='listUser' method="post"> 
            <s:submit value="查詢所有會員"></s:submit>   
</s:form>   


<s:iterator value="%{userList}">

<br>     ID: <s:property value="id"/> <br>
   UserName: <s:property value="username"/><br>
<br>


</s:iterator>

 


</body>
</html>
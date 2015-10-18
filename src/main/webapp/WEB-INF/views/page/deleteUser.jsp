<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:form action='deleteUser'> 
        <s:textfield name="user.id" label="id"></s:textfield>     
        <s:textfield name="user.username" label="用名"></s:textfield>   
        <s:textfield name="user.password" label="密碼"></s:textfield>   
            <s:submit value="刪除"></s:submit>   
</s:form>   
</body>
</html>
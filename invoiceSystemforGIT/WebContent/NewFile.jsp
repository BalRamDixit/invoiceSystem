<%@page import="jjit.validator.EmailValidator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="NewFile.jsp">
		email <input type="text" name="email" value="<%=request.getParameter("email")%>"/>
		<input type="submit" value="Check"/>
	</form>
	<pre>
	<%
	String email=request.getParameter("email");
	if(email!=null)
	{
	%>
	
	Index of @ --  <%=email.indexOf("@") %>
	
	Index of . -- <%=email.indexOf(".") %>
	
	
	<%
		EmailValidator validemail=new EmailValidator();
		if(validemail.validate(email))
		{
			%>
			<h1>Email is valid</h1>
			<%	
		}
		else
		{
			 %>
			 <h1>Email not Valid</h1>
			 <%
		}
	}	
	%>
	</pre>
	
	
</body>
</html>
<%@page import="jjit.DAO.DbAction"%>
<%@page import="java.sql.ResultSet"%>
<%
	String uuid=request.getParameter("uuid");
	ResultSet rs=DbAction.getParticular(uuid);
	if(rs.next())
	{
		out.println("<h2>Particular</h2>");
		out.println("<h3><pre>"+rs.getString(1)+"</pre></h3>");
		
		out.println("<h2>PaymentTerms</h2>");
		out.println("<h3><pre>"+rs.getString(2)+"</pre></h3>");
	}
%>
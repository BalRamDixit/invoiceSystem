<%@page import="jjit.DAO.DbAction"%>
<%@page import="java.sql.ResultSet"%>
<%
	String uuid=request.getParameter("uuid");
	ResultSet rs=DbAction.getParticular(uuid);
	if(rs.next())
	{
		out.println("<pre><h2>Particular</h2>");
		out.println("<h3>         "+rs.getString(1)+"</h3>");
		
		out.println("<h2>PaymentTerms</h2>");
		out.println("<h3>         "+rs.getString(2)+"</h3></pre>");
	}
%>
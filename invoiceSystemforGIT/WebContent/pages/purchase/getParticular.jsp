<%@page import="jjit.DAO.DbAction"%>
<%@page import="java.sql.ResultSet"%>
<%
	String uuid=request.getParameter("uuid");
	ResultSet rs=DbAction.getParticular(uuid);
	if(rs.next())
	{
		out.println("<pre><h1>PArticular</h1>");
		out.println("<h2>          "+rs.getString(1)+"</h2>");
		
		out.println("\n\n\n<h1>PaymentTerms</h1>");
		out.println("<h2>          "+rs.getString(2)+"</h2></pre>");
	}
%>
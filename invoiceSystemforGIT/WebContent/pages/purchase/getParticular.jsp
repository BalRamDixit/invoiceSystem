<%@page import="jjit.DAO.DbAction"%>
<%@page import="java.sql.ResultSet"%>
<%
	String uuid=request.getParameter("uuid");
	ResultSet rs=DbAction.getParticular(uuid);
	if(rs.next())
	{
		out.println("PArticular");
		out.println(rs.getString(1));
		
		out.println("PaymentTerms");
		out.println(rs.getString(2));
	}
%>
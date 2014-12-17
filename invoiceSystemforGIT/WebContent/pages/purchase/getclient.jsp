<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="jjit.DAO.DbAction"%>
<%@page import="java.sql.ResultSet"%>
<%
	ResultSet rs = DbAction.get_clientsforpo();
	JSONArray ja = new JSONArray();
	int i = 0;
	while (rs.next()) {
		HashMap<String, String> row = new HashMap<String, String>();
		String uuid = rs.getString("uuid");
		String contactperson = rs.getString("contactperson");
		String email = rs.getString("email");
		String city = rs.getString("city");
		row.put("uuid", uuid);
		row.put("contactperson", contactperson + " - " + city);
		JSONObject jb = new JSONObject(row);
		ja.add(i, jb);
		i++;
	}
	out.println(ja);
%>
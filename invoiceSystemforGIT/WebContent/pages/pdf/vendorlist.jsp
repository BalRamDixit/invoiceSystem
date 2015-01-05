<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.jjit.dao.DbAction"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Document"%>
<% 
	response.setContentType("application/pdf");
	Document doc=new Document(PageSize.A4,32,32,100,100);
	PdfWriter.getInstance(doc, response.getOutputStream());
	doc.open();
	Paragraph p1=new Paragraph("                ---------------- List FOR Vendors ----------------");
	float[] widths={2f,3f,4f,3f,3f,3f,3f};
	PdfPTable table=new PdfPTable(widths);
	table.setWidthPercentage(110);
	PdfPCell cell=new PdfPCell(p1);
	cell.setColspan(7);
	table.addCell(cell);
	table.addCell("S NO");
	table.addCell("Client Name");
	table.addCell("Company name");
	table.addCell("Mobile No");
	table.addCell("Email Id");
	table.addCell("Address");
	table.addCell("City");
	ResultSet rs=DbAction.get_vendorsforpo();
	int i=1;
	while(rs.next())
	{
		table.addCell(i+"");
		table.addCell(rs.getString(2));
		table.addCell(rs.getString(3));
		table.addCell(rs.getString(4));
		table.addCell(rs.getString(5));
		table.addCell(rs.getString(6));
		table.addCell(rs.getString(7));
		i++;
	}
	doc.add(table);
	doc.close();
%>
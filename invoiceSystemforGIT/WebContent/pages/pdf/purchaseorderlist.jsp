<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="jjit.DAO.DbAction"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Document"%>
<% 
	response.setContentType("application/pdf");
	Document doc=new Document(PageSize.A4_LANDSCAPE,32,32,100,100);
	PdfWriter.getInstance(doc, response.getOutputStream());
	doc.open();
	Paragraph p1=new Paragraph("                ---------------- List PurchaseOrders ----------------");
	float[] widths={2f,3f,4f,3f,3f,3f,3f};
	PdfPTable table=new PdfPTable(widths);
	table.setWidthPercentage(110);
	PdfPCell cell=new PdfPCell(p1);
	cell.setColspan(7);
	table.addCell(cell);
	table.addCell("S NO");
	table.addCell("Client Name");
	table.addCell("Vendor Name");
	table.addCell("Create date");
	table.addCell("Start date");
	table.addCell("End date");
	table.addCell("Unit");
	table.addCell("Rate");
	table.addCell("Duration");
	table.addCell("Service tax extra");
	table.addCell("Total");
	table.addCell("Particular");
	table.addCell("PaymentTerms");
	table.addCell("PurchaseOrderStatus");
	table.addCell("Payment Status");
	
	ResultSet rs=DbAction.get_purchaseorder1();
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
		table.addCell(rs.getString(8));
		table.addCell(rs.getString(9));
		table.addCell(rs.getString(10));
		table.addCell(rs.getString(11));
		table.addCell(rs.getString(12));
		table.addCell(rs.getString(13));
		table.addCell(rs.getString(14));
		table.addCell(rs.getString(15));
		i++;
	}
	doc.add(table);
	doc.close();
%>
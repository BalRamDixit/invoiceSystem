package com.jjit.pdfgenerator;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jjit.dao.InvoiceDao;

/**
 * Servlet implementation class PurchaseOrderListPdf
 */
@WebServlet("/PurchaseOrderListPdf")
public class PurchaseOrderListPdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseOrderListPdfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/pdf");
			Document doc=new Document(PageSize.LETTER.rotate(),100,100,100,100);
			PdfWriter.getInstance(doc, response.getOutputStream());
			doc.open();
			Paragraph p1=new Paragraph("---------------- List PurchaseOrders ----------------");
			float[] widths={2f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f};
			PdfPTable table=new PdfPTable(widths);
			table.setWidthPercentage(110);
			PdfPCell cell=new PdfPCell(p1);
			cell.setColspan(15);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			table.addCell("S NO");
			table.addCell("Client Name");//1
			table.addCell("Vendor Name");//2
			table.addCell("Create date");//3
			table.addCell("Start date");//4
			table.addCell("End date");//5
			table.addCell("Unit");//6
			table.addCell("Rate");//7
			table.addCell("Duration");//8
			table.addCell("Service tax extra");//9
			table.addCell("Total");//10
			table.addCell("Particular");//11
			table.addCell("PaymentTerms");//12
			table.addCell("PurchaseOrderStatus");//13
			table.addCell("Payment Status");//14
			
			ResultSet rs=InvoiceDao.get_purchaseorder1();
			int i=1;
			while(rs.next())
			{
				table.addCell(i+"");
				table.addCell(rs.getString(3));//1
				table.addCell(rs.getString(2));//2
				table.addCell(rs.getString(4));//3
				table.addCell(rs.getString(8));//4
				table.addCell(rs.getString(9));//5
				String Unit="Hours";
				if(rs.getString(5).equals("1"))
					Unit="Days";
				table.addCell(Unit);//6
				table.addCell(rs.getString(6));//7
				table.addCell(rs.getString(7));//8
				String stextra="No";
				if(rs.getString(14).equals("1"))
				{
					stextra="YES";
				}
				table.addCell(stextra);//9
				table.addCell(""+Double.parseDouble(rs.getString(7))*Double.parseDouble(rs.getString(6)));//10
				table.addCell(rs.getString(10));//11
				table.addCell(rs.getString(11));//12
				String postatus="";
				if(rs.getString(12).equals("a")){
					postatus="Active";
				}
				else if(rs.getString(12).equals("d")){
					postatus="Done";
				}
				else if(rs.getString(12).equals("t")){
					postatus="To Be Done";
				}
				else
				{
					postatus="Canceled";
				}
				table.addCell(postatus);//13
				String pstatus="";
				if(rs.getString(13).equals("a")){
					pstatus="Paid";
				}
				else if(rs.getString(13).equals("i")){
					pstatus="In progress";
				}
				else if(rs.getString(13).equals("d")){
					pstatus="Not Paid";
				}
				table.addCell(pstatus);//14
				i++;
			}
			doc.add(table);
			doc.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}

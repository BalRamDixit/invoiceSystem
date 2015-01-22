package com.jjit.pdfgenerator;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jjit.dao.PurchaseOrderDao;

/**
 * Servlet implementation class ClientListPdf
 */
@WebServlet("/ClientListPdf")
public class ClientListPdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientListPdfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			response.setContentType("application/pdf");
			Document doc=new Document(PageSize.A4,32,32,100,100);
			PdfWriter.getInstance(doc, response.getOutputStream());
			doc.open();
			Paragraph p1=new Paragraph("                ---------------- List FOR Clients ----------------");
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
			ResultSet rs=PurchaseOrderDao.get_clientsforpo();
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

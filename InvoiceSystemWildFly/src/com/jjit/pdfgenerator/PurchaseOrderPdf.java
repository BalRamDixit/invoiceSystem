package com.jjit.pdfgenerator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class PurchaseOrderPdf
 */
@WebServlet("/PurchaseOrderPdf")
public class PurchaseOrderPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseOrderPdf() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/pdf");
			Document doc=new Document(PageSize.A4,50,50,50,50);
			PdfWriter.getInstance(doc, response.getOutputStream());
			doc.open();
			Paragraph p1=new Paragraph("---------------- List PurchaseOrders ----------------");
			
			
			//Sub table 1 Design
			float[] widths1={6f,1f,1.5f,1.5f};
			PdfPTable subtable1=new PdfPTable(widths1);
			PdfPCell cell1=new PdfPCell(new Paragraph("DESCRIPTION"));
			cell1.setBackgroundColor(new BaseColor(0, 102, 255));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable1.addCell(cell1);
			cell1=new PdfPCell(new Paragraph("UNIT"));
			cell1.setBackgroundColor(new BaseColor(0, 102, 255));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable1.addCell(cell1);
			cell1=new PdfPCell(new Paragraph("RATE"));
			cell1.setBackgroundColor(new BaseColor(0, 102, 255));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable1.addCell(cell1);
			cell1=new PdfPCell(new Paragraph("AMOUNT"));
			cell1.setBackgroundColor(new BaseColor(0, 102, 255));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable1.addCell(cell1);
			
			subtable1.addCell("          ");
			subtable1.addCell("          ");
			subtable1.addCell("          ");
			cell1=new PdfPCell(new Paragraph("          "));
			cell1.setBackgroundColor(new BaseColor(204,204,204));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable1.addCell(cell1);
			
			subtable1.addCell("          ");
			subtable1.addCell("          ");
			subtable1.addCell("          ");
			cell1=new PdfPCell(new Paragraph("          "));
			cell1.setBackgroundColor(new BaseColor(204,204,204));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable1.addCell(cell1);
			
			subtable1.addCell("          ");
			subtable1.addCell("          ");
			subtable1.addCell("          ");
			cell1=new PdfPCell(new Paragraph("          "));
			cell1.setBackgroundColor(new BaseColor(204,204,204));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable1.addCell(cell1);
			
			subtable1.addCell("           ");
			subtable1.addCell("           ");
			subtable1.addCell("           ");
			cell1=new PdfPCell(new Paragraph("           "));
			cell1.setBackgroundColor(new BaseColor(204,204,204));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable1.addCell(cell1);
			
			subtable1.addCell("           ");
			subtable1.addCell("           ");
			subtable1.addCell("           ");
			cell1=new PdfPCell(new Paragraph("           "));
			cell1.setBackgroundColor(new BaseColor(204,204,204));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable1.addCell(cell1);
			
			//Sub table 2 Design
			float[] widths2={6f};
			PdfPTable subtable2=new PdfPTable(widths2);
			PdfPCell cell2=new PdfPCell(new Paragraph("OTHER COMMENTS"));
			cell2.setBackgroundColor(new BaseColor(0, 102, 255));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable2.addCell(cell2);
			subtable2.addCell("1.TOTAL payment due in 30 days");
			subtable2.addCell("2.please include the invoice number on your cheque");
			subtable2.addCell("           ");
			subtable2.addCell("           ");
			subtable2.addCell("           ");
			subtable2.addCell("           ");
			subtable2.addCell("           ");
			subtable2.addCell("           ");
			
			
			
			//Sub table 3 Design
			float[] widths3={1.5f,1.5f};
			PdfPTable subtable3=new PdfPTable(widths3);
			
			
			subtable3.addCell("TAX RATE");
			PdfPCell cell3=new PdfPCell(new Paragraph("        "));
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable3.addCell(cell3);

			
			subtable3.addCell("Tax");
			cell3=new PdfPCell(new Paragraph("        "));
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable3.addCell(cell3);

			
			subtable3.addCell("OTHER");
			cell3=new PdfPCell(new Paragraph("          "));
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable3.addCell(cell3);

			
			cell3=new PdfPCell(new Paragraph("==============="));
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setColspan(2);
			subtable3.addCell(cell3);

			
			subtable3.addCell("TOTAL");
			cell3=new PdfPCell(new Paragraph("          "));
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable3.addCell(cell3);

			
			cell3=new PdfPCell(new Paragraph("               "));
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setColspan(2);
			subtable3.addCell(cell3);

			
			cell3=new PdfPCell(new Paragraph("Make your All Cheque payable To"));
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setColspan(2);
			subtable3.addCell(cell3);

			
			cell3=new PdfPCell(new Paragraph("[Company Name]"));
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setColspan(2);
			subtable3.addCell(cell3);

			//Main table Design
			float[] widths={7f,3f};
			PdfPTable table=new PdfPTable(widths);
			table.addCell("[Company Name]");
			table.addCell("           ");
			table.addCell("[Company Slogan]");
			table.addCell("Date:");
			table.addCell("         ");
			table.addCell("Invoice No [12345]");
			table.addCell("[Street Address]");
			table.addCell("         ");
			table.addCell("[city, ST zip]");
			table.addCell("         ");
			table.addCell("Phone: [STD - Ph no]");
			table.addCell("         ");
			table.addCell("Mob: [xxx-xxx-xxxx]");
			table.addCell("         ");
			table.addCell("         ");
			table.addCell("         ");
			cell3=new PdfPCell(new Paragraph("BILL TO:"));
			cell3.setBackgroundColor(new BaseColor(0, 102, 255));
			table.addCell(cell3);
			table.addCell("         ");
			table.addCell("[Name]");
			table.addCell("         ");
			table.addCell("[Company Name]");
			table.addCell("         ");
			table.addCell("[Street Address]");
			table.addCell("         ");;
			table.addCell("[City, ST zip]");
			table.addCell("         ");
			table.addCell("Phone: [STD- Ph no]");
			table.addCell("         ");
			table.addCell("Mob: [xxx-xxx-xxxx]");
			table.addCell("         ");
			
			
			
			
			
			
			cell3=new PdfPCell(subtable1);
			cell3.setColspan(2);
			table.addCell(cell3);
			
			float[] widths5={7f,1.5f,1.5f};
			PdfPTable subtable4=new PdfPTable(widths5);
			subtable4.addCell("        ");
			subtable4.addCell("SubTotal");
			cell3=new PdfPCell(new Paragraph("          "));
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			subtable4.addCell(cell3);
			cell3=new PdfPCell(subtable4);
			cell3.setColspan(2);
			table.addCell(cell3);
			
			table.addCell(subtable2);
			table.addCell(subtable3);
			cell3=new PdfPCell(new Paragraph("If you have any questions about this invoice,please Contact"));
			cell3.setColspan(2);
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell3);
			cell3=new PdfPCell(new Paragraph("[name,Phone no,E-mail]"));
			cell3.setColspan(2);
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell3);
			cell3=new PdfPCell(new Paragraph("          "));
			cell3.setColspan(2);
			table.addCell(cell3);
			cell3=new PdfPCell(new Paragraph("Thank You For Your Business!"));
			cell3.setColspan(2);
			cell3.setBackgroundColor(new BaseColor(204,204,204));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell3);
			
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

package com.jjit.servlet.Invoice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jjit.dao.InvoiceDao;
import com.jjit.pojo.ExpenseBo;
import com.jjit.pojo.PurchaseOrderBo;

/**
 * Servlet implementation class InvoiceInsert
 */
@WebServlet("/InvoicePreviewInsert")
public class InvoicePreviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoicePreviewInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String data = request.getParameter("data");
			JSONParser jb=new JSONParser();
			JSONObject jo=(JSONObject)jb.parse(data);
			String invoiceno=(String)jo.get("invoiceno");
			String pono=(String)jo.get("pono");
			String sdate=(String)jo.get("sdate");
			String taxvalue=(String)jo.get("taxvalue");
			String total=(String)jo.get("total");
			PurchaseOrderBo purchasebo=(PurchaseOrderBo)request.getSession().getAttribute("purchaseOrder");
			String uuid=purchasebo.getUuid();
			HashMap<Integer, ExpenseBo> expenses=purchasebo.getExpenses();
			purchasebo.setInvoiceno(invoiceno);
			purchasebo.setPurchaseorderno(pono);
			purchasebo.setTax(taxvalue);
			purchasebo.setTotal(total);
			purchasebo.setPodate(sdate);
			HashMap<String,String> row=new HashMap<String,String>();
			row.put("total",purchasebo.getTotal());
			row.put("actotal", purchasebo.getActotal());
			row.put("pototal",purchasebo.getPototal());
			row.put("tax",purchasebo.getTax()+"");
			row.put("stextra",purchasebo.getStextra());
			row.put("particular",purchasebo.getParticular());
			row.put("paymentterms",purchasebo.getPaymentterms());
			row.put("currency", purchasebo.getCurrency());
			row.put("invoiceno", invoiceno);
			row.put("sdate", sdate);
			JSONObject send=new JSONObject(row);
			out.println(send);
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

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
@WebServlet("/InvoiceInsert")
public class InvoiceInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceInsertServlet() {
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
			String invoiceuuid=InvoiceDao.insertInvoice(invoiceno, uuid, sdate, taxvalue, total);
			
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

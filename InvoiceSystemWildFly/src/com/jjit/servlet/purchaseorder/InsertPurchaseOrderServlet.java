package com.jjit.servlet.purchaseorder;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jjit.dao.PurchaseOrderDao;
import com.jjit.dao.UuidGenerator;

/**
 * Servlet implementation class InsertPurchaseOrder
 */
@WebServlet("/InsertPurchaseOrder")
public class InsertPurchaseOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPurchaseOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String data = request.getParameter("data");
			JSONParser parse = new JSONParser();
			Object ob = parse.parse(data);
			JSONObject jb = (JSONObject) ob;
			String vendorid = (String) jb.get("vendorid");
			String clientid = (String) jb.get("clientid");
			String unittype = (String) jb.get("unittype");
			String rate = (String) jb.get("rate");
			String duration = (String) jb.get("duration");
			String sdate = (String) jb.get("sdate");
			sdate=UuidGenerator.changeToDDMMYYYY(sdate);
			String edate = (String) jb.get("edate");
			edate=UuidGenerator.changeToDDMMYYYY(edate);
			String particular = (String) jb.get("particular");
			String pterms = (String) jb.get("pterms");
			String pono = (String) jb.get("pono");
			String postatus = (String) jb.get("postatus");
			String pstatus = (String) jb.get("pstatus");
			String stextra=(String)jb.get("stextra");
			String cdate=(String)jb.get("cdate");
			cdate=UuidGenerator.changeToDDMMYYYY(cdate);
			String currency=(String)jb.get("currency");
			boolean status = PurchaseOrderDao.insertPurchaseOrder(vendorid, clientid,
					unittype, rate, duration, sdate, edate, particular, pterms,
					pono, postatus, pstatus,stextra,cdate,currency);
			out.println("hii");
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

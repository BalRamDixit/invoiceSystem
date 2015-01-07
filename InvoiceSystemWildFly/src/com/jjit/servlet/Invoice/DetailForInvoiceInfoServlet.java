package com.jjit.servlet.Invoice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jjit.dao.InvoiceDao;
import com.jjit.pojo.PurchaseOrderBo;

/**
 * Servlet implementation class DetailForInvoiceInfo
 */
@WebServlet("/DetailForInvoiceInfo")
public class DetailForInvoiceInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailForInvoiceInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String data = request.getParameter("data");
			JSONParser jb=new JSONParser();
			JSONObject jo=(JSONObject)jb.parse(data);
			String pono=(String)jo.get("pono");
			PurchaseOrderBo pobean=new PurchaseOrderBo();
			ResultSet rs=InvoiceDao.getdetailforinvoicebypono(pono);
			HashMap<String,String> row=new HashMap<String,String>();
			if(rs.next())
			{
				String uuid=rs.getString("uuid");
				String rate=rs.getString("rate");
				String duration=rs.getString("duration");
				String stextra=rs.getString("stextra");
				String particular=rs.getString("particular");
				String paymentterms=rs.getString("paymentterms");
				String total=Double.parseDouble(rate)*Double.parseDouble(duration)+"";
				String pototal=new String(total);
				String currency=rs.getString("currency");
				double tax;
				if(stextra.equals("1")){
					tax = 0.1236 * Double.parseDouble(total);
					total = (Double.parseDouble(total) + tax)+"";
				} else {
					Double xtotal = Double.parseDouble(total) / 1.1236;
					tax = Double.parseDouble(total) - xtotal;
					pototal=(Double.parseDouble(total) - tax)+"";
				}
				total=(int)Math.round(Double.parseDouble(total))+"";
				pototal=(int)Math.round(Double.parseDouble(pototal))+"";
				tax=(int)Math.round(tax);
				pobean.setUuid(uuid);
				pobean.setRate(rate);
				pobean.setDuration(duration);
				pobean.setParticular(particular);
				pobean.setPaymentterms(paymentterms);
				pobean.setStextra(stextra);
				pobean.setTax(tax+"");
				pobean.setTotal(total);
				pobean.setActotal(total);
				pobean.setPototal(pototal);
				request.getSession(true).setAttribute("purchaseOrder", pobean);
				row.put("total",total);
				row.put("pototal",pototal);
				row.put("tax",tax+"");
				row.put("stextra",stextra);
				row.put("particular",particular);
				row.put("paymentterms",paymentterms);
				row.put("currency", currency);
			}
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

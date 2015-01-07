package com.jjit.servlet.Invoice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jjit.pojo.ExpenseBo;
import com.jjit.pojo.PurchaseOrderBo;

/**
 * Servlet implementation class UpdateInvoice
 */
@WebServlet("/UpdateInvoice")
public class UpdateInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInvoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			PurchaseOrderBo pobean=(PurchaseOrderBo)request.getSession().getAttribute("purchaseOrder");
			String pototal=pobean.getPototal();
			String tax=pobean.getTax();
			String total=pobean.getTotal();
			String stextra=pobean.getStextra();
			if(stextra.equals("1"))
			{
				stextra="not included";
			}
			double inclusiveExpense=0;
			double exclusiveExpense=0;
			HashMap<Integer,ExpenseBo> expenses=pobean.getExpenses();
			boolean status=false;
			Set<Integer> keys= expenses.keySet();
			for(Integer e:keys)
			{
				if(expenses.get(e)!=null)
				{
					status=true;
					ExpenseBo ex=expenses.get(e);
					if(expenses.get(e).getInclusive())
					{
						inclusiveExpense=inclusiveExpense+Double.parseDouble(expenses.get(e).getCost());
					}
					else
					{
						exclusiveExpense=exclusiveExpense+Double.parseDouble(expenses.get(e).getCost());
					}
				}
			}
			pototal=(Double.parseDouble(pototal)-inclusiveExpense)+"";
			if(!(pototal.equals(pobean.getPototal())))
			{
				if(stextra.equals("not included")){
					tax = 0.1236 * Double.parseDouble(pototal)+"";
					
				} else {
					Double xtotal = Double.parseDouble(pototal) / 1.1236;
					tax = Double.parseDouble(pototal) - xtotal+"";
					pototal=(Double.parseDouble(pototal) - Double.parseDouble(tax))+"";
				}
			}
			total=(Double.parseDouble(pototal)+inclusiveExpense+exclusiveExpense)+Double.parseDouble(tax)+"";
			HashMap<String,String> row=new HashMap<String,String>();
			row.put("total",total);
			row.put("pototal",pototal);
			row.put("tax",tax+"");
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

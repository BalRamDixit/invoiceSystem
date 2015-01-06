package com.jjit.servlet.purchaseorder;

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

import com.jjit.dao.PurchaseOrderDao;
import com.jjit.dao.UuidGenerator;

/**
 * Servlet implementation class PurchaseOrderInfo
 */
@WebServlet("/PurchaseOrderInfo")
public class PurchaseOrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseOrderInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String action=request.getParameter("action");
			if("list".equals(action))
			{
				String var1=request.getParameter("jtSorting");
				String var2=request.getParameter("jtStartIndex");
				String var3=request.getParameter("jtPageSize");
				int recordCount  = PurchaseOrderDao.get_purchaseOrder_count();
				ResultSet rs=PurchaseOrderDao.get_purchaseOrder(var1,var2,var3);
				HashMap<Integer,Object> rows=new HashMap<Integer,Object>();
				int i=0;
				while(rs.next())
				{
					HashMap<String,String> row=new HashMap<String,String>();
					row.put("uuid",rs.getString(1));
					row.put("vendorname",rs.getString(2));
					row.put("clientname",rs.getString(3));
					row.put("createdate",UuidGenerator.changeToDDMMYYYY(rs.getString(4)));
					String unit="Hours";
					if("1".equals(rs.getString(5)))
						unit="Days";
					row.put("xunit",unit);
					row.put("rate",rs.getString(6));
					row.put("duration",rs.getString(7));
					row.put("startdate",UuidGenerator.changeToDDMMYYYY(rs.getString(8)));
					row.put("enddate",UuidGenerator.changeToDDMMYYYY(rs.getString(9)));
					String particular=rs.getString(10);
					row.put("particular",PurchaseOrderDao.getFormatedParticularwithLink(particular, rs.getString(1)));
					if(rs.getString(12).length()>7)
					{
						row.put("purchaseorderno",rs.getString(12));
					}
					else
					{
						String str=rs.getString(12);
						for(int x=0;x<=7-rs.getString(12).length();x++)
						{
							str=str+" ";
						}
						row.put("purchaseorderno",str);
					}
					String pstatus="";
					if("a".equals(rs.getString(13)))
					{
						pstatus="Done";
					}
					else if("i".equals(rs.getString(13)))
					{
						pstatus="In progress";
					}
					else
					{
						pstatus="Not Done";
					}
					row.put("paymentstatus",pstatus);
					rows.put(i,row);
					i++;
				}
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result", "OK");
				jTableResult.put("TotalRecordCount",recordCount);
				jTableResult.put("Records" ,rows);
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
			}
			else if("update".equals(action))
			{
				String uuid=request.getParameter("uuid");
				String unit=request.getParameter("unit");
				String rate=request.getParameter("rate");
				String duration=request.getParameter("duration");
				String createdate=request.getParameter("createdate");
				createdate=UuidGenerator.changeToDDMMYYYY(createdate);
				String startdate=request.getParameter("startdate");
				startdate=UuidGenerator.changeToDDMMYYYY(startdate);
				String enddate=request.getParameter("enddate");
				enddate=UuidGenerator.changeToDDMMYYYY(enddate);
				String particular=request.getParameter("particular");
				String paymentterms=request.getParameter("paymentterms");
				String purchaseorderno=request.getParameter("purchaseorderno");
				boolean result = PurchaseOrderDao.updatepurchaseOrder(uuid, unit, rate, duration, createdate, startdate, enddate, particular, paymentterms, purchaseorderno);
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result","OK");
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
			}
			else if("delete".equals(action))
			{
				String uuid=request.getParameter("uuid");
				boolean result = PurchaseOrderDao.deletepurchaseOrder(uuid);
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result","OK");
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
			}
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

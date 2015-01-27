package com.jjit.servlet.Invoice;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class ExpenseTypeServlet
 */
@WebServlet("/ExpenseType")
public class ExpenseTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseTypeServlet() {
        super();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
				JSONArray ja = new JSONArray();
				Properties proerties=new Properties();
				proerties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("properties\\expenseType.properties"));
				String count=proerties.getProperty("count");
				for(int i=1;i<=Integer.parseInt(count);i++)
				{
					HashMap<String, String> row = new HashMap<String, String>();
					row.put("exptype", proerties.getProperty("expense"+i));
					JSONObject jb = new JSONObject(row);
					ja.add(i-1, jb);
				}
				out.println(ja);
		} catch (Exception e) {

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

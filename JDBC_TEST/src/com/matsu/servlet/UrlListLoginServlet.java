package com.matsu.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.matsu.model.WeightData;

/**
 * Servlet implementation class UrlListLogin
 */
@WebServlet("/url_list_login")
public class UrlListLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UrlListLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	@Resource(lookup="jdbc/weight_pos")
	private DataSource ds;

	private static final String SQL_RESIST =
			"INSERT INTO weight_manager (" +
					"WEIGHT, DATE) " +
					"VALUES (?, ?)";

	private static final String SQL_SHOW =
			"SELECT date, weight FROM weight_manager " +
			"ORDER BY date";


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub



		String str_weight = request.getParameter("weight");
		String str_yyyy = request.getParameter("year");
		String str_mm = request.getParameter("month");
		String str_dd = request.getParameter("day");

		float weight = Float.parseFloat(str_weight);
		int yyyy = Integer.parseInt(str_yyyy);
		int mm = Integer.parseInt(str_mm);
		int dd = Integer.parseInt(str_dd);
		LocalDate ld = LocalDate.of(yyyy, mm, dd);

		System.out.println("DB connection");
		if(registUser(weight,ld)) {
//		if(loginUser(user,pass)) {
			List<WeightData> wdList = getList();
			request.setAttribute("list",wdList);
			request.setAttribute("resist",true);
		}else{
		}
		request.setAttribute("login","true");
		RequestDispatcher rd = request.getRequestDispatcher("/ShowList.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


public List<WeightData>  getList(){

	List<WeightData> wdList = new ArrayList<>();
	try(Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement(SQL_SHOW);
		ResultSet rs = ps.executeQuery()) {
		//PASSが正しいか
//		ps.setString(1, user);
		while (rs.next()) {
			wdList.add(new WeightData(rs));
		}
		System.out.println("result " + true);
	}catch(SQLException e) {
		System.out.println("SQL Exeption");
		System.out.println(e.getMessage());
	}


	return wdList;
}

public boolean registUser(float weight, LocalDate ld) {
	boolean res = false;
	try(Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_RESIST)) {
			//PASSが正しいか
		Date date = Date.valueOf(ld);
		ps.setFloat(1, weight);
		ps.setDate(2, date);

		System.out.println(ps);
		res = ps.executeUpdate() > 0;
		System.out.println("result " + res);
	}catch(SQLException e) {
		System.out.println("SQL Exeption");
		System.out.println(e.getMessage());
		res=false;
		}
	return res;
}

}


package com.matsu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class GetListServlet
 */
@WebServlet("/get_list")
public class GetListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Resource(lookup="jdbc/weight_pos")
	private DataSource ds;


	private static final String SQL_SHOW =
			"SELECT date, weight FROM weight_manager " +
			"ORDER BY date";



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub



		List<WeightData> wdList = getList();;
		request.setAttribute("list",wdList);
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
		System.out.println("Success TO Connect DB");
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

}

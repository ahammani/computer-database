package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerDAOService;

/**
 * Servlet implementation class CDBServlet
 */
public class CDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerDAOService service = ComputerDAOService.INSTANCE;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CDBServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Computer> computers = service.getAll();
		request.setAttribute("computers", computers);

		ServletContext context = this.getServletContext();
		context.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

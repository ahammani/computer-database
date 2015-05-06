package com.excilys.cdb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.utils.Utils;

/**
 * Servlet implementation class DeleteComputerServlet
 */
@WebServlet("/DeleteComputerServlet")
public class DeleteComputerServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteComputerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameter("selection").split(",");
		for (String s : ids) {
			long id = Utils.stringToLong(s, 0);
			if (id != 0)
				computerService.deleteComputer(id);
		}
		response.sendRedirect("DashboardServlet");

	}
}

package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyDAOService;
import com.excilys.cdb.service.ComputerDAOService;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyDAOService companyService = CompanyDAOService.INSTANCE;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Company> companies = companyService.getAll();
		request.setAttribute("companies", companies);

		ServletContext context = this.getServletContext();
		context.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Computer computer = UtilsServlet.postComputer(request, false);
		if (computer != null) {
			ComputerDAOService.INSTANCE.addComputer(computer);
			response.sendRedirect("DashboardServlet");
		} else {
			response.sendRedirect("AddComputerServlet");
		}
	}
}

package com.excilys.cdb.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.mapper.TimeMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyDAOService;
import com.excilys.cdb.service.ComputerDAOService;
import com.excilys.cdb.utils.Utils;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerDAOService computerService = ComputerDAOService.INSTANCE;
	private CompanyDAOService companyService = CompanyDAOService.INSTANCE;

	private boolean checkDate(String s) {
		return Utils.checkDate(s) || (s == "");
	}

	private boolean checkName(String computerName) {
		return computerName != "";
	}

	private boolean checkNumber(String companyId) {
		// TODO Auto-generated method stub
		return Utils.isNumber(companyId) || (companyId == "");
	}

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

		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		if (checkName(computerName) && checkDate(introduced)
				&& checkDate(discontinued) && checkNumber(companyId)) {
			Company company = companyService.getCompany(Long
					.parseLong(companyId));
			LocalDateTime intro = TimeMapper.StringToLocalDateTime(introduced);
			LocalDateTime dis = TimeMapper.StringToLocalDateTime(discontinued);
			Computer computer = new Computer(computerName, intro, dis, company);
			computerService.addComputer(computer);
			response.sendRedirect("DashBoardServlet?reload=true");
		} else {
			response.sendRedirect("AddComputerServlet");
		}
	}
}

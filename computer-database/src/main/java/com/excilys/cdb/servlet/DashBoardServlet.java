package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import page.Page;

import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.service.ComputerDAOService;

/**
 * Servlet implementation class CDBServlet
 */
@WebServlet("/DashboardServlet")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ComputerDAOService computerService = ComputerDAOService.INSTANCE;
	private static final List<String> fields = Arrays.asList("c_name",
			"introduced", "discontinued", "name");
	private static final List<String> orders = Arrays.asList("asc", "desc");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("page");
		String lim = request.getParameter("limit");
		String search = request.getParameter("search");
		String field = request.getParameter("field_order");
		String order = request.getParameter("order");

		Page pages = new Page(p, lim);

		if (field == null || !fields.contains(field)) {
			field = "";
		}
		if (order == null || !orders.contains(order.toLowerCase())) {
			order = "ASC";
		}
		if (search == null || search.isEmpty()) {
			pages.setMaxComputers(computerService.count());
			pages.setComputers(DTOMapper.toDTOList(computerService.getAll(
					pages, field, order)));
		} else {
			pages.setMaxComputers(computerService.count(search));
			pages.setComputers(DTOMapper.toDTOList(computerService.getAll(
					search, pages, field, order)));
			request.setAttribute("search", search);
		}

		pages.setMaxPages(pages.getMaxComputers());
		request.setAttribute("pages", pages);
		ServletContext context = this.getServletContext();
		System.out.println(pages);
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

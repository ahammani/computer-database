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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class CDBServlet
 */
@WebServlet("/DashboardServlet")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;// ComputerService.INSTANCE;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoardServlet() {
		super();
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

		Page pages = new Page(p, lim, search, field, order);
		pages.validate();

		if (pages.getSearch().isEmpty()) {
			pages.setMaxComputers(computerService.count());
		} else {
			pages.setMaxComputers(computerService.count(search));
			request.setAttribute("search", search);
		}
		pages.setComputers(DTOMapper.toDTOList(computerService.getAll(pages)));
		pages.setMaxPages(pages.getMaxComputers());
		request.setAttribute("pages", pages);
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
	}

}

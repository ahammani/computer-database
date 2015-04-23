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

import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.service.ComputerDAOService;
import com.excilys.cdb.servlet.dto.ComputerDTO;
import com.excilys.cdb.utils.Utils;

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
		int page = Utils.StringToInt(p, 1);
		int limit = Utils.StringToInt(lim, 3);
		int maxComputers;
		int offset = (page - 1) * limit;
		offset = (offset < 0) ? 0 : offset;
		List<ComputerDTO> computers;

		if (field == null || !fields.contains(field)) {
			field = "";
		}
		if (order == null || !orders.contains(order.toLowerCase())) {
			order = "ASC";
		}
		if (search == null || search.isEmpty()) {
			maxComputers = computerService.count();
			computers = DTOMapper.toDTOList(computerService.getAll(offset,
					limit, field, order));
		} else {
			maxComputers = computerService.count(search);
			computers = DTOMapper.toDTOList(computerService.getAll(search,
					offset, limit, field, order));
			request.setAttribute("search", search);
		}
		int maxPages = (maxComputers % limit == 0) ? (maxComputers / limit)
				: (maxComputers / limit) + 1;
		page = (page > maxPages) ? (maxPages - 1) : page;
		request.setAttribute("maxPages", maxPages);
		request.setAttribute("maxComputers", maxComputers);
		request.setAttribute("computers", computers);
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
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

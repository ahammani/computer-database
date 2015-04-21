package com.excilys.cdb.servlet;

import java.io.IOException;
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
@WebServlet("/DashBoardServlet")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ComputerDAOService computerService = ComputerDAOService.INSTANCE;

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
		int page = Utils.StringToInt(p, 1);
		int limit = Utils.StringToInt(lim, 3);
		int maxComputers = computerService.count();
		int maxPages = (maxComputers % limit == 0) ? (maxComputers / limit)
				: (maxComputers / limit) + 1;
		int offset = (page - 1) * limit;
		offset = (offset < 0) ? 0 : offset;
		page = (page > maxPages) ? (maxPages - 1) : page;
		List<ComputerDTO> computers = DTOMapper.toDTOList(computerService
				.getAll(offset, limit));
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

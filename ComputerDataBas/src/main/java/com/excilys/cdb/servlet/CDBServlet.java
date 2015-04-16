package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerDAOService;
import com.excilys.cdb.servlet.dto.ComputerDTO;

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

		List<ComputerDTO> computers = new ArrayList<>();
		for (Computer c : service.getAll()) {
			computers.add(DTOMapper.toDTO(c));
		}
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

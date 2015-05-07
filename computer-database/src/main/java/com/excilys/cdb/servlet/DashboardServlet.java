package com.excilys.cdb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class CDBServlet
 */
@RequestMapping({ "/dashboard", "/" })
@Controller
public class DashboardServlet {
	@Autowired
	private ComputerService computerService;

	public DashboardServlet() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "lim", required = false) String lim,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "field", required = false) String field,
			@RequestParam(value = "order", required = false) String order,
			Model model) {

		Page pages = new Page(page, lim, search, field, order);
		pages.validate();

		if (pages.getSearch().isEmpty()) {
			pages.setMaxComputers(computerService.count());
		} else {
			pages.setMaxComputers(computerService.count(search));
			model.addAttribute("search", search);
		}
		pages.setComputers(DTOMapper.toDTOList(computerService.getAll(pages)));
		pages.setMaxPages(pages.getMaxComputers());
		model.addAttribute("pages", pages);
		return "dashboard";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}

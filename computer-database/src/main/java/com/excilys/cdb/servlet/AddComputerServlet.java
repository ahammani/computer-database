package com.excilys.cdb.servlet;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class AddComputer
 */

@RequestMapping("/addComputer")
@Controller
public class AddComputerServlet {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	public AddComputerServlet() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(Model model) {
		List<Company> companies = companyService.getAll();
		model.addAttribute("companies", companies);
		return "redirect:addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, Model model) {
		Computer computer = UtilsServlet.toComputer(request, companyService);
		if (computer != null) {
			computerService.addComputer(computer);
			return "redirect:dashboard";
		} else {
			return "redirect:addComputer";
		}
	}
}

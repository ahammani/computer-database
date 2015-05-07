package com.excilys.cdb.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.servlet.dto.ComputerDTO;
import com.excilys.cdb.utils.Utils;

@RequestMapping("editComputer")
@Controller
public class EditComputerServlet {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	public EditComputerServlet() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@RequestParam(value = "id") String sid, Model model) {
		List<Company> companies = companyService.getAll();
		model.addAttribute("companies", companies);

		long id = Utils.stringToLong(sid, 1);
		ComputerDTO computer = DTOMapper.toDTO(computerService.getComputer(id));
		if (computer != null) {
			model.addAttribute("computer", computer);
			return "editComputer";
		} else {
			return "redirect:dashboard";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request) {
		Computer computer = UtilsServlet.toComputer(request, companyService);
		if (computer != null) {
			computerService.updateComputer(computer);
		}
		return "redirect:dashboard";
	}

}

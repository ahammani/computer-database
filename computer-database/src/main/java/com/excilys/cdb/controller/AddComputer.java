package com.excilys.cdb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.cdb.controller.dto.ComputerDTO;
import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class AddComputer
 */

@RequestMapping("/addComputer")
@Controller
public class AddComputer {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private DTOMapper dtoMapper;

	public AddComputer() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(Model model) {
		List<Company> companies = companyService.getAll();
		model.addAttribute("companies", companies);
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@Valid @ModelAttribute ComputerDTO computerDTO,
			Model model) {
		Computer computer = dtoMapper.toComputer(computerDTO, companyService);
		if (computer != null) {
			computerService.addComputer(computer);
			return "redirect:dashboard";
		} else {
			return "redirect:addComputer";
		}
	}
}

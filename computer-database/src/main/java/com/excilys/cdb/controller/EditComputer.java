package com.excilys.cdb.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.controller.dto.ComputerDTO;
import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.utils.Utils;

@RequestMapping("editComputer")
@Controller
public class EditComputer {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DTOMapper dtoMapper;
	private static Logger logger = LoggerFactory.getLogger(EditComputer.class);

	public EditComputer() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@RequestParam(value = "id") String sid, Model model) {
		List<Company> companies = companyService.getAll();
		model.addAttribute("companies", companies);

		long id = Utils.stringToLong(sid, 1);
		ComputerDTO computer = dtoMapper.toDTO(computerService.getComputer(id));
		if (computer != null) {
			model.addAttribute("computer", computer);
			return "editComputer";
		} else {
			return "redirect:dashboard";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			@Valid @ModelAttribute("editComputer") ComputerDTO computerDTO,
			final BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.error("DTO Valdation failed !");
		} else {
			Computer computer = dtoMapper.toComputer(computerDTO,
					companyService);
			if (computer != null) {
				computerService.updateComputer(computer);
			}
		}
		return "redirect:dashboard";
	}

}

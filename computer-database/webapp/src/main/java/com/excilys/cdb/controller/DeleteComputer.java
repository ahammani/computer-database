package com.excilys.cdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.page.Utils;
import com.excilys.cdb.service.ComputerService;

@RequestMapping("/deleteComputer")
@Controller
public class DeleteComputer {
	@Autowired
	private ComputerService computerService;

	public DeleteComputer() {
		super();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam(value = "selection") String select) {
		String[] ids = select.split(",");
		for (String s : ids) {
			long id = Utils.stringToLong(s, 0);
			if (id != 0)
				computerService.deleteComputer(id);
		}
		return "redirect:dashboard";

	}
}

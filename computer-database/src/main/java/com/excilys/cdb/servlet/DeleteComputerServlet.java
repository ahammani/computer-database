package com.excilys.cdb.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.utils.Utils;

@RequestMapping("deleteComputer")
@Controller
public class DeleteComputerServlet {
	@Autowired
	private ComputerService computerService;

	public DeleteComputerServlet() {
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

package com.excilys.cdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.ComputerService;

@RequestMapping({ "/dashboard", "/" })
@Controller
public class Dashboard {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private DTOMapper dtoMapper;

	public Dashboard() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String lim,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "field_order", required = false) String field,
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
		pages.setComputers(dtoMapper.toDTOList(computerService.getAll(pages)));
		pages.setMaxPages(pages.getMaxComputers());
		model.addAttribute("pages", pages);
		return "dashboard";
	}

}

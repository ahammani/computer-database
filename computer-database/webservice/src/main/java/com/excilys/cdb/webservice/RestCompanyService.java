package com.excilys.cdb.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.ICompanyService;

@RestController
@RequestMapping("rest/company")
public class RestCompanyService implements IRestService<Company> {
	@Autowired
	private ICompanyService service;

	@RequestMapping(method = RequestMethod.GET, value = "findAll")
	public List<Company> findAll() {
		return service.getAll();
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Company find(@PathVariable("id") long id) {
		return service.getCompany(id);
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE, value = "delete/{id}")
	public void delete(@PathVariable("id") long id) {
		service.deleteCompany(id);

	}

}

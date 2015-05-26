package com.excilys.cdb.webservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

@RestController
@RequestMapping("rest/company")
public class RestCompanyService implements IRestService<Company> {
	@Autowired
	private CompanyService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("findAll")
	public List<Company> findAll() {
		return service.getAll();
	}

	@Override
	@GET
	@RequestMapping("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Company find(@PathVariable("id") long id) {
		return service.getCompany(id);
	}

	@Override
	@DELETE
	@RequestMapping("delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void delete(@PathVariable("id") long id) {
		service.deleteCompany(id);

	}

}

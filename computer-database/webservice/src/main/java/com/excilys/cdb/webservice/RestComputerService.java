package com.excilys.cdb.webservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

@RestController
@RequestMapping("rest/computer")
public class RestComputerService implements IRestService<Computer> {
	@Autowired
	private ComputerService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("findAll")
	public List<Computer> findAll() {
		return service.getAll();
	}

	@Override
	@GET
	@RequestMapping("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Computer find(@PathVariable("id") long id) {
		return service.getComputer(id);
	}

	@Override
	@DELETE
	@RequestMapping("delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void delete(@PathVariable("id") long id) {
		service.deleteComputer(id);

	}

	@Override
	@POST
	@RequestMapping("update/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void update(Computer computer) {
		service.updateComputer(computer);

	}

	@Override
	@POST
	@RequestMapping("create")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void create(Computer computer) {
		service.addComputer(computer);

	}

}

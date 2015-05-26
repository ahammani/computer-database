package com.excilys.cdb.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

@RestController
@RequestMapping("computer")
public class RestComputerService {
	@Autowired
	private ComputerService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("getAll")
	public List<Computer> getAll() {
		return service.getAll();
	}

	@GET
	@RequestMapping("{id}")
	public Computer find(@PathParam("id") Long id) {
		return service.getComputer(id);

	}
}

package com.excilys.cdb.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.service.IComputerService;

@RestController
@RequestMapping("rest/computer")
public class RestComputerService implements IRestService<ComputerDTO> {
	@Autowired
	private IComputerService service;
	@Autowired
	private DTOMapper mapper;

	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping(method = RequestMethod.GET, value = "findAll")
	public List<ComputerDTO> findAll() {
		return mapper.toDTOList(service.getAll());
	}

	@Override
	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ComputerDTO find(@PathVariable("id") long id) {
		return mapper.toDTO(service.getComputer(id));
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE, value = "delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public void delete(@PathVariable("id") long id) {
		service.deleteComputer(id);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "update")
	@Produces({ MediaType.APPLICATION_JSON })
	public void update(@RequestBody ComputerDTO computer) {
		service.updateComputer(mapper.toComputer(computer));
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "create")
	@Produces({ MediaType.APPLICATION_JSON })
	public ComputerDTO create(@RequestBody ComputerDTO computer) {
		service.addComputer(mapper.toComputer(computer));
		return computer;
	}

}

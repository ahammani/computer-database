package com.excilys.cdb.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.IComputerService;

@RestController
@RequestMapping("rest/computer")
public class RestComputerService implements IRestService<ComputerDTO> {
	@Autowired
	private IComputerService service;
	@Autowired
	private DTOMapper mapper;

	@RequestMapping(method = RequestMethod.GET, value = "findAll")
	public List<ComputerDTO> findAll() {
		return mapper.toDTOList(service.getAll());
	}

	@RequestMapping(method = RequestMethod.POST, value = "findAllPage")
	public List<ComputerDTO> findAll(@RequestBody Page p) {
		return mapper.toDTOList(service.getAll(p));
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ComputerDTO find(@PathVariable("id") long id) {
		return mapper.toDTO(service.getComputer(id));
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE, value = "delete/{id}")
	public void delete(@PathVariable("id") long id) {
		service.deleteComputer(id);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "update")
	public void update(@RequestBody ComputerDTO computer) {
		service.updateComputer(mapper.toComputer(computer));
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "create")
	public ComputerDTO create(@RequestBody ComputerDTO computer) {
		service.addComputer(mapper.toComputer(computer));
		return computer;
	}

}

package com.excilys.cdb.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

@Component
public class ClientService implements IClientService {
	private static final String ROOT = "http://localhost:8080/webservice/rest";
	private static final String COMPANY = "/company";
	private static final String COMPUTER = "/computer";
	private static final String DELETE = "/delete";
	private static final String CREATE = "/create";
	private static final String UPDATE = "/update";
	Logger logger = LoggerFactory.getLogger(ClientService.class);
	@Autowired
	DTOMapper mapper = new DTOMapper();

	@Override
	public List<Computer> findAllComputer() {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		WebTarget webTarget = client.target(ROOT + COMPUTER + "/findAll");
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		logger.debug("RESPONSE " + response.toString());
		List<ComputerDTO> computers = response
				.readEntity(new GenericType<List<ComputerDTO>>() {
				});
		return mapper.toComputerList(computers);
	}

	@Override
	public List<Company> findAllCompany() {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		WebTarget webTarget = client.target(ROOT + COMPANY + "/findAll");
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		logger.debug("RESPONSE " + response.toString());
		return response.readEntity(new GenericType<List<Company>>() {
		});
	}

	@Override
	public Computer findComputer(Long id) {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		ComputerDTO computer = client.target(ROOT + COMPUTER + "/" + id)
				.request().get().readEntity(ComputerDTO.class);
		return mapper.toComputer(computer);
	}

	@Override
	public Computer createComputer(Computer c) {
		logger.debug("{}", c);
		ComputerDTO comp = mapper.toDTO(c);
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		client.target(ROOT + COMPUTER + CREATE).request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(comp, MediaType.APPLICATION_JSON));
		return c;
	}

	@Override
	public void updateComputer(Computer c) {
		ComputerDTO comp = mapper.toDTO(c);
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		client.target(ROOT + COMPUTER + UPDATE).request()
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(comp, MediaType.APPLICATION_JSON));

	}

	@Override
	public void deleteComputer(long id) {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		client.target(ROOT + COMPUTER + DELETE + "/" + id).request().delete();
	}

	@Override
	public void deleteCompany(long id) {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		client.target(ROOT + COMPANY + DELETE + "/" + id).request().delete();

	}

	@Override
	public List<Computer> findAllComputer(Page p) {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		Response response = client.target(ROOT + COMPUTER + "/findAllPage")
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(p, MediaType.APPLICATION_JSON));
		List<ComputerDTO> computers = response
				.readEntity(new GenericType<List<ComputerDTO>>() {
				});
		return mapper.toComputerList(computers);
	}

}

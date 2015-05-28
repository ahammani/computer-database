package com.excilys.cdb.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.google.common.net.MediaType;

@Service
public class ClientService2 implements IClientService2 {
	private static final String ROOT = "http://localhost:8080/webservice/rest/";
	private static final String COMPANY = "company";
	private static final String COMPUTER = "computer";
	private static final String DELETE = "delete";

	@Override
	public List<ComputerDTO> findAllComputer() {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		WebTarget webTarget = client.target(ROOT + COMPUTER + "/findAll");
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		System.out.println("RESPONSE " + response.toString());
		return response.readEntity(new GenericType<List<ComputerDTO>>() {
		});
	}

	@Override
	public List<Company> findAllCompany() {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		WebTarget webTarget = client.target(ROOT + COMPANY + "/findAll");
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		System.out.println("RESPONSE " + response.toString());
		return response.readEntity(new GenericType<List<Company>>() {
		});
	}

	@Override
	public ComputerDTO findComputer(Long id) {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		ComputerDTO computer = client.target(ROOT + COMPUTER + "/" + id)
				.request().get().readEntity(ComputerDTO.class);
		return computer;
	}

	@Override
	public void createComputer(ComputerDTO c) {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		// client.target(ROOT).request().post(Entity.entity()))

	}

	@Override
	public void updateComputer(ComputerDTO c) {
		// TODO Auto-generated method stub

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

}

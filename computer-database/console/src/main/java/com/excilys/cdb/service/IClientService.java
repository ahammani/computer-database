package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public interface IClientService {
	List<Computer> findAllComputer();

	List<Company> findAllCompany();

	Computer findComputer(Long id);

	Computer createComputer(Computer c);

	void updateComputer(Computer c);

	void deleteComputer(long id);

	void deleteCompany(long id);
}

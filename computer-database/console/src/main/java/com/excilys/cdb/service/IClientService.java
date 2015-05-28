package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

public interface IClientService {
	List<Computer> findAllComputer();

	List<Computer> findAllComputer(Page p);

	List<Company> findAllCompany();

	Computer findComputer(Long id);

	Computer createComputer(Computer c);

	void updateComputer(Computer c);

	void deleteComputer(long id);

	void deleteCompany(long id);
}

package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public interface IClientService2 {
	List<ComputerDTO> findAllComputer();

	List<Company> findAllCompany();

	ComputerDTO findComputer(Long id);

	void createComputer(ComputerDTO c);

	void updateComputer(ComputerDTO c);

	void deleteComputer(long id);

	void deleteCompany(long id);
}

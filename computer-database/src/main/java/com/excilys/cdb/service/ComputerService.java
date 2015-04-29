package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

public enum ComputerService implements IComputerService {
	INSTANCE;

	public List<Computer> getAll() {
		return ComputerDAO.INSTANCE.findAll();
	}

	public List<Computer> getAll(Page page) {
		if (page.getSearch().equals("")) {
			return ComputerDAO.INSTANCE.findAll(page.getOffset(),
					page.getLimit(), page.getField_order(), page.getOrder());
		} else {
			return ComputerDAO.INSTANCE.findAll(page.getSearch(),
					page.getOffset(), page.getLimit(), page.getField_order(),
					page.getOrder());
		}

	}

	public Computer getComputer(long id) {
		return ComputerDAO.INSTANCE.find(id);
	}

	public int addComputer(Computer c) {
		return ComputerDAO.INSTANCE.create(c);
	}

	public void updateComputer(Computer c) {
		ComputerDAO.INSTANCE.update(c);
	}

	public void deleteComputer(long id) {
		ComputerDAO.INSTANCE.delete(id);
	}

	public int count() {
		return ComputerDAO.INSTANCE.count();
	}

	public int count(String search) {
		if (search == null || search.isEmpty())
			return 0;
		else
			return ComputerDAO.INSTANCE.count(search);
	}
}

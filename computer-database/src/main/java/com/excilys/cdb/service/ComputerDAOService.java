package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Computer;

public enum ComputerDAOService {
	INSTANCE;

	public List<Computer> getAll() {
		return ComputerDAO.INSTANCE.findAll();
	}

	public List<Computer> getAll(int offset, int limit) {
		return ComputerDAO.INSTANCE.findAll(offset, limit);
	}

	public List<Computer> getAll(String search, int offset, int limit) {
		return ComputerDAO.INSTANCE.findAll(search, offset, limit);
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
}

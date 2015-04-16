package main.java.com.excilys.cdb.service;

import java.util.List;

import main.java.com.excilys.cdb.dao.ComputerDAO;
import main.java.com.excilys.cdb.model.Computer;

public enum ComputerDAOService {
	INSTANCE;

	public List<Computer> getAll() {
		return ComputerDAO.INSTANCE.findAll();
	}

	public List<Computer> getAll(int offset, int limit) {
		return ComputerDAO.INSTANCE.findAll(offset, limit);
	}

	public Computer getComputer(long id) {
		return ComputerDAO.INSTANCE.find(id);
	}

	public void addComputer(Computer c) {
		ComputerDAO.INSTANCE.create(c);
	}

	public void updateComputer(Computer c) {
		ComputerDAO.INSTANCE.update(c);
	}

	public void deleteComputer(long id) {
		ComputerDAO.INSTANCE.delete(id);
	}
}

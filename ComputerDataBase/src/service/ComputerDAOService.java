package service;

import java.util.List;

import model.Computer;
import dao.ComputerDAO;

public class ComputerDAOService {
	private ComputerDAO computers = new ComputerDAO();

	public ComputerDAO getComputers() {
		return computers;
	}

	public void setComputers(ComputerDAO computers) {
		this.computers = computers;
	}

	public List<Computer> getAll() {
		return computers.findAll();
	}

	public Computer getComputer(long id) {
		return computers.find(id);
	}

}

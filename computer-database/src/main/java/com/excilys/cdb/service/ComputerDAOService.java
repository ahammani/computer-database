package com.excilys.cdb.service;

import java.util.List;

import page.Page;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Computer;

public enum ComputerDAOService {
	INSTANCE;

	public List<Computer> getAll() {
		return ComputerDAO.INSTANCE.findAll();
	}

	public List<Computer> getAll(Page pages, String field_order, String order) {
		return ComputerDAO.INSTANCE.findAll(pages.getOffset(),
				pages.getLimit(), field_order, order);
	}

	public List<Computer> getAll(String search, Page pages, String field_order,
			String order) {
		return ComputerDAO.INSTANCE.findAll(search, pages.getOffset(),
				pages.getLimit(), field_order, order);
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

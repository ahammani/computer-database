package com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

@Service
public class ComputerService implements IComputerService {
	@Autowired
	private ComputerDAO INSTANCE;

	public ComputerDAO getINSTANCE() {
		return INSTANCE;
	}

	public void setINSTANCE(ComputerDAO iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public List<Computer> getAll() {
		return INSTANCE.findAll();
	}

	public List<Computer> getAll(Page page) {
		if (page.getSearch().equals("")) {
			return INSTANCE.findAll(page.getOffset(), page.getLimit(),
					page.getField_order(), page.getOrder());
		} else {
			return INSTANCE.findAll(page.getSearch(), page.getOffset(),
					page.getLimit(), page.getField_order(), page.getOrder());
		}

	}

	public Computer getComputer(long id) {
		return INSTANCE.find(id);
	}

	public int addComputer(Computer c) {
		return INSTANCE.create(c);
	}

	public void updateComputer(Computer c) {
		INSTANCE.update(c);
	}

	public void deleteComputer(long id) {
		INSTANCE.delete(id);
	}

	public int count() {
		return INSTANCE.count();
	}

	public int count(String search) {
		if (search == null || search.isEmpty())
			return 0;
		else
			return INSTANCE.count(search);
	}
}

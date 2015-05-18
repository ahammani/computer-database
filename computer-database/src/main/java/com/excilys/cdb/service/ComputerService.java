package com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = true)
	public List<Computer> getAll() {
		return INSTANCE.findAll();
	}

	@Transactional(readOnly = true)
	public List<Computer> getAll(Page page) {
		if (page.getSearch().equals("")) {
			return INSTANCE.findAll(page.getOffset(), page.getLimit(),
					page.getField_order(), page.getOrder());
		} else {
			return INSTANCE.findAll(page.getSearch(), page.getOffset(),
					page.getLimit(), page.getField_order(), page.getOrder());
		}

	}

	@Transactional(readOnly = true)
	public Computer getComputer(long id) {
		return INSTANCE.find(id);
	}

	@Transactional
	public void addComputer(Computer c) {
		INSTANCE.create(c);
	}

	@Transactional
	public void updateComputer(Computer c) {
		INSTANCE.update(c);
	}

	@Transactional
	public void deleteComputer(long id) {
		INSTANCE.delete(id);
	}

	@Transactional(readOnly = true)
	public int count() {
		return INSTANCE.count();
	}

	@Transactional(readOnly = true)
	public int count(String search) {
		if (search == null || search.isEmpty())
			return 0;
		else
			return INSTANCE.count(search);
	}
}

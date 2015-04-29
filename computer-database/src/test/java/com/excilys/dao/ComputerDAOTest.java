package com.excilys.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.*;
import com.excilys.cdb.model.Computer.ComputerBuilder;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.utils.ExecuteScript;

public class ComputerDAOTest {
	List<Computer> expectedList;
	Computer comp1;
	Computer comp2;
	Computer comp3;
	Company c1 = new Company(1, "Apple Inc.");
	Company c2 = new Company(2, "Thinking Machines");
	Company c3 = new Company(0, null);
	ComputerService computers = ComputerService.INSTANCE;

	@Before
	public void prepare() {
		ExecuteScript.execute();
		expectedList = new ArrayList<Computer>();

		LocalDate t1 = LocalDate.of(1991, 03, 03);
		LocalDate t2 = LocalDate.of(1991, 04, 03);
		LocalDate t3 = LocalDate.of(1991, 04, 04);
		comp1 = new ComputerBuilder("MacBook Pro 15.4 inch").id(1).company(c1)
				.build();
		comp2 = new ComputerBuilder("CM-200").id(2).introduced(t1)
				.discontinued(t2).company(c2).build();
		comp3 = new ComputerBuilder("CM-5e").id(3).discontinued(t3).company(c3)
				.build();
		expectedList.add(comp1);
		expectedList.add(comp2);
		expectedList.add(comp3);
	}

	@Test
	public void testFind() {
		Computer expected = comp1;
		Computer actual = computers.getComputer(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testFindAll() {
		boolean isList = (ComputerService.INSTANCE.getAll()) instanceof List<?>;
		assertTrue(isList);
		List<Computer> l = (ComputerService.INSTANCE.getAll());
		assertEquals(3, l.size());
		assertTrue(expectedList.equals(l));
	}

	@Test
	public void testCreate() {
		Computer expected = new ComputerBuilder("test").id(4)
				.discontinued(LocalDate.of(2000, 01, 02)).company(c1).build();
		int id = computers.addComputer(expected);
		Computer actual = computers.getComputer(id);
		assertFalse(actual.equals(null));
		assertEquals(expected, actual);
	}

}

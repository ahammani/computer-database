package com.excilys.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.*;
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
		comp1 = new Computer("MacBook Pro 15.4 inch", null, null, c1, 1);
		comp2 = new Computer("CM-200", t1, t2, c2, 2);
		comp3 = new Computer("CM-5e", null, t3, c3, 3);
		expectedList.add(comp1);
		expectedList.add(comp2);
		expectedList.add(comp3);
	}

	@Test
	public void testFind() {
		ExecuteScript.execute();
		Computer expected = comp1;
		Computer actual = computers.getComputer(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testFindAll() {
		ExecuteScript.execute();
		boolean isList = (ComputerService.INSTANCE.getAll()) instanceof List<?>;
		assertTrue(isList);
		List<Computer> l = (ComputerService.INSTANCE.getAll());
		assertEquals(3, l.size());
		assertTrue(expectedList.equals(l));
	}

	@Test
	public void testCreate() {
		ExecuteScript.execute();
		Computer expected = new Computer("test", null, LocalDate.of(2000, 01,
				02), c1, 4);
		int id = computers.addComputer(expected);
		Computer actual = computers.getComputer(id);
		assertFalse(actual.equals(null));
		assertEquals(expected, actual);
	}

}

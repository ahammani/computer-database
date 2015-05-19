package com.excilys.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.ExecuteScript;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class ComputerDAOTest {
	List<Computer> expectedList;
	Computer comp1;
	Computer comp2;
	Computer comp3;
	Company c1 = new Company(1, "Apple Inc.");
	Company c2 = new Company(2, "Thinking Machines");
	Company c3 = new Company(0, null);
	@Autowired
	ComputerDAO computers;

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
		if (computers == null)
			System.out.println("NULL");
		else
			System.out.println("PAS NULL");
		Computer actual = computers.find(1);
		if (actual == null)
			System.out.println("NULL");
		else
			System.out.println("PAS NULL");

		assertEquals(expected, actual);
	}

	@Test
	public void testFindAll() {
		boolean isList = (computers.findAll()) instanceof List<?>;
		assertTrue(isList);
		List<Computer> l = (computers.findAll());
		assertEquals(3, l.size());
		assertTrue(expectedList.equals(l));
	}

	@Test
	public void testCreate() {
		Computer expected = new ComputerBuilder("test").id(4)
				.discontinued(LocalDate.of(2000, 01, 02)).company(c1).build();
		computers.create(expected);
		Computer actual = computers.find(4);
		assertFalse(actual.equals(null));
		assertEquals(expected, actual);
	}

}

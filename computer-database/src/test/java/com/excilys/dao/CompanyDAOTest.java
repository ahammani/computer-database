package com.excilys.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.utils.ExecuteScript;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class CompanyDAOTest {
	List<Company> expectedList;
	@Autowired
	CompanyDAO companies;

	@Before
	public void prepare() {
		ExecuteScript.execute();
		expectedList = new ArrayList<Company>();
		expectedList.add(new Company(1, "Apple Inc."));
		expectedList.add(new Company(3, "RCA"));
		expectedList.add(new Company(2, "Thinking Machines"));
	}

	@Test
	public void testFindAll() {

		boolean isList = (companies.findAll()) instanceof List<?>;
		assertTrue(isList);
		List<Company> l = (companies.findAll());
		assertEquals(3, l.size());
		assertTrue(expectedList.equals(l));
		assertFalse(l.equals(null));
	}

}

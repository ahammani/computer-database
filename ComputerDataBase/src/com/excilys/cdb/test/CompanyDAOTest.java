package com.excilys.cdb.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyDAOService;

public class CompanyDAOTest {

	@Test
	public void testFindAll() {
		List<Company> expectedList = new ArrayList<Company>();
		expectedList.add(new Company(1, "Apple Inc."));
		expectedList.add(new Company(3, "RCA"));
		expectedList.add(new Company(2, "Thinking Machines"));
		boolean isList = (CompanyDAOService.INSTANCE.getAll()) instanceof List<?>;
		assertTrue(isList);
		List<Company> l = (CompanyDAOService.INSTANCE.getAll());
		assertEquals(3, l.size());
		assertTrue(expectedList.equals(l));
	}

}

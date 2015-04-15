package com.excilys.cdb.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.excilys.cdb.service.CompanyDAOService;
import com.excilys.cdb.utils.ExecuteScript;

public class CompanyDAOTest {

	@Test
	public void testFindAll() {
		boolean isList = (CompanyDAOService.INSTANCE.getAll()) instanceof List<?>;
		int nbComputers = (CompanyDAOService.INSTANCE.getAll()).size();
		assertEquals(5, nbComputers);
		ExecuteScript.execute();
	}

}

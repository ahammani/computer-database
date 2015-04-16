

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyDAOService;
import com.excilys.cdb.utils.ExecuteScript;

public class CompanyDAOTest {
	List<Company> expectedList;

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

		boolean isList = (CompanyDAOService.INSTANCE.getAll()) instanceof List<?>;
		assertTrue(isList);
		List<Company> l = (CompanyDAOService.INSTANCE.getAll());
		assertEquals(3, l.size());
		assertTrue(expectedList.equals(l));
		assertFalse(l.equals(null));
	}

}

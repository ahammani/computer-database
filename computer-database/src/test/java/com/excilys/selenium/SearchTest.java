package com.excilys.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.utils.ExecuteScript;

public class SearchTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	Company c1 = new Company(1, "Apple Inc.");
	Company c2 = new Company(2, "Thinking Machines");
	LocalDate t1 = LocalDate.of(1991, 03, 03);
	LocalDate t2 = LocalDate.of(1991, 04, 03);
	LocalDate t3 = LocalDate.of(1991, 04, 04);
	Computer comp1 = new ComputerBuilder("MacBook Pro 15.4 inch").id(1)
			.company(c1).build();
	Computer comp2 = new ComputerBuilder("CM-200").id(2).introduced(t1)
			.discontinued(t2).company(c2).build();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void SearchByName() throws Exception {
		ExecuteScript.execute();
		driver.get(baseUrl + "/computer-database/DashboardServlet");
		driver.findElement(By.id("searchbox")).clear();
		driver.findElement(By.id("searchbox")).sendKeys("Mac");
		driver.findElement(By.id("searchsubmit")).click();
		assertTrue((new ComputerService()).count("Mac") == 2);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}

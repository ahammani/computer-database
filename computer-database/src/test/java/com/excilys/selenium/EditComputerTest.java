package com.excilys.selenium;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.utils.ExecuteScript;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class EditComputerTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	Company c = new Company(3, "RCA");
	LocalDate t = LocalDate.of(1991, 04, 04);
	Computer expected = new ComputerBuilder("Test").id(3).discontinued(t)
			.company(c).build();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testEditComputer() throws Exception {
		ExecuteScript.execute();
		driver.get(baseUrl + "/computer-database/DashboardServlet");
		driver.findElement(By.linkText("CM-5e")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("Test");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("RCA");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Computer found = (new ComputerService()).getComputer(3);
		assertEquals(expected, found);
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

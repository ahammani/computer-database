package com.excilys.selenium;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerDAOService;
import com.excilys.cdb.utils.ExecuteScript;

public class EditComputerTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	Company c = new Company(3, "RCA");
	LocalDate t = LocalDate.of(1991, 04, 04);
	Computer expected = new Computer("Test", null, t, c, 3);

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
		Computer found = ComputerDAOService.INSTANCE.getComputer(3);
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

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}

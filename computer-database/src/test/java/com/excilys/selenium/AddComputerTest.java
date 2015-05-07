package com.excilys.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class AddComputerTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private String previous;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testEmptyAddComputer() throws Exception {
		driver.get(baseUrl + "/computer-database/DashboardServlet");
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	}

	@Test
	public void testJustNameAddComputer() throws Exception {
		driver.get(baseUrl + "/computer-database/AddComputerServlet");
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("Test2");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	}

	@Test
	public void testFullAddComputer() throws Exception {
		driver.get(baseUrl + "/computer-database/DashboardServlet");
		previous = driver.findElement(By.id("homeTitle")).getText().split(" ")[0];

		driver.get(baseUrl + "/computer-database/AddComputerServlet");
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("Test3");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("2015-01-01");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("2020-02-02");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		driver.get(baseUrl + "/computer-database/DashboardServlet");
		String next = driver.findElement(By.id("homeTitle")).getText()
				.split(" ")[0];
		assertEquals(Integer.parseInt(previous) + 1, Integer.parseInt(next));

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

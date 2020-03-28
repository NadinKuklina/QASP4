package ru.ola.spqa;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class Login {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    String baseUrl = "https://dev.sp4.sitepokupok.ru/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLogin() throws Exception {
    driver.get("https://dev.sp4.sitepokupok.ru/");
    driver.findElement(By.xpath("//li[4]/a/span")).click();
    driver.findElement(By.id("UserLoginForm_login")).click();
    driver.findElement(By.id("UserLoginForm_login")).clear();
    driver.findElement(By.id("UserLoginForm_login")).sendKeys("Eva");
    driver.findElement(By.id("UserLoginForm_password")).click();
    driver.findElement(By.id("UserLoginForm_password")).clear();
    driver.findElement(By.id("UserLoginForm_password")).sendKeys("12345");
    driver.findElement(By.xpath("//form[@id='UserLoginForm']/div[4]/button")).click();
  }

  @AfterClass(alwaysRun = true)
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

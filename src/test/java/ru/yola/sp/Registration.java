package ru.yola.sp;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.fail;

public class Registration {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRegistration() throws Exception {
    driver.get("https://dev.sp4.sitepokupok.ru");
    //Нажать кнопку Войти
    driver.findElement(By.cssSelector("a.sp-sidebar-btn")).click();
    driver.findElement(By.cssSelector("a[href*=register]")).click();
    driver.findElement(By.id("UserRegisterForm_login")).click();
    driver.findElement(By.id("UserRegisterForm_login")).clear();
    driver.findElement(By.id("UserRegisterForm_login")).sendKeys("Emma");
    driver.findElement(By.id("UserRegisterForm_email")).click();
    driver.findElement(By.id("UserRegisterForm_email")).clear();
    driver.findElement(By.id("UserRegisterForm_email")).sendKeys("nadezhda.kuklina+emma@sitepokupok.ru");
    driver.findElement(By.id("UserRegisterForm_password")).click();
    driver.findElement(By.id("UserRegisterForm_password")).clear();
    driver.findElement(By.id("UserRegisterForm_password")).sendKeys("12345");
    driver.findElement(By.id("UserRegisterForm_isAgree_label")).click();
    driver.findElement(By.cssSelector("div.form-group button")).click();
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
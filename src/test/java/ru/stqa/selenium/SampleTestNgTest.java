package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.selenium.pages.HomePage;

import java.util.List;

public class SampleTestNgTest extends TestNgTestBase {

  private HomePage homepage;

  @BeforeMethod
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, HomePage.class);
    driver.get(baseUrl);

  }

  @Test
  public void testLoginPositive() {

    WebElement loginLink = this.waitUntilElementIsLoadedCustomTime(
            By.xpath("//span[contains(text(),'Login')]"),
            40,
            "Login link was not loaded");

    loginLink.click();
    WebElement buttonLogin = waitUntilElementIsLoadedCustomTime(
            By.xpath("//span[contains(text(),'Log in')]"),
            40,
            "Login field element was not loaded");

    List<WebElement> webElements
            = driver.findElements(
                    By.xpath("//div [@class='mat-input-infix mat-form-field-infix']/input"));

    WebElement fieldLogin = webElements.get(0);
    fieldLogin.click();
    fieldLogin.sendKeys("alexshufutinsk@gmail.com");

    WebElement fieldPassword = webElements.get(1);
    fieldPassword.click();
    fieldPassword.sendKeys("123456");

    buttonLogin.click();

    WebElement filterCities = waitUntilElementIsLoadedCustomTime(
            By.xpath("//div[@class='mat-select-value']"),
            40,
            "Filter Cities element was not loaded");

    filterCities.click();
  }

  @Test
  public void testWrongPassword(){

    WebElement loginLink = this.waitUntilElementIsLoadedCustomTime(
            By.xpath("//span[contains(text(),'Login')]"),
            40,
            "Login link was not loaded");

    loginLink.click();

    WebElement buttonLogin = waitUntilElementIsLoadedCustomTime(
            By.xpath("//span[contains(text(),'Log in')]"),
            40,
            "Login field element was not loaded");


    List<WebElement> webElements
            = driver.findElements(
            By.xpath("//div [@class='mat-input-infix mat-form-field-infix']/input"));
    WebElement fieldLogin = webElements.get(0);
    fieldLogin.click();
    fieldLogin.sendKeys("alexshufutinsk@gmail.com");

    WebElement wrongPassword = webElements.get(1);
    wrongPassword.click();
    wrongPassword.sendKeys("ghghhghsgshsg");
    //wrongPassword.click();
    buttonLogin.click();
    WebElement wrongMessage = waitUntilElementIsLoadedCustomTime(
            By.xpath("//*[contains(text(),'Wrong authorization, login or password')]"),
            50,
            "Wrong message was not displayed");
    WebElement cancelButton = waitUntilElementIsLoadedCustomTime(
            By.xpath("//span[contains(text(),'Cancel')]"),
            25,
            "Cancel button wasn't found");

    cancelButton.click();
    WebElement linkGoToEvents = waitUntilElementIsLoadedCustomTime(
            By.xpath("//span[contains(text(),'Go to Event list')]"),
            30,
            "'Go to Events List' wasn't loaded");


  }


  private WebElement waitUntilElementIsLoadedCustomTime(By by, int time, String error_message) {
    WebDriverWait wait = new WebDriverWait(driver, time);
    wait.withMessage(error_message + "\n");
    return wait.until(ExpectedConditions.visibilityOfElementLocated(by));


  }

}

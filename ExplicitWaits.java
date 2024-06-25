package com.charlie.learningselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ExplicitWaits {

    private static final String SITE = "https://the-internet.herokuapp.com/";

    private WebDriver driver = new ChromeDriver();

    private long startTime;
    private double duration;

    @BeforeTest
    public void setUp() {
        startTime = System.currentTimeMillis();
        driver.manage().window().maximize();
    }

    @Test
    public void buttonClickTest() {
        driver.get(SITE);

        WebElement dynamicControlsLinkEl = driver.findElement(
                By.linkText("Dynamic Controls"));
        dynamicControlsLinkEl.click();

        WebElement checkboxEl = driver.findElement(By.id("checkbox"));
        Assert.assertTrue(checkboxEl.isDisplayed());

        WebElement removeButtonEl = driver.findElement(
                By.cssSelector("button[onclick='swapCheckbox()']"));
        removeButtonEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(checkboxEl));

        List<WebElement> elements = driver.findElements(By.id("checkbox"));
        Assert.assertTrue(elements.isEmpty());

        WebElement addButtonEl = driver.findElement(
                By.cssSelector("button[onclick='swapCheckbox()']"));
        addButtonEl.click();

        checkboxEl = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("checkbox")));
        Assert.assertTrue(checkboxEl.isDisplayed());
    }

    @Test
    public void buttonClicksTest() {
        driver.get(SITE);

        WebElement dynamicControlsLinkEl = driver.findElement(
                By.linkText("Dynamic Controls"));
        dynamicControlsLinkEl.click();

        WebElement textboxEl = driver.findElement(By.cssSelector("input[type='text']"));
        Assert.assertTrue(textboxEl.isDisplayed());
        Assert.assertFalse(textboxEl.isEnabled());

        WebElement enableButtonEl = driver.findElement(
                By.cssSelector("button[onclick='swapInput()']"));
        enableButtonEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(textboxEl));

        Assert.assertTrue(textboxEl.isDisplayed());
        Assert.assertTrue(textboxEl.isEnabled());


        WebElement disableButtonEl = driver.findElement(
                By.cssSelector("button[onclick='swapInput()']"));
        disableButtonEl.click();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.elementToBeClickable(textboxEl)));
        Assert.assertTrue(textboxEl.isDisplayed());
        Assert.assertFalse(textboxEl.isEnabled());
    }

    @Test
    public void testProgressBars() {
        driver.get("https://testpages.eviltester.com/styled/index.html");

        WebElement dynamicButtonsLinkEl = driver.findElement(
                By.linkText("Multiple Progress Bars"));
        dynamicButtonsLinkEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.attributeToBe(By.id("progressbar0"),
                "value", "100"));
        wait.until(ExpectedConditions.attributeToBe(By.id("progressbar1"),
                "value", "100"));
        wait.until(ExpectedConditions.attributeToBe(By.id("progressbar2"),
                "value", "100"));

        wait.until(ExpectedConditions.textToBe(By.id("status"), "Stopped"));

        WebElement statusElement = driver.findElement(By.id("status"));
        String statusText = statusElement.getText();

        Assert.assertEquals(statusText, "Stopped", "Status message is incorrect.");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        long endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000.0;
        System.out.println("Total time taken: " + duration + " seconds");
    }
}

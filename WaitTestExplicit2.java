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

public class WaitTestExplicit2 {
    private static final String SITE = "https://testpages.eviltester.com/styled/index.html";

    private WebDriver driver = new ChromeDriver();

    private long startTime;
    private double duration;

    @BeforeTest
    public void setUp() {
        driver.manage().window().maximize();
        startTime = System.currentTimeMillis();

    }

    private static void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void buttonClickTest() {
        driver.get(SITE);

        WebElement dynamicButtonsLinkEl = driver.findElement(By.linkText("Dynamic Buttons Challenge 01"));
        dynamicButtonsLinkEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement startButtonEl = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button00")));
        startButtonEl.click();

        WebElement oneButtonEl = wait.until(ExpectedConditions.elementToBeClickable(By.id("button01")));
        oneButtonEl.click();

        WebElement twoButtonEl = wait.until(ExpectedConditions.elementToBeClickable(By.id("button02")));
        twoButtonEl.click();

        WebElement threeButtonEl = wait.until(ExpectedConditions.elementToBeClickable(By.id("button03")));
        threeButtonEl.click();

        WebElement allButtonsClickedMessageEl = driver.findElement(By.id("buttonmessage"));
        String message = allButtonsClickedMessageEl.getText();

        Assert.assertEquals(message, "All Buttons Clicked",
                "Message after clicking all buttons is incorrect.");
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

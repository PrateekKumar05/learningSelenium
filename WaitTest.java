package com.charlie.learningselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitTest {
    private static final String SITE = "https://testpages.eviltester.com/styled/index.html";

    private WebDriver driver = new ChromeDriver();

    private long startTime;
    private double duration;

    @BeforeTest
    public void setUp() {
        driver.manage().window().maximize();
        startTime = System.currentTimeMillis();

        System.out.println("Implicit wait timeout (Before): " +
                driver.manage().timeouts().getImplicitWaitTimeout());

        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));

        System.out.println("Implicit wait timeout (After): " +
                driver.manage().timeouts().getImplicitWaitTimeout());
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

        WebElement startButtonEl = driver.findElement(By.id("button00"));
        startButtonEl.click();

        WebElement oneButtonEl = driver.findElement(By.id("button01"));
        oneButtonEl.click();

        WebElement twoButtonEl = driver.findElement(By.id("button02"));
        twoButtonEl.click();

        WebElement threeButtonEl = driver.findElement(By.id("button03"));
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

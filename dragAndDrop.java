package com.charlie.learningselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class dragAndDrop {
    private WebDriver driver = new ChromeDriver();

    private static final String SITE = "https://demoqa.com/droppable";

    private static void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void dragDropTest() {
        driver.manage().window().maximize();
        driver.get(SITE);

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        new Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform();

        Assert.assertEquals(droppable.getText(), "Dropped!");

        delay();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

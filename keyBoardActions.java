package com.charlie.learningselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class keyBoardActions {
    private WebDriver driver = new ChromeDriver();

    private static final String SITE = "https://testpages.eviltester.com/styled/basic-html-form-test.html";

    private static void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void keyboardActionsTest() {
        driver.manage().window().maximize();
        driver.get(SITE);

        WebElement usernameEl = driver.findElement(By.name("username"));
        WebElement passwordEl = driver.findElement(By.name("password"));
        WebElement commentsEl = driver.findElement(By.name("comments"));

        commentsEl.clear();

        Actions actions = new Actions(driver);

        actions.sendKeys(usernameEl, "John Smith")
                .sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT, Keys.ARROW_LEFT, Keys.ARROW_LEFT, Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys("Oliver ")
                .pause(Duration.ofSeconds(2))
                .keyUp(Keys.SHIFT)
                .sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT)
                .sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE)
                .pause(Duration.ofSeconds(5))
                .sendKeys(passwordEl, "password123")
                .sendKeys(commentsEl, "Some comments here")
                .perform();

        System.out.println("Username: " + usernameEl.getAttribute("value"));
        System.out.println("Password: " + passwordEl.getAttribute("value"));
        System.out.println("Comments: " + commentsEl.getAttribute("value"));

        delay();

        WebElement submitInputEl =  driver.findElement(By.cssSelector("input[value='submit']"));

        submitInputEl.submit();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://testpages.eviltester.com/styled/the_form_processor.php");

        delay();


    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

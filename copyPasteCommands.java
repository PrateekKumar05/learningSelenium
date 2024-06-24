package com.charlie.learningselenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class copyPasteCommands {
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

        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        actions.sendKeys(usernameEl, "John Smith")
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(cmdCtrl)
                .sendKeys("xvv")
                .pause(Duration.ofSeconds(5))
                .keyUp(cmdCtrl)
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

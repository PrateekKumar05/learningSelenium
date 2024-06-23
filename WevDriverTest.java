package com.charlie.learningselenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WevDriverTest {

    @Test
    public void navigateToPageUsingChrome() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
        driver.get("https://demoblaze.com/");
        WebElement samsungEl = driver.findElement(By.className("hrefch"));
        Assert.assertTrue(samsungEl.isEnabled());
        Assert.assertTrue(samsungEl.isDisplayed());
        samsungEl.click();
        WebElement addCart = driver.findElement(By.className("btn-success"));
        addCart.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        WebElement cartel = driver.findElement(By.id("cartur"));
        cartel.click();
        WebElement cartelSuc = driver.findElement(By.className("success"));
        Assert.assertTrue(cartelSuc.isDisplayed());
        Assert.assertTrue(cartelSuc.isEnabled());
        delay(2000);
        driver.quit();
    }

    private void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
package com.charlie.learningselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Test
public class ImageTags {
    public void images() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
        driver.get("https://demoblaze.com/");
        List<WebElement> categoriesEls = driver.findElements(By.id("itemc"));
        for (WebElement categoryEl : categoriesEls) {
            System.out.println("----------------------");
            System.out.println("Category clicked: " + categoryEl.getText());

            categoryEl.click();

            delay(2000);

            WebElement containerEl = driver.findElement(By.id("tbodyid"));

            List<WebElement> linkEls = containerEl.findElements(By.tagName("a"));

            for (WebElement linkEl : linkEls) {
                String hrefAttr = linkEl.getAttribute("href");
                Assert.assertNotNull(hrefAttr);

                System.out.println("URL: " + hrefAttr);
            }
        }
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

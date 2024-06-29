package com.charlie.learningselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class ElementScreenShot {

    private static final String DYNAMIC_CONTENT_SITE = "https://the-internet.herokuapp.com/dynamic_content";
    private static final String SCREENSHOT_DIR = "screenshots";

    private WebDriver driver = new ChromeDriver();


    @Test
    public void testTakeElementScreenshot() throws IOException {
        driver.manage().window().maximize();
        driver.get(DYNAMIC_CONTENT_SITE);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contentEl = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("content")));

        List<WebElement> rowEls = contentEl.findElements(By.className("row"));
        System.out.println(rowEls.size());

        int i = 0;
        for (WebElement rowEl : rowEls) {
            takeScreenshot(rowEl, "screenshot_row_" + i++ + ".png");
        }
        delay();
    }

    private static void takeScreenshot(WebElement rowEl, String filename) throws IOException {
        File screenshotFile = rowEl.getScreenshotAs(OutputType.FILE);

        Path directoryPath = Paths.get(SCREENSHOT_DIR);

        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path destinationFilePath = FileSystems.getDefault().getPath(SCREENSHOT_DIR, filename);

        Files.move(screenshotFile.toPath(), destinationFilePath);
    }

    private static void delay() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

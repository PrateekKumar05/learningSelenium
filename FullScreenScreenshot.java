package com.charlie.learningselenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FullScreenScreenshot {

    private static final String DYNAMIC_CONTENT_SITE = "https://the-internet.herokuapp.com/dynamic_content";
    private static final String SCREENSHOT_DIR = "screenshots";

    private WebDriver driver = new ChromeDriver();


    private static void delay() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testTakeFullScreenScreenshot() throws IOException {
        driver.manage().window().maximize();
        driver.get(DYNAMIC_CONTENT_SITE);

        takeScreenshot(driver, "screenshot_one.png");

        driver.navigate().refresh();

        takeScreenshot(driver, "screenshot_two.png");
    }

    private static void takeScreenshot(WebDriver driver, String filename) throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Path directoryPath = Paths.get(SCREENSHOT_DIR);

        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path destinationFilePath = FileSystems.getDefault().getPath(SCREENSHOT_DIR, filename);

        Files.copy(screenshotFile.toPath(), destinationFilePath);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

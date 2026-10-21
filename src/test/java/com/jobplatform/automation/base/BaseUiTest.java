package com.jobplatform.automation.base;

import com.jobplatform.automation.config.ConfigReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseUiTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    void setUpBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // Selenium Manager resolves a compatible driver in supported environments.
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getInt("ui.timeout.seconds", 10))
        );
    }

    @AfterEach
    void tearDownBrowser() {
        if (driver != null) driver.quit();
    }

    protected String userBaseUrl() {
        return ConfigReader.require("ui.base.url");
    }

    protected String adminBaseUrl() {
        return ConfigReader.require("admin.base.url");
    }

    protected String loginPath() {
        return ConfigReader.get("ui.login.path", "/auth/login");
    }
}

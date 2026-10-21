package com.jobplatform.automation.ui;

import com.jobplatform.automation.base.BaseUiTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageSmokeTest extends BaseUiTest {
    @Test
    void candidateLoginPageShowsEmailPasswordAndSubmit() {
        driver.get(userBaseUrl() + loginPath());

        WebElement email = driver.findElement(By.cssSelector("input#email, input[name='email']"));
        WebElement password = driver.findElement(By.cssSelector("input#password, input[name='password']"));
        WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));

        assertTrue(email.isDisplayed(), "Email input should be visible");
        assertTrue(password.isDisplayed(), "Password input should be visible");
        assertTrue(submit.isDisplayed(), "Submit button should be visible");
    }

    @Test
    void invalidCredentialsDoNotNavigateAwayFromLoginPage() {
        driver.get(userBaseUrl() + loginPath());

        driver.findElement(By.cssSelector("input#email, input[name='email']"))
                .sendKeys("selenium.invalid.user@example.invalid");
        driver.findElement(By.cssSelector("input#password, input[name='password']"))
                .sendKeys("DefinitelyWrongPassword123!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(d -> {
            String current = d.getCurrentUrl();
            return current.contains("/auth/login");
        });

        assertTrue(driver.getCurrentUrl().contains("/auth/login"),
                "Invalid credentials should not lead to an authenticated page");
    }
}

package com.jobplatform.automation.ui;

import com.jobplatform.automation.base.BaseUiTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminLoginPageSmokeTest extends BaseUiTest {
    @Test
    void adminLoginPageShowsRequiredControls() {
        driver.get(adminBaseUrl() + loginPath());

        WebElement email = driver.findElement(By.cssSelector("input#email, input[name='email']"));
        WebElement password = driver.findElement(By.cssSelector("input#password, input[name='password']"));
        WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));

        assertTrue(email.isDisplayed(), "Admin email input should be visible");
        assertTrue(password.isDisplayed(), "Admin password input should be visible");
        assertTrue(submit.isDisplayed(), "Admin submit button should be visible");
    }
}

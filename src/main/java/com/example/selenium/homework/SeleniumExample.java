package com.example.selenium.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumExample {

    public static void main(String[] args) {
        // Đường dẫn đến ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:/path/to/chromedriver.exe");

        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // 1. Mở trang web
            driver.get("http://demo.guru99.com/test/newtours/");
            System.out.println("Opened website: " + driver.getTitle());

            // 2. Kiểm thử Đăng nhập
            WebElement usernameField = driver.findElement(By.name("userName"));
            WebElement passwordField = driver.findElement(By.name("password"));
            WebElement loginButton = driver.findElement(By.name("submit"));

            usernameField.sendKeys("test");
            passwordField.sendKeys("test");
            loginButton.click();

            // Xác minh đăng nhập thành công
            String expectedTitle = "Login: Mercury Tours";
            String actualTitle = driver.getTitle();

            if (expectedTitle.equals(actualTitle)) {
                System.out.println("Login Test: PASS");
            } else {
                System.out.println("Login Test: FAIL");
            }

            // 3. Kiểm thử liên kết (Home link)
            WebElement homeLink = driver.findElement(By.linkText("Home"));
            homeLink.click();

            if (driver.getCurrentUrl().contains("index.php")) {
                System.out.println("Home Link Test: PASS");
            } else {
                System.out.println("Home Link Test: FAIL");
            }

            // 4. Kiểm thử đăng nhập sai
            driver.get("http://demo.guru99.com/test/newtours/");
            usernameField = driver.findElement(By.name("userName"));
            passwordField = driver.findElement(By.name("password"));
            loginButton = driver.findElement(By.name("submit"));

            usernameField.sendKeys("invalid");
            passwordField.sendKeys("123");
            loginButton.click();

            // Kiểm tra thông báo lỗi
            WebElement errorMsg = driver.findElement(By.xpath("//span[contains(text(),'Enter your userName and password correct')]"));
            if (errorMsg.isDisplayed()) {
                System.out.println("Invalid Login Test: PASS");
            } else {
                System.out.println("Invalid Login Test: FAIL");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng trình duyệt
            driver.quit();
        }
    }
}

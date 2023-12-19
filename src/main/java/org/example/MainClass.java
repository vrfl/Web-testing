package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainClass {
    @Test
    public void TestForHabr() {
        System.setProperty("webdriver.chrome.driver", "/Users/wachanga/Documents/Web-testing/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://habr.com/ru/all/");
        driver.manage().window().setSize(new Dimension(1280, 1025));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(
                By.xpath("//*[@class='tm-svg-img tm-header-user-menu__icon tm-header-user-menu__icon_search tm-header-user-menu__icon_dark']")
        ).click();

        Assert.assertEquals(
                driver.findElement(By.xpath("//input[@name='q']")),
                driver.switchTo().activeElement()
        );

        driver.findElement(By.xpath("//input[@name='q']"))
                .sendKeys("Selenium WebDriver");

        driver.findElement(By.xpath("//*[@class='tm-input-text-decorated__label tm-input-text-decorated__label_after']"))
                .click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.linkText("Что такое Selenium?")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Assert.assertEquals(
                "28 сен 2012 в 16:14",
                driver.findElement(By.xpath("//*[@title='2012-09-28, 16:14']")).getText()
        );

        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(
                By.xpath("//a[@href='/ru/articles/' and @class='footer-menu__item-link router-link-active']")
        ).click();

        driver.quit();
    }
}

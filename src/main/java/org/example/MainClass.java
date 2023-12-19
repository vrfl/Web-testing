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

    @Test
    public void testForMail() {
        System.setProperty("webdriver.chrome.driver", "/Users/wachanga/Documents/Web-testing/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Откроем сайт mail.ru
        driver.get("https://mail.ru");
        driver.manage().window().setSize(new Dimension(1280, 1025));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Нажмем кнопку Войти
        driver.findElement(
                By.xpath("//button[@class='resplash-btn resplash-btn_primary resplash-btn_mailbox-big ihfknge-de8k2m']")
        ).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //  Переключимся на фрейм с авторизцией
        WebElement frame = driver.findElement(By.xpath("//*[@class='ag-popup__frame__layout__iframe']"));
        driver.switchTo().frame(frame);

        // Введем логин
        WebElement usernameInput = driver.findElement(By.xpath("//input[@name='username']"));
        Assert.assertEquals(
                usernameInput,
                driver.switchTo().activeElement()
        );
        usernameInput.sendKeys("mega_professional_tester");

        driver.findElement(
                By.xpath("//button[@data-test-id='next-button']")
        ).click();

        // Введем пароль
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        passwordInput.sendKeys("nogduV-xedpe4-wekcez");

        // Войдем в аккаунт
        driver.findElement(By.xpath("//button[@data-test-id='submit-button']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Откроем профиль
        driver.findElement(By.xpath("//*[@class='ph-avatar-img svelte-dfhuqc']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Проверим что имя в профиле совпадает
        Assert.assertEquals(
                "Тестер Супер-дупер",
                driver.findElement(By.xpath("//*[@class='ph-name svelte-1popff4']")).getText()
        );

        // Выйдем из аккаунта
        driver.findElement(By.xpath("//*[@data-testid='whiteline-account-exit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Удостоверимся, что на экране есть кнопка Создать почту
        driver.findElement(
                By.xpath("//a[@class='resplash-btn resplash-btn_outlined-themed resplash-btn_mailbox-big ihfknge-de8k2m']")
        ).isDisplayed();

        driver.quit();
    }
}

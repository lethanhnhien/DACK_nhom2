package Selenium_CuoiKi_Nhom2.Intergration_Testcases;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;


/**
 * Check Cart return empty when log out
 */

@Test
public class TC6 {
    public static void tc6(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //Step 1: Goto https://janshop.com.vn/
            driver.get("https://janshop.com.vn/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//            System.out.println("Opened Website");

            //Step 2: Click Log in button
            WebElement loginBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='user f-right'] a[title='Đăng nhập']"))));
            loginBtn.click();

            //Step 3: Fill all fields

            //3.1 fill email:
            WebElement fieldEmail = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("customer_email"))));
            fieldEmail.sendKeys("nhatm@gmail.com");

            //3.2 fill password:
            WebElement fieldPassword = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("customer_password"))));
            fieldPassword.sendKeys("123456");

            //Step 4: Click Submit
            WebElement SubmitBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[value='Đăng nhập']"))));
            SubmitBtn.click();
            System.out.println("Đăng nhập thành công!!!");

            //Step 5: Add a product to cart:
            WebElement homeBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("body > div:nth-child(1) > header:nth-child(5) > nav:nth-child(2) > div:nth-child(1) > ul:nth-child(3) > li:nth-child(1) > a:nth-child(1)"))));
            homeBtn.click();
            WebElement viewProductBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("body > div:nth-child(1) > section:nth-child(9) > section:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > button:nth-child(2)"))));
            viewProductBtn.click();
            WebElement addProductBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".txt-main"))));
            addProductBtn.click();
            WebElement closeModal = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@title,'Close')]//i[contains(@class,'fa-close')]"))));
            closeModal.click();
            //Step 6: Check amount in cart:
            WebElement amount = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
            String amountNumber =  amount.getText();
            System.out.println("Amount in Cart: "+amountNumber);

            //Step 7: Get amount after log out
            WebElement amount2 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".text"))));
            String amountNumber2 =  amount2.getText();
            System.out.println("Amount : "+amountNumber2);

            //Step 8: Verify
            if(amountNumber.equals(amountNumber2)){
                System.out.println("Not return empty cart when user log out! That is BUG");
            }else{
                System.out.println("Returned empty cart when user log out");
            }
            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Fail!!!");
        }
        driver.close();
        driver.quit();
    }
}

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
 * Test log out
 * Test Step:
 * 1. Goto https://janshop.com.vn/
 * 2. Click Log in button
 * 3. In log in form, enter email and password
 * 4. Click Submit button
 * 5. Click Log out button
 * 6. Check status
 */

@Test
public class TC4 {
    public static void tc4(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //Step 1: Goto https://janshop.com.vn/
            driver.get("https://janshop.com.vn/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//            System.out.println("Opened Website");

            //Step 2: Click Register button
            WebElement registerBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='user f-right'] a[title='Đăng nhập']"))));
            registerBtn.click();

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


            WebElement outBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='user f-right'] a[title='Đăng xuất']"))));
            outBtn.click();

            //Verify Register status
            try{
                WebElement checkStatus = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='errors'] ul li"))));
                String checkValue = checkStatus.getText();
                System.out.println(checkValue);
                if(checkValue.equals("Thông tin đăng nhập không hợp lệ.")){
                    System.out.println("Đăng nhập thất bại !!!");
                }
            }catch(Exception e){
                System.out.println("Đăng nhập thành công!!!");
            }
            try{
                WebElement logoutBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]"))));
                logoutBtn.click();
                System.out.println("Đăng xuất thành công!!!");
            }catch(Exception e){
                System.out.println("Đăng xuất thất bại !!!");
            }
            //
            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Fail!!!");
        }
        driver.close();
        driver.quit();
    }
}

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
 * Test login
 * Test Step:
 * 1. Goto https://janshop.com.vn/
 * 2. Click Log in button
 * 3. In log in form, enter email and password
 * 4. Click Submit button
 * 5. Check status
 */

@Test
public class TC3 {
    public static void tc3(){
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

            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Fail!!!");
        }
        driver.close();
        driver.quit();
    }
}

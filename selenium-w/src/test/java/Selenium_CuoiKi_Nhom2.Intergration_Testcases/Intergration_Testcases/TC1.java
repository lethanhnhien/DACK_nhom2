package Selenium_CuoiKi_Nhom2.Intergration_Testcases;


import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Test register an account
 * Test Step:
 * 1. Goto https://janshop.com.vn/
 * 2. Click Register button
 * 3. In register form, enter all field
 * 4. Click Submit button
 * 5. Check status
 */

@Test
public class TC1 {
    public static void tc1(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //Step 1: Goto https://janshop.com.vn/
            driver.get("https://janshop.com.vn/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//            System.out.println("Opened Website");


            WebElement avaBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".user.f-right"))));
            avaBtn.click();

            //Step 2: Click Register button
            WebElement registerBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='user f-right'] a[title='Đăng ký']"))));
            registerBtn.click();

            //Step 3: Fill all fields
            //3.1 fill first name:
            WebElement fieldFirstName = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("first_name"))));
            fieldFirstName.sendKeys("nhat");

            //3.2 fill last name:
            WebElement fieldLastName = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("last_name"))));
            fieldLastName.sendKeys("vo");

            //3.3 fill email:
            WebElement fieldEmail = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("email"))));
            fieldEmail.sendKeys("nhatm@gmail.com");

            //3.4 fill phone:
            WebElement fieldPhone = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("phone"))));
            fieldPhone.sendKeys("0899243945");

            //3.5 fill password:
            WebElement fieldPassword = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("creat_password"))));
            fieldPassword.sendKeys("123456");

            //Step 4: Click Submit
            WebElement SubmitBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[value='Đăng ký']"))));
            SubmitBtn.click();

            //Verify Register status
            String checkTitle = driver.getTitle();
            System.out.println(checkTitle);
            if(checkTitle.equals("Tạo tài khoản – janshop")){
                System.out.println("Đăng kí chưa thành công!!!");
            }else{
                System.out.println("Đã đăng kí!!!");
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

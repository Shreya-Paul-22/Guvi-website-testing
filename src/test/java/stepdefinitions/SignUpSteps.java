package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import java.time.Duration;

public class SignUpSteps {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.guvi.in/register";

    @Given("I am on the Sign Up page")
    public void i_am_on_the_sign_up_page() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @When("I enter username {string}, email {string}, phone number {string}, and password {string}")
    public void i_enter_username_email_phone_and_password(String username, String email, String phone, String password) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(username);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("mobileNumber")).clear();
        driver.findElement(By.id("mobileNumber")).sendKeys(phone);
    }

    @When("I leave mandatory fields blank")
    public void i_leave_mandatory_fields_blank() {
        // Do not fill any fields
    }

    @And("I submit the sign up form")
    public void i_submit_the_sign_up_form() {
        driver.findElement(By.id("signup-btn")).click();
    }

    @Then("my user account should be created successfully")
    public void account_created_successfully() {
 
    }

    @Then("I should be redirected to the welcome page")
    public void redirected_to_welcome_page() {
        driver.quit();
    }

    @Then("I should see an error message for invalid email")
    public void error_message_for_invalid_email() {
        
            WebElement emailError = driver.findElement(By.xpath("//div[@class='invalid-feedback is-invalid']"));
            Assert.assertTrue("Hmm...that doesnt look like an email address. Try again.", emailError.isDisplayed());
        
            driver.quit();
   
    }

    @Then("I should see a validation message for password complexity")
    public void validation_message_for_password_complexity() {
        WebElement pwdError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Password must satisfy conditions below.']")));
        Assert.assertTrue("Password must satisfy conditions below.", pwdError.isDisplayed());
        driver.quit();
    }

   /* @Then("I should see an error message for invalid phone number")
    public void error_message_for_invalid_phone_number() {
        WebElement phoneError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/main[@id='sign-in-page']/section[@class='signup-page']/div[@class='section']/div[@class='sign-up-form signup-custom-w']/form[@id='signup-form']/div[@class='form-group mobNumErr']/div[2]")));
        Assert.assertTrue("Hmm...that doesnt look like an mobile number. Try again.", phoneError.isDisplayed());
        driver.quit();
    }*/

    @Then("I should see error messages for required fields")
    public void error_messages_for_required_fields() {

    	driver.findElement(By.id("signup-btn")).click();
    	driver.quit();
    }
//Use case2
    @Given("I am on the GUVI homepage")
    public void i_am_on_the_guvi_homepage() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.guvi.in/");
        driver.manage().window().maximize();
    }

    @When("I log in with username {string} and password {string}")
    public void i_log_in_with_username_and_password(String username, String password) {
        driver.findElement(By.linkText("Login")).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(username);
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("mobile-login")).click();
    }

    @When("I click on the course section")
    public void i_click_on_the_course_section() {
        WebElement courseSection = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Courses")));
        courseSection.click();
    }

    @When("I search for course {string}")
    public void i_search_for_course(String courseName) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class='flex-grow outline-none focus:outline-none border-none text-sm lg:text-base h-fit w-full lg:w-fit pl-2 py-1 lg:pr-6']")));
        searchBox.clear();
        searchBox.sendKeys(courseName);
        searchBox.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@alt,'Python Zero to Hero Malayalam Thumbnail')]"))).click();
    }
    @When("I fill in name {string}, email {string}, and phone number {string}")
    public void i_fill_in_name_email_and_phone(String name, String email, String phone) {
        // Wait for the enrollment form to appear (adjust selectors as needed)
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']")));
        nameField.clear();
        nameField.sendKeys(name);
        WebElement emailField = driver.findElement(By.xpath("//input[@id='email']"));
        emailField.clear();
        emailField.sendKeys(email);
        WebElement phoneField = driver.findElement(By.xpath("//input[@id='phone']"));
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    @When("I enroll in the course")
    public void i_enroll_in_the_course() {
        WebElement enrollBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='enroll-now']")));
        enrollBtn.click();
    }

    @When("I cancel the payment")
    public void i_cancel_the_payment() {
        // Wait for payment page and click cancel/back if available
        try {
            WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"close pointer paymentcancel1\"]")));
            cancelBtn.click();
        } catch (TimeoutException e) {
            // If no cancel button, try browser back
            driver.navigate().back();
        }
    }

    @Then("I should see a message indicating the payment was cancelled or not completed")
    public void i_should_see_payment_cancelled_message() {
        boolean found = false;
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"paymentcancelconfirmation\"]/div/div/div[1]/h2")));
            found = msg.isDisplayed();
        } catch (TimeoutException e) {
            found = false;
        }
        Assert.assertTrue("Complete your purchase to start learning", found);
        driver.quit();
    }

    // Use Case 3: Login Functionality
    @Given("I am on the Login page")
    public void i_am_on_the_login_page() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.guvi.in/");
        driver.manage().window().maximize();
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login")));
        loginBtn.click();
    }

    @When("I enter login email {string} and password {string}")
    public void i_enter_login_email_and_password(String email, String password) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.clear();
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @When("I submit the login form")
    public void i_submit_the_login_form() {
        driver.findElement(By.id("login-btn")).click();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        // Wait for dashboard or user profile to appear
        boolean loggedIn = false;
        try {
            wait.until(ExpectedConditions.or(
                
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='content']"))
            ));
            loggedIn = true;
        } catch (TimeoutException e) {
            loggedIn = false;
        }
        Assert.assertTrue("Please Consider Activating Your Guvi Account.", loggedIn);
        driver.quit();
    }

    @Then("I should see an error message for invalid login")
    public void i_should_see_an_error_message_for_invalid_login() {
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='emailgroup']//div[1]")));
        Assert.assertTrue("Incorrect Email or Password", errorMsg.isDisplayed());
        driver.quit();
    }

    @When("I leave login fields blank")
    public void i_leave_login_fields_blank() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.clear();
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
    }

    @Then("I should see validation errors for blank login fields")
    public void i_should_see_validation_errors_for_blank_login_fields() {
        WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Hey, Did you forgot your password? Try again.']")));
        Assert.assertTrue("Hey, Did you forgot your password? Try again.", passwordError.isDisplayed());
        driver.quit();
    }

    
}
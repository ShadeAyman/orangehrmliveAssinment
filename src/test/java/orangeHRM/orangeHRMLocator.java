package orangeHRM;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class orangeHRMLocator {
    SHAFT.GUI.WebDriver driver;
    //locators
    By emailInput = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input");
    By password = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input");
    By loginButton = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
    By Admin = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a");
    By RecordsFound = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span");
    By addButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
    By UserRole = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div");
    By userRoleDropDown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div[2]/div[3]");
    By employeeName = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input");
    By employeeNameDropDown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div");
    By status = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div");
    By statusDropDown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div[2]/div[2]");
    By username = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
    By saveButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");
    By addPassword = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
    By addPasswordConfirmation = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
    By userSearch = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input");
    By searchButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");


    //Test Methods
    @Test
    public void testCase() throws InterruptedException {

//    1-Navigate to "https://opensource-demo.orangehrmlive.com/"
        driver.browser().navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//    2-Enter "Admin" as username
        driver.element().type(emailInput, "Admin");
//    3-Enter "admin123" as password
        driver.element().type(password, "admin123");
//    4-Click on the login button
        driver.element().click(loginButton);
//    5-Click on Admin tab on the left side menu
        driver.element().click(Admin);
//    6-Get the number of records found
        String recordsCheat = driver.element().getText(RecordsFound); //geting the value as a string and will compare it later to the new value if change then it and yes its lazy :3
//    7-Click on add button
        driver.element().click(addButton);
//    8-Fill the required data
        driver.element().click(UserRole);
        driver.element().click(userRoleDropDown);
        driver.element().type(employeeName,"tris");
        Thread.sleep(2000);//the guy in the video said its bad but meh
        driver.element().click(employeeNameDropDown);
        driver.element().click(status);
        driver.element().click(statusDropDown);
        driver.element().type(username,"Tristan");
        driver.element().type(addPassword,"abcd123456");
        driver.element().type(addPasswordConfirmation,"abcd123456");
//    9-Click on save button
        driver.element().click(saveButton);
        Thread.sleep(2000);
//    10-Verify that the number of records increased by
        String recordsCheat1 = driver.element().getText(RecordsFound);
        driver.assertThat().element(RecordsFound).text().doesNotEqual(recordsCheat).withCustomReportMessage("first record"+recordsCheat+"second record:"+recordsCheat1);//have no idea if that is working
//    11-Search with the username for the new user
        driver.element().type(userSearch,"Tristan");
        driver.element().click(searchButton);
        Thread.sleep(1000);
//    12-Delete the new user
        driver.element().click(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[6]/div/button[1]"));
        driver.element().click(By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]"));
//    13-Verify that the number of records decreased by 1
        driver.element().click(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]"));//clicking on reset
        Thread.sleep(2000);
        driver.assertThat().element(RecordsFound).text().doesNotEqual(recordsCheat1);
    }

    @BeforeClass
    public void setUp() {
        driver = new SHAFT.GUI.WebDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


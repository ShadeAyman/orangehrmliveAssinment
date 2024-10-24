package orangeHRM;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.example.Main.strToInt;//for the string to int function



public class orangeHRMLocator {
    SHAFT.GUI.WebDriver driver;
    //locators
    By emailInput = By.xpath("//input[@name=\"username\"]");
    By password = By.xpath("//input[@name=\"password\"]");
    By loginButton = By.xpath("//button");
    By Admin = By.xpath("//a[@href=\"/web/index.php/admin/viewAdminModule\"]");
    By RecordsFound = By.xpath("//div/span[@data-v-7b563373]");
    By addButton = By.xpath("//button[text()=\" Add \"]");
    //----------------------
    By UserRole = By.xpath("//div/label[text()=\"User Role\"]/../following-sibling::div");
    By userRoleDropDown = By.xpath("//span[text()=\"ESS\"]");
    By employeeName = By.xpath("//div/label[text()=\"Employee Name\"]/../following-sibling::div/div/div/input");
    By employeeNameDropDown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div[1]");
    By status = By.xpath("//div/label[text()=\"Status\"]/../following-sibling::div");
    By statusDropDown = By.xpath("//span[text()=\"Enabled\"]");
    //------------------------
    By username = By.xpath("//div/label[text()=\"Username\"]/../following-sibling::div/input");//the /../ will travel 1 step up
    By addPassword = By.xpath("//div/label[text()=\"Password\"]/../following-sibling::div/input");
    By addPasswordConfirmation = By.xpath("//div/label[text()=\"Confirm Password\"]/../following-sibling::div/input");
    By saveButton = By.xpath("//button[@type=\"submit\"]");
    By userSearch = By.xpath("//div/label[text()=\"Username\"]/../following-sibling::div/input");
    By searchButton = By.xpath("//button[@type=\"submit\"]");
    By deleteButton = By.xpath("//div[@data-v-c423d1fa]/button[1]");
    By confirmDeletion = By.xpath("//button[text()=\" Yes, Delete \"]");
    By restButton = By.xpath("//button[text()=\" Reset \"]");

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
//        String originalRecord = (; //getting the value as a string and will compare it later to the new value if change then it and yes its lazy :3
        int originalRecord = strToInt(driver.element().getText(RecordsFound));
//    7-Click on add button
        driver.element().click(addButton);
//    8-Fill the required data
        driver.element().click(UserRole);
        driver.element().click(userRoleDropDown);
        driver.element().type(employeeName,"te");
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
//    10-Verify that the number of records increased by 1
        int newRecord = strToInt(driver.element().getText(RecordsFound));
        SHAFT.Validations.assertThat().object(newRecord-originalRecord).isEqualTo(1).perform();//thx for the help <3

//        driver.assertThat().element(RecordsFound).text().doesNotEqual(originalRecord).withCustomReportMessage("first record"+ originalRecord +"second record:"+newRecord);//have no idea if that is working
//    11-Search with the username for the new user
        driver.element().type(userSearch,"Tristan");
        driver.element().click(searchButton);
        Thread.sleep(1000);
//    12-Delete the new user
        driver.element().click(deleteButton);
        driver.element().click(confirmDeletion);
//    13-Verify that the number of records decreased by 1
        driver.element().click(restButton);//clicking on reset
        Thread.sleep(2000);
        int lastRecord = strToInt(driver.element().getText(RecordsFound));
        SHAFT.Validations.assertThat().object(newRecord-lastRecord).isEqualTo(1).perform();
//        driver.assertThat().element(RecordsFound).text().doesNotEqual(newRecord);
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


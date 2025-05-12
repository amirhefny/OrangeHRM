package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.UIActions;

import java.time.Duration;
import java.util.Random;

public class AdminPage extends UIActions {
    public static String generatedEmail =generateRandomEmail();
    public static String generatedPassword =generateRandomPassword();

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DOMAIN = "@example.com";
    private static final int EMAIL_LENGTH = 7;


    public void clickOnAdministrator(){
         
        final WebElement Administratorbtn = find(LocatorType.xpath,"//a[@href='/web/index.php/admin/viewAdminModule']");
        implicitWait(10);
        try {
            clickOnElement(Administratorbtn);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't find this element %s",Administratorbtn));
        }
    }
    public void clickOnAddIcon() throws Exception {
        final WebElement addbutton = find(LocatorType.xpath,"//div[@class='orangehrm-header-container']//button");
        clickOnElement(addbutton);
    }
    public int getTheRecordNumber(){
        implicitWait(15);
        final WebElement recordNumber = find(LocatorType.xpath,"//span[@class='oxd-text oxd-text--span']");
       String recordsFound = getElementText(recordNumber);
        String numberWithStringType = recordsFound.replaceAll("[^0-9]", "");
        int numberOfRecords = Integer.parseInt(numberWithStringType);
        return numberOfRecords;
    }

    public void setUserRole() {
        WebElement userRole = waitUntil(locateElement(LocatorType.xpath,"//label[text()='User Role']/../..//div[@class='oxd-select-text-input']"),ExpectedConditionsEnum.visibilityOfElement, Duration.ofSeconds(7));
        try {
            clickOnElement(userRole);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't find this element %s",userRole));
        }

        WebElement userRoleSelection = waitUntil(locateElement(LocatorType.xpath,"(//div[@class='oxd-select-option'])[2]"),ExpectedConditionsEnum.visibilityOfElement, Duration.ofSeconds(5));
        try {
            clickOnElement(userRoleSelection);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't find this element %s",userRoleSelection));
        }
    }

    public void setEmployeeName() throws Exception {
        WebElement employeeNameInputField = find(LocatorType.xpath,"//input[@placeholder='Type for hints...']");
        fill(employeeNameInputField,"A");

        implicitWait(10);
        final WebElement employeeName = find(LocatorType.xpath,"(//div[@class='oxd-autocomplete-dropdown --positon-bottom']//div[@class='oxd-autocomplete-option'])[3]");
        clickOnElement(employeeName);

    }

    public void setUserStatus(){
        WebElement userStatus = waitUntil(locateElement(LocatorType.xpath,"//label[text()='Status']/../..//div[@class='oxd-select-text-input']"),ExpectedConditionsEnum.visibilityOfElement, Duration.ofSeconds(5));
        try {
            clickOnElement(userStatus);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't find this element %s",userStatus));
        }
        WebElement userStatusSelection = waitUntil(locateElement(LocatorType.xpath,"(//div[@class='oxd-select-option'])[2]"),ExpectedConditionsEnum.visibilityOfElement, Duration.ofSeconds(5));
        try {
            clickOnElement(userStatusSelection);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't find this element %s",userStatusSelection));
        }
    }

    public void setUsername(){
        try {
            WebElement username = find(LocatorType.xpath,"//label[text()='Username']/../..//input[@class='oxd-input oxd-input--active']");
            fill(username,generatedEmail);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't set the username due to this exception %s",e.getMessage()));
        }
    }

    public void setPassword(){
        try {
            WebElement password = find(LocatorType.xpath,"//label[text()='Confirm Password']/../..//input[@class='oxd-input oxd-input--active']");
            fill(password,generatedPassword);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't set the password due to this exception %s",e.getMessage()));
        }
    }

    public void setConfirmPassword(){
        try {
            WebElement password = find(LocatorType.xpath,"//label[text()='Password']/../..//input[@class='oxd-input oxd-input--active']");
            fill(password,generatedPassword);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't set the confirm password due to this exception %s",e.getMessage()));
        }
    }

    public void addEmployee(){
        WebElement saveBtn = find(LocatorType.xpath,"//button[@type='submit']");
        try {
            clickOnElement(saveBtn);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't add this employee exception %s",e.getMessage()));
        }
    }

    public void verifyTheAddedUser(){
        addEmployee();
        implicitWait(3);

        SoftAssert softAssert = new SoftAssert();
        int recordAfterAddUser = getTheRecordNumber() +1 ;
        String errorMsg = String.format("Bug::The user is added but not reflected on the system The number before add the user is %s and after adding is %s",getTheRecordNumber(),recordAfterAddUser);
        softAssert.assertEquals(getTheRecordNumber(),recordAfterAddUser,errorMsg);
    }

    public void searchForEmployee(){
        try {

            WebElement username = waitUntil(locateElement(LocatorType.xpath,"//label[text()='Username']/../..//input[@class='oxd-input oxd-input--active']"),ExpectedConditionsEnum.visibilityOfElement,Duration.ofSeconds(10));
            fill(username,generatedEmail);


            WebElement searchBtn = find(LocatorType.xpath,"//button[@type='submit']");
            clickOnElement(searchBtn);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't search for the employee"));
        }
    }

    public void deleteUser(){
        WebElement deleteIcon = waitUntil(locateElement(LocatorType.xpath,"//button[@class='oxd-icon-button oxd-table-cell-action-space']//i[@class='oxd-icon bi-trash']"),ExpectedConditionsEnum.visibilityOfElement,Duration.ofSeconds(5));
        try {
            clickOnElement(deleteIcon);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't found this elment %s",deleteIcon));
        }
        WebElement deleteChoice = waitUntil(locateElement(LocatorType.xpath,"//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']"),ExpectedConditionsEnum.visibilityOfElement,Duration.ofSeconds(5));
        try {
            clickOnElement(deleteChoice);
        } catch (Exception e) {
            System.out.println(String.format("Couldn't found this elment %s",deleteChoice));
        }
    }
    public void verifyTheDeletedUser(){
        deleteUser();
        implicitWait(3);
        clickOnAdministrator();
        SoftAssert softAssert = new SoftAssert();
        int recordAfterDeleteUser = getTheRecordNumber() -1 ;
        String errorMsg = String.format("Bug::The user is deleted but not reflected on the system The number before delete the user is %s and after deleting is %s",getTheRecordNumber(),recordAfterDeleteUser);
        softAssert.assertEquals(getTheRecordNumber(),recordAfterDeleteUser,errorMsg);
    }


    public static String generateRandomEmail() {
        Random random = new Random();
        StringBuilder email = new StringBuilder(EMAIL_LENGTH + DOMAIN.length());
        for (int i = 0; i < EMAIL_LENGTH; i++) {
            email.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        email.append(DOMAIN);
        return email.toString();
    }

    public static String generateRandomPassword() {
        Faker faker = new Faker();
        return faker.internet().password(10, 15);
    }

}

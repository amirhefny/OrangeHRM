package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.AdminPage;
import pages.LoginPage;

public class stepdefinition {
    LoginPage loginPageObj = new LoginPage();
    AdminPage adminPage = new AdminPage();

    @Given("User enter the username {string} and the password {string}")
    public void loginSuccessfully(String username, String pass) throws Exception {
        loginPageObj.setUsername(username);
        loginPageObj.setPassword(pass);
        loginPageObj.clickOnLogin();
    }

    @Given("Click on Admin tab on the left side menu")
    public void clickOnAdministrator() throws Exception {
        adminPage.clickOnAdministrator();
    }

    @Given("Click on add button")
    public void clickOnAddIcon() throws Exception {
        adminPage.clickOnAddIcon();
    }

    @Given("User set the role")
    public void setEmployeeUserRole() throws Exception {
        adminPage.setUserRole();
    }

    @Given("User set the employee name")
    public void setEmployeeName() throws Exception {
        adminPage.setEmployeeName();
    }

    @Given("User set the status")
    public void setStatus(){
        adminPage.setUserStatus();

    }

    @Given(("User set the username and password"))
    public void setUsernameAndPass(){
        adminPage.setUsername();
        adminPage.setPassword();
        adminPage.setConfirmPassword();
    }

    @Then("Admin add the user and Verify that the number of records increased by one")
    public void addUser(){
        adminPage.verifyTheAddedUser();
    }

    @Then("Search with the username for the new user")
    public void searchForTheEmployeeByMail(){
        adminPage.searchForEmployee();
    }

    @Then("Delete the User and Verify that the number of records decreased by one")
    public void deleteTheUser(){
        adminPage.verifyTheDeletedUser();
    }





}

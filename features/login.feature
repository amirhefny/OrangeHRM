Feature: Login Successfully

  Scenario: Admin Add user and then delete the same user
    Given User enter the username "Admin" and the password "admin123"
    When Click on Admin tab on the left side menu
    Then Click on add button
    And User set the role
    Then User set the employee name
    And User set the status
    And User set the username and password
    Then Admin add the user and Verify that the number of records increased by one
    Then Search with the username for the new user
    Then Delete the User and Verify that the number of records decreased by one

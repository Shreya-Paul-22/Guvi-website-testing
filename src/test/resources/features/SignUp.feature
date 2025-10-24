Feature: Sign Up Functionality
  As a new user
  I want to sign up for an account
  So that I can access the website's features
  
  @TC01 
  Scenario: TC01 - Valid data entered in all fields
    Given I am on the Sign Up page
    When I enter username "testuserShreya", email "testuser1@example.com", phone number "9876543210", and password "Test@1234"
    And I submit the sign up form
    Then my user account should be created successfully
    And I should be redirected to the welcome page

  @TC02
  Scenario: TC02 - Invalid email format
    Given I am on the Sign Up page
    When I enter username "testuserPaul", email "invalid", phone number "9876543210", and password "Test@1234"
    
    Then I should see an error message for invalid email
  @TC03
  Scenario: TC03 - Password does not meet criteria
    Given I am on the Sign Up page
    When I enter username "testuser", email "testuser3@example.com", phone number "9876543210", and password "123"
    And I submit the sign up form
    Then I should see a validation message for password complexity

  @TC04
  Scenario: TC04 - Leaving mandatory fields blank
    Given I am on the Sign Up page
    When I leave mandatory fields blank
    And I submit the sign up form
    Then I should see error messages for required fields

  @TC05
  Scenario: TC05 - Successful Course Enrollment
    Given I am on the GUVI homepage
    When I click on the course section
    And I search for course "Python"
    And I fill in name "Test User", email "testuser@guvi.com", and phone number "9876543210"
    And I enroll in the course
    And I cancel the payment
    Then I should see a message indicating the payment was cancelled or not completed

  @TC06
  Scenario Outline: TC06 - Login Functionality Validation
    Given I am on the Login page
    When I enter login email "<email>" and password "<password>"
    And I submit the login form
    Then <result>

    Examples:
      | email                   | password    | result                                         |
      | kumarishreyapaul22@gmail.com     | Shreya@2001   | I should be logged in successfully             |
      | invaliduser@guvi.com    | wrongpass   | I should see an error message for invalid login|
      |                        |             | I should see validation errors for blank login fields |
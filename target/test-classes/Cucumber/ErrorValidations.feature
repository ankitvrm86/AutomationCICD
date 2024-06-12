@tag
Feature: Error Validation
I want to use this template for my feature file

@ErrorValidation
Scenario Outline: Negative scenario of login error validation
Given User landed on Ecommerce Page
When User Logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed

Examples:
|name								| password 	|
|ankit86@gmail.com	| Ankit@12	|
@tag
Feature: Purchase the order from Ecommerce Website
I want to use this template for my feature file

Background:
Given User landed on Ecommerce Page

@Regression
Scenario Outline: Postive Test of Submitting the order
Given User Logged in with username <name> and password <password>
When User add product <productName> to cart
And  Checkout <productName> and submit the order 
Then "THANKYOU FOR THE ORDER." is displayed on Confirmation Page

Examples:
|name								| password 	| productName |
|ankit86@gmail.com	| Ankit@123	| ZARA COAT 3	|
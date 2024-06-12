package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber", glue = "rahulshettyacademy.StepDefinitions", monochrome = true, 
tags = "@Regression", plugin= {"html: target/cucumber.html"})

//by default output of Cucumber will come in non readable format so we use "monochrome= true"

//plugin - give in key value pairs = key -> in which format you want the report to be generated. value-> where do you want to store the report

public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
//to run test in TestNG then we need to extend "AbstractTestNGCucumberTests" class which provides lots of wrapper so that Cucumber can open and run
//TestNG code. IF you are running with JUnit then by default Cucumber have libraries to run code with JUnit
Technical Requirements


Operating system: Windows/MacOS/Linux
Software platform: JAVA


JDK 8


Java IDE: Eclipse/IntelliJ IDEA under Win
Version control: Git
Git repository hosting service: Github


Specific access is needed


Build tool: Maven


Included


Software-testing framework: Selenium


Included


Testing & Reporting framework: TestNG


Included


Web Browsers


Chrome
Firefox





Setup


Install JDK 8 from https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Install your preferred IDE.
Import the project from your IDE File > Import > Projects from Git > Clone URI.
Project is imported and ready for test. See the USAGE section for info on how to use it.



Purpose

The test automation is testing the basics of completing an order from the site.

Structure and Usage

The framework used is POM (Page object model). Tests are interacting with the webelements with every page, so that minimum code duplication is used.

Tests are run from the XML File: TestNGTests.xml or from the OrdersTestSuite test class, 


Reports on the tests are found under TestNG live reporting window, on the Console and in the Test Output folder in the project.
Tests are re-run up to 2 times in case they fail for some reason, due to the implemented interface IRetryAnalyzer from the RetryAnalyzer class.
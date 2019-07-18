## Technical Requirements

1. Operating system: Windows/MacOS/Linux
2. Software platform: JAVA
	1. JDK 8
3. Java IDE: Eclipse/IntelliJ IDEA under Win
4. Version control: Git
5. Git repository hosting service: Gitthub
6. Build tool: Maven
	1. Included
7. Software-testing framework: Selenium
	1. Included
8. Testing & Reporting framework: TestNG
	1. Included
9. Web Browsers
	1. Chrome
	2. Firefox

## Setup

1. Install JDK 8 from https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
2. Install your preferred IDE.
3. Import the project from your IDE File > Import > Projects from Git > Clone URI.
4. Initialize pom.xml as a maven project and setup SDK to JDK 8 if needed.
5. Project is imported and ready for test. See the USAGE section for info on how to use it.


## Purpose

The test automation is testing the basics of completing an order from the site.

## Structure and Usage

The framework used is POM (Page object model).
Tests are run from the XML File: TestNGTests.xml in order to run on both browsers or from the OrdersTestSuite test class.

Reports on the tests are found under TestNG live reporting window, on the Console and in the Test Output folder in the project.
Tests are re-run up to 2 times in case they fail for some reason, due to the implemented interface IRetryAnalyzer from the RetryAnalyzer class.
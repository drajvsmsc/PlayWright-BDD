@mag
Feature: Select the Mag

#Background:
#Given A workbook named "Mydra" and sheet named "Mydata" is read

  Scenario Outline: TS_<TestDataID>
    Given User navigates to Website
    Then use selects Jackets

    Examples:
      |TestDataID|
      |01_COT|
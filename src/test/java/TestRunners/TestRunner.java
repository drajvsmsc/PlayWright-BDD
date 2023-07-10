package TestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(features ="src/test/resources/features",tags="@mag",glue = {"StepDefinitions"},plugin={"html:target/cucumber-reports/cucumber-html-report.html"
        /*,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"*/})
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}

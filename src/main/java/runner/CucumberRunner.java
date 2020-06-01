package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features="src/main/java/features", glue={"stepdefs"},monochrome=true)
public class CucumberRunner extends AbstractTestNGCucumberTests{
	
	

}

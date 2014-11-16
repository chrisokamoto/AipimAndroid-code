//package test.com.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Screenshot {
	
	private WebDriver browser = GeneralDefinitions.getBrowser();
	
	@Before
	public void beforeScenario(Scenario sc) throws IOException {
		if(GeneralDefinitions.getFirstScenario() == true){
			File aipimPath = new File("aipim4J/screenshots");
			if (aipimPath.exists()){
				File[] files = aipimPath.listFiles();
		        for (int i = 0; i < files.length; i++) {
		            if (files[i].isDirectory()) {
		                FileUtils.deleteDirectory(files[i]);
		            } else {
		                files[i].delete();
		            }
		        }
			}
			GeneralDefinitions.setFirstScenario(false);
		}
			
		Dimension screenResolution = new Dimension((int) SCREENSHOT_WIDTH, (int) SCREENSHOT_HEIGHT);
		browser.manage().window().setSize(screenResolution);
		
	}

	@After
	public void afterScenario(Scenario scenario) throws IOException, InterruptedException {
		Calendar cal = Calendar.getInstance();
		Boolean screenshot = false;
		BufferedReader file = new BufferedReader(new FileReader("config/aipim.yml"));
		file.readLine();
		if(file.readLine().contains("true"))
			screenshot = true;
		file.close();
		
		File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);		
		Thread.sleep(100);
		String featureName = "";
		
		for(String scenarioTag : scenario.getSourceTagNames()){
			if(scenarioTag.contains("feature"))
				featureName = scenarioTag.substring(1, scenarioTag.length()) + "/";
		}
		
		String path = "aipim4J/screenshots/" + featureName;
		if ((scenario.getSourceTagNames().contains("@screenshot")) && 
				(scenario.getSourceTagNames().contains("@javascript")) &&
				browser != null && screenshot)
			FileUtils.copyFile(scrFile, new File(path + scenario.getName() + "_" + cal.getTimeInMillis() + ".png"));
	}

}


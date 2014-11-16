//package test.com.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class GeneralDefinitions {
	
	private static WebDriver gBrowser;
	private static Boolean firstScenario = true;
	
	public static WebDriver getBrowser(){
		if(gBrowser == null)
			 gBrowser = new FirefoxDriver();
		return gBrowser;
	}

	public static Boolean getFirstScenario() {
		return firstScenario;
	}

	public static void setFirstScenario(Boolean firstScenario) {
		GeneralDefinitions.firstScenario = firstScenario;
	}
	
}

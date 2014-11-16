package com.factati.aipim;

import java.util.ArrayList;
import java.util.List;

public class Workset {
	
	private String featureName;
	private List<String> featureDescription;
	private String contextName;
	private List<String> contextDescription;
	private Boolean hasScreenshot;
	private List<String> screenshotFileNames;
	private Integer screenshotCounter;
	private List<ScenarioAipim> scenario;
	
	Workset(){
		screenshotFileNames = new ArrayList<String>();
		featureDescription = new ArrayList<String>();
		contextDescription = new ArrayList<String>();
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public List<String> getFeatureDescription() {
		return featureDescription;
	}

	public void setFeatureDescription(List<String> featureDescription) {
		this.featureDescription = featureDescription;
	}

	public List<String> getContextDescription() {
		return contextDescription;
	}

	public void setContextDescription(List<String> contextDescription) {
		this.contextDescription = contextDescription;
	}

	public Boolean getHasScreenshot() {
		return hasScreenshot;
	}

	public void setHasScreenshot(Boolean hasScreenshot) {
		this.hasScreenshot = hasScreenshot;
	}

	public List<String> getScreenshotFileNames() {
		return screenshotFileNames;
	}

	public void setScreenshotFileNames(List<String> screenshotFileNames) {
		this.screenshotFileNames = screenshotFileNames;
	}

	public Integer getScreenshotCounter() {
		return screenshotCounter;
	}

	public void setScreenshotCounter(Integer screenshotCounter) {
		this.screenshotCounter = screenshotCounter;
	}

	public List<ScenarioAipim> getScenario() {
		return scenario;
	}

	public void setScenario(List<ScenarioAipim> scenario) {
		this.scenario = scenario;
	}

	public String getContextName() {
		return contextName;
	}

	public void setContextName(String contextName) {
		this.contextName = contextName;
	}
	
	
	
	

}

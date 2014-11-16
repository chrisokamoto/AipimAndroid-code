package com.facta.aipim;

import java.util.ArrayList;
import java.util.List;

public class Feature {
	
	private String featureName;
	private List<String> featureDescription;
	private String contextName;
	private List<String> contextDescription;
	private List<ScenarioAipim> scenario;
	private String filename;
	
	Feature(){
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
	

}

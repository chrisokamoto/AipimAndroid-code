package com.facta.aipim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
	
	private ParserHelper parserHelper = new ParserHelper();
	private String line;
	private Workset workset = new Workset();
	
	public String tagScreenshot(BufferedReader file) throws IOException {
		if ((line != null) && (parserHelper.isTag(line))){
			workset.setHasScreenshot(true);
			//line = parserHelper.readLineFromFile(file);
		}
		
		return line;
	}
	
	public String tagFeature(BufferedReader file) throws IOException{
		if ((line != null) && (parserHelper.isFeature(line))){
			int posFeatureName = line.indexOf(":");
			String description = line.substring(posFeatureName+1);
			this.workset.setFeatureName(description);
			
			line = parserHelper.readLineFromFile(file);
			while ((line != null) && !parserHelper.isCommand(line)){
				workset.getFeatureDescription().add(line);
				line = parserHelper.readLineFromFile(file);
			}
		}
		
		return line;
	}
	
	public String tagContext(BufferedReader file) throws IOException{
		if ((line != null) && (parserHelper.isContext(line))){			
			
			line = parserHelper.readLineFromFile(file);
			while ((line != null) && !parserHelper.isCommand(line)){
				workset.getContextDescription().add(line);
				line = parserHelper.readLineFromFile(file);
			}
		}
		
		return line;
	}
	
	public String tagScenario(BufferedReader file) throws IOException{
		String screenshot = "";
		String scenarioName = "";
		List<String> scenarioSteps = new ArrayList<String>();
		
		if ((line != null) && (parserHelper.isScenario(line))){
			scenarioName = parserHelper.getScenario(line);			 
			
			line = parserHelper.readLineFromFile(file);
			while ((line != null) && (!parserHelper.isCommand(line))){
				scenarioSteps.add(line);
				line = parserHelper.readLineFromFile(file);
			}
			
			if(workset.getHasScreenshot()){
				screenshot = workset.getScreenshotFileNames().get(workset.getScreenshotCounter());
				workset.setScreenshotCounter(workset.getScreenshotCounter() + 1);
				workset.setHasScreenshot(false);
			}
			
			ScenarioAipim scenario = new ScenarioAipim();
			scenario.setName(scenarioName);
			scenario.setSteps(scenarioSteps);
			scenario.setScreenshot(screenshot);
			
			workset.getScenario().add(scenario);
		}
		
		return line;
	}
	
	public String processTags(BufferedReader file) throws IOException{
		tagScreenshot(file);
		tagFeature(file);	
		tagScreenshot(file);
		tagContext(file);
		tagScreenshot(file);
		tagScenario(file);
		tagScreenshot(file);
		
		line = parserHelper.readLineFromFile(file);
		while (line != null){
			if(parserHelper.isCommand(line))
				return line;
			line = parserHelper.readLineFromFile(file);
		}
		
		return line;
	}
	
	public Feature parser(String filename) throws IOException{
		BufferedReader file = readFile(filename);
		List<String> screenshotsFilenames = getScreenshotFilenames(filename);		
		Feature feature = new Feature();			
		
		workset.setHasScreenshot(false);
		workset.setScreenshotCounter(0);
		workset.setScreenshotFileNames(screenshotsFilenames);
		workset.setFeatureName("");
		workset.setFeatureDescription(new ArrayList<String>());
		workset.setContextName("");
		workset.setContextDescription(new ArrayList<String>());
		workset.setScenario(new ArrayList<ScenarioAipim>());
		
		line = parserHelper.readLineFromFile(file);
		while (line != null){			
			line = processTags(file);
		}
		
		feature.setFeatureName(workset.getFeatureName());
		feature.setFeatureDescription(workset.getFeatureDescription());
		feature.setContextName(workset.getContextName());
		feature.setContextDescription(workset.getContextDescription());
		feature.setScenario(workset.getScenario());
		feature.setFilename(filename);
		
		return feature;
		
	}
	
	private BufferedReader readFile(String filename) {
		BufferedReader file = null;
		
		filename = "functional-tests/features/" + filename;
		try{
			file = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}
		
		return file;
	}

	private List<String> getScreenshotFilenames(String filename) {
		List<String> listScreenshotNames = new ArrayList<String>();
		
		File path = new File("aipim4J/screenshots/" + filename);
		if(path.isDirectory()){
			File[] files = path.listFiles();
			Arrays.sort(files);
			for (File f : files){							
				listScreenshotNames.add(f.getName());
			}
		}
				
		return listScreenshotNames;
				
	}
	
	
	public ParserHelper getParserHelper() {
		return parserHelper;
	}

	public void setParserHelper(ParserHelper parserHelper) {
		this.parserHelper = parserHelper;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Workset getWorkset() {
		return workset;
	}

	public void setWorkset(Workset workset) {
		this.workset = workset;
	}
	
	

}

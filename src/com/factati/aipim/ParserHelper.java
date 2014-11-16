package com.factati.aipim;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class ParserHelper {
	
	Scanner scanner;
	
	ParserHelper(){
		
	}
	
	public Boolean isCommand(String line){
		return isFeature(line) || isScenario(line) || isContext(line) || isTag(line); 
	}
	
	public boolean isFeature(String line) {
		if(line.contains("Funcionalidade"))
			return true;
		
		return false;
	}
	
	public String getFeature(String line){
		String target = "Funcionalidade:";
		String feature = line.substring(line.indexOf(target) + target.length());
		
		return feature;
	}

	public boolean isScenario(String line) {
		if(line.contains("Cenário"))
			return true;
		
		return false;
	}
	
	public String getScenario(String line){
		String target = "Cenário:";
		String feature = line.substring(line.indexOf(target) + target.length());
		
		return feature;
	}

	public boolean isContext(String line) {
		if(line.contains("Contexto"))
			return true;
		
		return false;
	}
	
	public String getContext(String line){
		String target = "Contexto:";
		String feature = line.substring(line.indexOf(target) + target.length());
		
		return feature;
	}

	public boolean isTag(String line) {
		if(line.contains("@"))
			return true;
		
		return false;
	}

	public boolean isScreenshot(String line) {
		if((line.contains("@screenshot")) && (line.contains("@javascript")))
			return true;
		
		return false;
	}
	
	public boolean isComment(String line) {
		if(line.contains("#"))
			return true;
		
		return false;
	}
	
	public String readLineFromFile(BufferedReader file) throws IOException{				
		
		return file.readLine();
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	
		

}

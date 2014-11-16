package com.factati.aipim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Aipim {
	public static void main(String[] args) throws IOException {
		
		if (args[0].equals("html")){
			File path = new File("aipim4J/html/");
				for (File f : path.listFiles()){
					if(f.getName().contains(".feature.html"))					
						f.delete();
				}        
		
			List<String> featureFileNames = getFeaturesFileNames();
			Parser parserObject = new Parser();
			List<Feature> listFeatures = new ArrayList<Feature>();
			
			for (String filename: featureFileNames){
				listFeatures.add(parserObject.parser(filename));
			}
			
			Html html = new Html();
			html.generateIndexHtml(listFeatures);
			html.generateFeaturesHtml(listFeatures);
		
		}
		
		else if (args[0].equals("generate")){
			File aipimPath = new File("aipim4J");
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
			aipimPath.delete();
			
			new File("aipim4J").mkdirs();
			new File("aipim4J/screenshots").mkdirs();
			new File("aipim4J/screenshots/empty");
			new File("aipim4J/html").mkdirs();
			new File("aipim4J/html/costum").mkdirs();
			new File("config").mkdirs();
						
            File destDirCostumCSS = new File("aipim4J/html/costum/costum.css");
            File destDirCostumJS = new File("aipim4J/html/costum/costum.js");
            File destDirHtmlBootstrap = new File("aipim4J/html/bootstrap.min.css");
            File destDirHtmlJQuery = new File("aipim4J/html/jquery-1.9.1.js");
            File destDirConfig = new File("config/aipim.yml");
            File destDirScreenshot = new File("config/Screenshot.java");       
            File destGeneralDefinitions = new File("src/test/com/resources/GeneralDefinitions.java");
            URL srcFileCSS = Aipim.class.getResource("/lib/assets/costum.css");
            URL srcFileJS = Aipim.class.getResource("/lib/assets/costum.js");
            URL srcFileBootstrap = Aipim.class.getResource("/lib/assets/bootstrap.min.css");
            URL srcFileJQuery = Aipim.class.getResource("/lib/assets/jquery-1.9.1.js");
            URL srcFileConfig = Aipim.class.getResource("/config/aipim.yml");            
            URL srcGeneralDefinitions = Aipim.class.getResource("/lib/assets/GeneralDefinitions.java");
            URL srcScreenshot = Aipim.class.getResource("/lib/assets/Screenshot.java");
            FileUtils.copyURLToFile(srcFileCSS, destDirCostumCSS);            
            FileUtils.copyURLToFile(srcFileJS, destDirCostumJS);
            FileUtils.copyURLToFile(srcFileBootstrap, destDirHtmlBootstrap);
            FileUtils.copyURLToFile(srcFileJQuery, destDirHtmlJQuery);
            FileUtils.copyURLToFile(srcGeneralDefinitions, destGeneralDefinitions);
            FileUtils.copyURLToFile(srcFileConfig, destDirConfig);
            FileUtils.copyURLToFile(srcScreenshot, destDirScreenshot);
            
            String width = "1366";
            String height = "768";
            
            if(args.length > 1){
            	for(int i=0; i<args.length; i++ ){
            		if(args[i].equals("--resolution")){
            			int pos = args[i+1].indexOf("x");
            			width = args[i+1].substring(0, pos - 1);
            			height = args[i+1].substring(pos + 1, args[i+1].length());
            		}
            	}
            }
            
            BufferedReader file = new BufferedReader(new FileReader(destDirScreenshot));
            String fileStr = "";
            String line = file.readLine();
    		while (line != null){		
    			fileStr += line + System.getProperty("line.separator");
    			line = file.readLine();
    		}
    		
    		fileStr = fileStr.replace("SCREENSHOT_WIDTH", width);
    		fileStr = fileStr.replace("SCREENSHOT_HEIGHT", height);
    		file.close();
    		
    		BufferedWriter output = new BufferedWriter(new FileWriter("src/test/com/resources/Screenshot.java"));
    		output.write(fileStr);
    		output.close();
    		destDirScreenshot.delete();
		}
		
		else if (args[0].equals("--screenshot")){			
            String fileStr = "---" + System.getProperty("line.separator");
            fileStr += "screenshot: true";
    		
    		BufferedWriter output = new BufferedWriter(new FileWriter("config/aipim.yml"));
    		output.write(fileStr);
    		output.close();
		}
		
		else if (args[0].equals("--no-screenshot")){			
            String fileStr = "---" + System.getProperty("line.separator");
            fileStr += "screenshot: false";
    		
    		BufferedWriter output = new BufferedWriter(new FileWriter("config/aipim.yml"));
    		output.write(fileStr);
    		output.close();
		}

    }

	private static List<String> getFeaturesFileNames() {
		List<String> listFeatureNames = new ArrayList<String>();
		
		File path = new File("src/cucumber/resources");
		for (File f : path.listFiles()){
			if(f.getName().contains(".feature"))					
				listFeatureNames.add(f.getName());
		}
		
		return listFeatureNames;
	}
}



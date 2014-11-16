package com.facta.aipim;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Html {
	
	public String getTagColor(String str, String word, String classname){
		str = str.replace(word,"<font class="+classname+">"+word+"</font>");
		
		return str;
	}
	
	public String getTagQuotes(String str){
		List<String> quotes = new ArrayList<String>();				
				
		Pattern pattern = Pattern.compile("(?:^|\\s)\"([^\"]*?)\"(?:$|\\s)");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			quotes.add(matcher.group(1));
		}
		Set<String> uniqueQuotes = new HashSet<String>(quotes);
		
		for(String quote : uniqueQuotes){
			str = str.replace(quote, "<font class=\"tag-quotes\">"+quote+"</font>");
		}
		
		return str;
	}
	
	public String colorize(String str){
		str = getTagQuotes(str);
		str = getTagColor(str, "Dado ", "tag-given");
		str = getTagColor(str, "Quando ", "tag-when");
		str = getTagColor(str, "Então ", "tag-then");
		str = getTagColor(str, "E ", "tag-and");
		
		return str;
	}
	
	public void generateFeaturesHtml(List<Feature> listFeatures) throws IOException{
		Integer i=0, j=0, k=0;
		
		for(Feature feature : listFeatures){//i
			k=0;
		
			BufferedWriter output = new BufferedWriter(new FileWriter("aipim4J/html/" + feature.getFilename() +
					".html"));
		
			output.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			output.newLine();
			output.write("<html>");
			output.newLine();
			output.write("	<head>");
			output.newLine();
			output.write("		<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
			output.newLine();
			output.write("		<link href=\"bootstrap.min.css\" rel=\"stylesheet\">");
			output.newLine();
			output.write("		<link href=\"costum/costum.css\" rel=\"stylesheet\">");
			output.newLine();
			output.write("		<script src=\"jquery-1.9.1.js\"></script>");
			output.newLine();
			output.write("		<script src=\"costum/costum.js\"></script>");
			output.newLine();
			output.write("	</head>");
			output.newLine();
			output.write("<body>");
			output.newLine();
			output.write("	<div class=\"panel panel-default scenario-index-panel\">");
			output.newLine();
			output.write("		<div class=\"panel-body scenario-index-body\">");
			output.newLine();
			output.write("			<ul class=\"list-unstyled\">");
			output.newLine();
		
			for(Feature t : listFeatures){//j
				output.write("				<li><a href=\"" + t.getFilename() + ".html\">" +
					(j+1) + ". " + t.getFeatureName() + "</a></li>");
				output.newLine();
				if (i == j){
					output.write("					<li>");
					output.newLine();
					output.write("						<ul class=\"list-unstyled features-link\">");
					for(ScenarioAipim scenario : t.getScenario()){//k
						output.write("							<li><a href=\"#\">" + (j+1) + "." + 
								+ (k+1) + ". " + scenario.getName() + "</a></li>");
						output.newLine();
						
						k++;
					}
					output.write("						</ul>");
					output.newLine();
					output.write("					</li>");
					output.newLine();
					j++;
				}
			}
			output.write("			</ul>");
			output.newLine();
			output.write("		</div>");
			output.newLine();
			output.write("	</div>");
			output.newLine();
			
			j = 0;
			for(ScenarioAipim scenario : feature.getScenario()){//j
				
				output.write("	<div class=\"panel panel-default scenario-panel\">");
				output.newLine();
				output.write("		<div class=\"panel-heading scenario-name\">");
				output.newLine();
				output.write("    		<h3 class=\"panel-title\">" + (i+1) + "." + (j+1) + ". " +
						scenario.getName() + 
						"<span class=\"pull-right\"><a href=\"#\">[índice]</a></span></h3>");
				output.newLine();
				output.write("  		</div>");
				output.newLine();
				output.write("  		<div class=\"panel-body scenario-body\">");
				output.newLine();
				output.write("			<ul class=\"list-unstyled\">");
				output.newLine();
				
				for(String scenarioStep : scenario.getSteps()){					
					output.write("				<li>" + colorize(scenarioStep) + "</li>");
					output.newLine();
				}
				
				output.write("		    </ul>");
				output.newLine();
				output.write("		</div>");
				output.newLine();
				
				if((scenario.getScreenshot() != null) || (!scenario.getScreenshot().equals(""))){
					output.write("  		<div class=\"panel-footer scenario-screenshot\">");
					output.newLine();
					output.write("  			<a href=\"../screenshots/" + feature.getFilename() + 
							"/" + scenario.getScreenshot() + "\"><center><img src=\"../screenshots/" +
							feature.getFilename() + "/" + scenario.getScreenshot() + "\" " +
                            "style=\"width: 50%; height: 80%\" /></center></a>");
					output.newLine();
					output.write("  		</div>");
					output.newLine();
				}
				
				output.write("	</div>");
				output.newLine();
				
				j++;
			}
			output.write("</body>");
			output.newLine();
			output.write("</html>");
			output.newLine();
			i++;
			output.close();
		}
	}
	
	public void generateIndexHtml(List<Feature> listFeatures) throws IOException{
		BufferedWriter output = new BufferedWriter(new FileWriter("aipim4J/html/index.html"));
		output.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		output.newLine();
		output.write("<html>");
		output.newLine();
		output.write("	<head>");
		output.newLine();
		output.write("		<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		output.newLine();
		output.write("		<link href=\"bootstrap.min.css\" rel=\"stylesheet\">");
		output.newLine();
		output.write("		<link href=\"costum/costum.css\" rel=\"stylesheet\">");
		output.newLine();
		output.write("		<script src=\"jquery-1.9.1.js\"></script>");
		output.newLine();
		output.write("		<script src=\"costum/costum.js\"></script>");
		output.newLine();
		output.write("	</head>");
		output.newLine();
		output.write("<body>");
		output.newLine();
		output.write("	<div class=\"panel panel-default scenario-index-panel\">");
		output.newLine();
		output.write("		<div class=\"panel-body scenario-index-body\">");
		output.newLine();
		output.write("			<ul class=\"list-unstyled\">");
		output.newLine();
		
		Integer i=0;
		for(Feature feature : listFeatures){
			output.write("<li><a href=\"" + feature.getFilename() + ".html\">" + (i+1) + ". " + feature.getFeatureName() + "</a></li>");
			output.newLine();
			i++;
		}
		
		output.write("			</ul>");
		output.newLine();
		output.write("		</div>");
		output.newLine();
		output.write("	</div>");
		output.newLine();
		output.write("</body>");
		output.newLine();
		output.write("</html>");
		output.newLine();
		
		output.close();
	}
	
}

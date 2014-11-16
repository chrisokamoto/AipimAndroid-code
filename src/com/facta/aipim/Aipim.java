package com.facta.aipim;

import java.io.BufferedWriter;
import java.io.File;
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
            new File("functional-tests/features/resources").mkdirs();

            File destDirCostumCSS = new File("aipim4J/html/costum/costum.css");
            File destDirCostumJS = new File("aipim4J/html/costum/costum.js");
            File destDirHtmlBootstrap = new File("aipim4J/html/bootstrap.min.css");
            File destDirHtmlJQuery = new File("aipim4J/html/jquery-1.9.1.js");
            File destDirConfig = new File("aipim.yml");
            File destDirScreenshot = new File("functional-tests/features/resources/screenshot.rb");
            URL srcFileCSS = Aipim.class.getResource("/lib/assets/costum.css");
            /*URL srcFileJS = new File( "lib/assets/costum.js").toURI().toURL();
            URL srcFileBootstrap = new File( "lib/assets/bootstrap.min.css").toURI().toURL();
            URL srcFileJQuery = new File( "lib/assets/jquery-1.9.1.js").toURI().toURL();
            URL srcFileConfig = new File( "config/aipim.yml").toURI().toURL();            
            URL srcScreenshot = new File( "lib/assets/screenshot.rb").toURI().toURL();*/
            URL srcFileJS = Aipim.class.getResource("/lib/assets/costum.js");
            URL srcFileBootstrap = Aipim.class.getResource("/lib/assets/bootstrap.min.css");
            URL srcFileJQuery = Aipim.class.getResource("/lib/assets/jquery-1.9.1.js");
            URL srcFileConfig = Aipim.class.getResource("/config/aipim.yml");            
            URL srcScreenshot = Aipim.class.getResource("/lib/assets/Screenshot.java");
            FileUtils.copyURLToFile(srcFileCSS, destDirCostumCSS);
            FileUtils.copyURLToFile(srcFileJS, destDirCostumJS);
            FileUtils.copyURLToFile(srcFileBootstrap, destDirHtmlBootstrap);
            FileUtils.copyURLToFile(srcFileJQuery, destDirHtmlJQuery);            
            FileUtils.copyURLToFile(srcFileConfig, destDirConfig);
            FileUtils.copyURLToFile(srcScreenshot, destDirScreenshot);

            
        }

        else if (args[0].equals("--screenshot")){
            String fileStr = "---" + System.getProperty("line.separator");
            fileStr += "screenshot: true";

            BufferedWriter output = new BufferedWriter(new FileWriter("aipim.yml"));
            output.write(fileStr);
            output.close();
        }

        else if (args[0].equals("--no-screenshot")){
            String fileStr = "---" + System.getProperty("line.separator");
            fileStr += "screenshot: false";

            BufferedWriter output = new BufferedWriter(new FileWriter("aipim.yml"));
            output.write(fileStr);
            output.close();
        }

    }

    private static List<String> getFeaturesFileNames() {
        List<String> listFeatureNames = new ArrayList<String>();

        File path = new File("functional-tests/features");
        for (File f : path.listFiles()){
            if(f.getName().contains(".feature"))
                listFeatureNames.add(f.getName());
        }

        return listFeatureNames;
    }
}



package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    //And here too
    private String mPath;

    public Finder(String pathToCountryDir) {
        mPath = pathToCountryDir;
    }

    private Boolean isMatchPattern(String word, String searchWord) {
        String pattern = searchWord + ".*";
        Pattern negativePattern = Pattern.compile(pattern);
        Matcher matcher = negativePattern.matcher(word);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public String[] makeCountryList(String searchWord) {
        File folder = new File(mPath);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> countryNames;
        try{
           countryNames = new ArrayList<String>(listOfFiles.length);
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() &&
                        isMatchPattern(listOfFiles[i].getName().toLowerCase(), searchWord.toLowerCase())) {
                    countryNames.add(listOfFiles[i].getName());
                }
            }
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
            countryNames = new ArrayList<String>();
            countryNames.add("Error");
        }


        return countryNames.toArray(new String[countryNames.size()]);
    }

    public String[] makeCountryList() {
        File folder = new File(mPath);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> countryNames;
        try{
            countryNames = new ArrayList<String>(listOfFiles.length);
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    countryNames.add(listOfFiles[i].getName());
                }
            }
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
            countryNames = new ArrayList<String>();
            countryNames.add("Error");
        }


        return countryNames.toArray(new String[countryNames.size()]);
    }
    static public String readTextFromInputStream(InputStream in) throws IOException {
        StringBuilder text = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in,"windows-1251"));
        String line = null;
        String newline = System.getProperty("line.separator");
        while ((line = reader.readLine())!=null){
            text.append(line);
            text.append(newline);
        }
        reader.close();
        return text.toString();
    }

    public String getDescription(String name) {
        String str = null;
        String result = "";
        try
        {
            FileInputStream stream = new FileInputStream(mPath+"/"+name);
            result = readTextFromInputStream(stream);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}

package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        ArrayList<String> countryNames = new ArrayList<String>(listOfFiles.length);

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() &&
                    isMatchPattern(listOfFiles[i].getName().toLowerCase(), searchWord.toLowerCase())) {
                countryNames.add(listOfFiles[i].getName());
            }
        }
        return countryNames.toArray(new String[countryNames.size()]);
    }

    public String[] makeCountryList() {
        File folder = new File(mPath);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> countryNames = new ArrayList<String>(listOfFiles.length);

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                countryNames.add(listOfFiles[i].getName());
            }
        }
        return countryNames.toArray(new String[countryNames.size()]);
    }

    public String getDescription(String name) {
        String str = null;
        String result = "";
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(mPath+"/"+name));
            while ((str = in.readLine()) != null)
            {
                result += str;
            }
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}

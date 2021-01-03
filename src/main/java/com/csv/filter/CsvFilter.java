package com.csv.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Filter csv files in folder
 */
public class CsvFilter implements FilenameFilter {

    private final static String regexDate = "(?<!\\d)(?:(?:(?:1[6-9]|[2-9]\\d)?\\d{2})(?:(?:(?:0[13578]|1[02])31)|(?:(?:0[1,3-9]|1[0-2])(?:29|30)))|(?:(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))0229)|(?:(?:1[6-9]|[2-9]\\d)?\\d{2})(?:(?:0?[1-9])|(?:1[0-2]))(?:0?[1-9]|1\\d|2[0-8]))(?!\\d)";
    /**
     * Tests if a specified file should be included in a file list.
     *
     * @param dir  the directory in which the file was found.
     * @param name the name of the file.
     * @return <code>true</code> if and only if the name should be
     * included in the file list; <code>false</code> otherwise.
     */
    @Override
    public boolean accept(File dir, String name) {

        boolean checkFileName = false;
        if (name.toLowerCase().endsWith(".csv")) {
            String[] fileName = name.replace(".csv","").split("_");

            if (checkDeviceName(fileName[0]) && checkDate(fileName[1])) {
                checkFileName = true;
            }
        }
        return checkFileName;
    }

    /**
     * Check file name match with LAPTOP, PRINTER and ROUTER
     * @param name
     * @return
     */
    private boolean checkDeviceName(String name) {
        switch (name) {
            case "LAPTOP" :
            case "PRINTER" :
            case "ROUTER" :
                return true;
            default:
                return false;
        }
    }

    /**
     * Check file name match with date yyyymmdd
     * @param date
     * @return
     */
    private boolean checkDate(String date) {
        return date.matches(regexDate);
    }

}

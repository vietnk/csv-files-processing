package com.csv.property;

import com.csv.CsvFilesProcessingApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * CSV Property
 */
@Component
public class CsvProperty {

    private static Logger logger = LoggerFactory.getLogger(CsvFilesProcessingApplication.class);

    private InputStream inputStream;

    private static final String propFileName = "application.properties";

    public static String CSV_INPUT_DIRECTORY = "";
    public static String CSV_OUTPUT_DIRECTORY = "";
    public static String CSV_VENDOR = "";

    /**
     * Get CSV Directory
     * @return
     * @throws IOException
     */
    public void getCsvDirectory() throws IOException {

        try {
            Properties prop = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            CSV_INPUT_DIRECTORY = prop.getProperty("csv.input.directory");
            CSV_OUTPUT_DIRECTORY = prop.getProperty("csv.output.directory");
            CSV_VENDOR = prop.getProperty("csv.vendor");

        }  catch (Exception e) {
            logger.error( e.getMessage() );
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
    }
}

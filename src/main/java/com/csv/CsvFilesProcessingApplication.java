package com.csv;

import com.csv.controller.CsvController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class CsvFilesProcessingApplication {

	private static Logger logger = LoggerFactory.getLogger(CsvFilesProcessingApplication.class);

	public static void main(String[] args) throws IOException {
		logger.info("STARTING THE APPLICATION");
		final ConfigurableApplicationContext context = SpringApplication.run(CsvFilesProcessingApplication.class, args);
		final CsvController csvController = context.getBean(CsvController.class);
		csvController.run();
		logger.info("APPLICATION FINISHED");
	}
}

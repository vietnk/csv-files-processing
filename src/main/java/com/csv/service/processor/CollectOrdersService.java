package com.csv.service.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;

/**
 * CollectOrdersService
 */
public class CollectOrdersService implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(CollectOrdersService.class);

    private String csvFileName;
    private Path csvFilePath;
    private BlockingQueue<String> queue;

    public CollectOrdersService(String fileName, Path filePath, BlockingQueue<String> queueInput){
        csvFileName = fileName;
        csvFilePath = filePath;
        queue = queueInput;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            BufferedReader reader = Files.newBufferedReader(csvFilePath);

            reader.lines().parallel().forEach( line -> {
                try {
                    if (!line.equals("order_id, item, quantity, vendor")) {
                        queue.put(line);
                    }
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e.getCause());
                    e.printStackTrace();
                }
            });

            logger.info("read " + csvFileName + "finished");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e.getCause());
        }
    }
}

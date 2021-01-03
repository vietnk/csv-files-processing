package com.csv.service.processor;

import com.csv.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ExportOrdersService
 */
public class ExportOrdersService implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(ExportOrdersService.class);

    private String csvFileName;
    private String csvFilePath;
    private List<Order> orders = new ArrayList<>();

    public ExportOrdersService(String fileName, String filePath, List<Order> orders){
        this.csvFileName = fileName;
        this.csvFilePath = filePath;
        this.orders.addAll(orders);
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
            boolean stillWrite = true;
            while (stillWrite) {
                    if (orders.size() != 0) {
                        stillWrite = false;
                    }

                    FileWriter csvWriter = new FileWriter(this.csvFilePath + "/" + this.csvFileName);
                    csvWriter.append("order_id");
                    csvWriter.append(",");
                    csvWriter.append("item");
                    csvWriter.append(",");
                    csvWriter.append("quantity");
                    csvWriter.append("\n");

                    this.orders.parallelStream().forEach((order) -> {
                        try {
                            csvWriter.append(order.toString());
                            csvWriter.append("\n");
                        } catch (IOException e) {
                            logger.error(e.getMessage(), e.getCause());
                            e.printStackTrace();
                        }
                    });

                    csvWriter.flush();
                    csvWriter.close();


                    logger.info("write " + csvFileName + "finished");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e.getCause());
        }
    }
}

package com.csv.controller;

import com.csv.model.Order;
import com.csv.property.CsvProperty;
import com.csv.service.processor.SortOrdersInterface;
import com.csv.service.reader.CsvReaderInterface;
import com.csv.service.writer.CsvWriterInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;

@Component
/**
 * CsvController
 */
public class CsvController {

    private static Logger logger = LoggerFactory.getLogger(CsvController.class);

    private CsvProperty csvProperty;
    private CsvReaderInterface csvReader;
    private SortOrdersInterface sortOrders;
    private CsvWriterInterface csvWriter;

    private static Collection<Thread> readerThreadCollection;

    public CsvController(CsvProperty csvProperty,
                         CsvReaderInterface csvReader,
                         SortOrdersInterface sortOrders,
                         CsvWriterInterface csvWriter) {
        this.csvProperty = csvProperty;
        this.csvReader = csvReader;
        this.sortOrders = sortOrders;
        this.csvWriter = csvWriter;
    }

    /**
     * run create csv order by vendor
     * @throws IOException
     */
    public void run() throws IOException {
        Collection<Thread> allThreadCollection = new ArrayList<>();
        Collection<Thread> writerThreadCollection = new ArrayList<>();
        readerThreadCollection = new ArrayList<>();

        this.csvProperty.getCsvDirectory();

        BlockingQueue<String> queueInput = new LinkedBlockingDeque<>();
        this.csvReader.startToCollectOrders(readerThreadCollection, queueInput);
        allThreadCollection.addAll(readerThreadCollection);

        HashMap<String, List<Order>> orders = this.sortOrders.sort(queueInput);
        this.csvWriter.startToExportOrders(writerThreadCollection, orders);
        allThreadCollection.addAll(writerThreadCollection);

        for(Thread t: allThreadCollection){
            try {
                t.join();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e.getCause());
                e.printStackTrace();
            }
        }

        logger.info("Create CSV Order by Vendor finished");
    }

    /**
     * check reader still alive
     * @return boolean
     */
    public static boolean isReaderAlive(){
        if (readerThreadCollection == null) {
            readerThreadCollection = new ArrayList<>();
        }

        for(Thread t: readerThreadCollection){
            if(t.isAlive())
                return true;
        }
        return false;
    }
}

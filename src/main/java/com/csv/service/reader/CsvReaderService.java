package com.csv.service.reader;

import com.csv.controller.CsvController;
import com.csv.filter.CsvFilter;
import com.csv.property.CsvProperty;
import com.csv.service.processor.CollectOrdersService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;

/**
 * CsvReaderService
 */
@Service
public class CsvReaderService implements CsvReaderInterface{

    @Override
    /**
     * Start thread to collect orders
     * @param readerThreadCollection
     * @param queueInput
     */
    public void startToCollectOrders(Collection<Thread> readerThreadCollection, BlockingQueue<String> queueInput) {
        File directory = new File(CsvProperty.CSV_INPUT_DIRECTORY);
        File[] fileList = directory.listFiles(new CsvFilter());

        assert fileList != null;
        for (File file : fileList) {
            CollectOrdersService collectOrdersService = new CollectOrdersService(file.getName(), file.toPath(), queueInput);
            Thread readerThread = new Thread(collectOrdersService,"reader-"+file.getName());
            readerThreadCollection.add(readerThread);
            readerThread.start();
        }
    }
}

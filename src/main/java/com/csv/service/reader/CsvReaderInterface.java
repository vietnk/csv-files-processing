package com.csv.service.reader;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;

/**
 * CsvReaderInterface
 */
public interface CsvReaderInterface {

    /**
     * Start thread to collect orders
     * @param readerThreadCollection
     * @param queueInput
     */
    void startToCollectOrders(Collection<Thread> readerThreadCollection, BlockingQueue<String> queueInput) throws IOException;
}

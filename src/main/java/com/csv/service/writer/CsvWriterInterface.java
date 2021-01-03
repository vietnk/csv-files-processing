package com.csv.service.writer;

import com.csv.model.Order;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * CsvWriterInterface
 */
public interface CsvWriterInterface {

    /**
     * write csv order by vendor
     * @param writerThreadCollection
     * @param orders
     * @throws IOException
     */
    void startToExportOrders(Collection<Thread> writerThreadCollection, HashMap<String, List<Order>> orders) throws IOException;
}

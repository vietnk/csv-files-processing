package com.csv.service.writer;

import com.csv.model.Order;
import com.csv.property.CsvProperty;
import com.csv.service.processor.ExportOrdersService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class CsvWriterService implements CsvWriterInterface {

    /**
     * write csv order by vendor
     *
     * @param writerThreadCollection
     * @param orders
     */
    @Override
    public void startToExportOrders(Collection<Thread> writerThreadCollection, HashMap<String, List<Order>> orders) {
        orders.forEach((vendor, order) -> {
            if (order.size() > 0) {
                String fileName = vendor + "_orders.csv";
                ExportOrdersService exportOrdersService = new ExportOrdersService(fileName, CsvProperty.CSV_OUTPUT_DIRECTORY, order);
                Thread writerThread = new Thread(exportOrdersService, "writer-" + fileName);
                writerThreadCollection.add(writerThread);
                writerThread.start();
            }
        });
    }
}

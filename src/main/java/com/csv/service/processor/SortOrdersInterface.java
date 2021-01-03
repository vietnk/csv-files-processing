package com.csv.service.processor;

import com.csv.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Sort orders by vendor
 */
public interface SortOrdersInterface {

    /**
     * Sort Order
     * @param queueInput
     * @return
     */
    HashMap<String, List<Order>> sort(BlockingQueue<String> queueInput);
}

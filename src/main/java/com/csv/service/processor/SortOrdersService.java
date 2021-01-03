package com.csv.service.processor;

import com.csv.controller.CsvController;
import com.csv.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * SortOrdersService
 */
@Service
public class SortOrdersService implements SortOrdersInterface {

    /**
     * Sort Order
     * @param queueInput
     * @return
     */
    @Override
    public HashMap<String, List<Order>> sort(BlockingQueue<String> queueInput) {

        HashMap<String, List<Order>> orders = new HashMap<>();
        List<Order> ordersHP = new ArrayList<>();
        List<Order> ordersDELL = new ArrayList<>();
        List<Order> ordersIBM = new ArrayList<>();

        while(true){
            String line = queueInput.poll();

            if(line == null && !CsvController.isReaderAlive()) {
                orders.put("HP", ordersHP);
                orders.put("DELL", ordersDELL);
                orders.put("IBM", ordersIBM);
                return orders;
            }

            if(line != null){
                String[] csvData = line.split(",");
                switch (csvData[3]) {
                    case "HP" :
                        ordersHP.add(this.mapDataToOrder(csvData));
                        break;
                    case "DELL" :
                        ordersDELL.add(this.mapDataToOrder(csvData));
                        break;
                    case "IBM" :
                        ordersIBM.add(this.mapDataToOrder(csvData));
                        break;
                    default:
                        break;
                }
            }

        }
    }

    /**
     * Map csv data to Order
     *
     * @param data
     * @return
     */
    private Order mapDataToOrder(String[] data) {
        Order order = new Order();
        order.setOrderId(data[0]);
        order.setItem(data[1]);
        order.setQuantity(data[2]);

        return order;
    }
}

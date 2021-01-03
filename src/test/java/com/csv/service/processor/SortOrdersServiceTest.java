package com.csv.service.processor;

import com.csv.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SortOrdersServiceTest {

    @InjectMocks
    private SortOrdersService sortOrdersService = new SortOrdersService();

    @Test
    void sort() throws InterruptedException {

        BlockingQueue<String> queueInput = new LinkedBlockingDeque<>();

        queueInput.put("0000000,LAPTOP,666,HP");
        queueInput.put("0000001,LAPTOP,406,IBM");
        queueInput.put("0000002,LAPTOP,937,IBM");
        queueInput.put("0000003,LAPTOP,953,HP");
        queueInput.put("0000004,LAPTOP,817,IBM");
        queueInput.put("0000005,LAPTOP,792,DELL");
        queueInput.put("0000006,LAPTOP,5,HP");
        queueInput.put("0000007,LAPTOP,860,TOSHIBA");
        queueInput.put("0000008,LAPTOP,448,IBM");
        queueInput.put("0000009,LAPTOP,362,HP");
        queueInput.put("0000000,PRINTER,907,HP");
        queueInput.put("0000001,PRINTER,773,IBM");
        queueInput.put("0000002,PRINTER,942,IBM");
        queueInput.put("0000003,PRINTER,523,HP");
        queueInput.put("0000004,PRINTER,893,IBM");
        queueInput.put("0000005,PRINTER,670,DELL");
        queueInput.put("0000006,PRINTER,989,HP");
        queueInput.put("0000007,PRINTER,860,TOSHIBA");
        queueInput.put("0000008,PRINTER,42,IBM");
        queueInput.put("0000009,PRINTER,795,HP");
        queueInput.put("0000000,ROUTER,86,HP");
        queueInput.put("0000001,ROUTER,488,IBM");
        queueInput.put("0000002,ROUTER,324,IBM");
        queueInput.put("0000003,ROUTER,894,HP");
        queueInput.put("0000004,ROUTER,136,IBM");
        queueInput.put("0000005,ROUTER,853,DELL");
        queueInput.put("0000006,ROUTER,375,HP");
        queueInput.put("0000007,ROUTER,167,TOSHIBA");
        queueInput.put("0000008,ROUTER,881,IBM");
        queueInput.put("0000009,ROUTER,547,HP");

        HashMap<String, List<Order>> orders = new HashMap<>();
        List<Order> ordersHP = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i==0) {
                Order order = new Order();
                order.setOrderId("0000000");
                order.setItem("LAPTOP");
                order.setQuantity("666");
                ordersHP.add(order);
                order = new Order();
                order.setOrderId("0000000");
                order.setItem("PRINTER");
                order.setQuantity("907");
                ordersHP.add(order);
                order = new Order();
                order.setOrderId("0000000");
                order.setItem("ROUTER");
                order.setQuantity("86");
                ordersHP.add(order);
            } else if(i ==3) {
                Order order = new Order();
                order.setOrderId("0000003");
                order.setItem("LAPTOP");
                order.setQuantity("953");
                ordersHP.add(order);
                order = new Order();
                order.setOrderId("0000003");
                order.setItem("PRINTER");
                order.setQuantity("523");
                ordersHP.add(order);
                order = new Order();
                order.setOrderId("0000003");
                order.setItem("ROUTER");
                order.setQuantity("894");
                ordersHP.add(order);
            } else if(i==6) {
                Order order = new Order();
                order.setOrderId("0000006");
                order.setItem("LAPTOP");
                order.setQuantity("5");
                ordersHP.add(order);
                order = new Order();
                order.setOrderId("0000006");
                order.setItem("PRINTER");
                order.setQuantity("989");
                ordersHP.add(order);
                order = new Order();
                order.setOrderId("0000006");
                order.setItem("ROUTER");
                order.setQuantity("375");
                ordersHP.add(order);
            } else if(i==9) {
                Order order = new Order();
                order.setOrderId("0000009");
                order.setItem("LAPTOP");
                order.setQuantity("362");
                ordersHP.add(order);
                order = new Order();
                order.setOrderId("0000009");
                order.setItem("PRINTER");
                order.setQuantity("795");
                ordersHP.add(order);
                order = new Order();
                order.setOrderId("0000009");
                order.setItem("ROUTER");
                order.setQuantity("547");
                ordersHP.add(order);
            }
        }
        orders.put("HP",ordersHP);
        List<Order> ordersDELL = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i==5) {
                Order order = new Order();
                order.setOrderId("0000005");
                order.setItem("LAPTOP");
                order.setQuantity("792");
                ordersDELL.add(order);
                order = new Order();
                order.setOrderId("0000005");
                order.setItem("PRINTER");
                order.setQuantity("670");
                ordersDELL.add(order);
                order = new Order();
                order.setOrderId("0000005");
                order.setItem("ROUTER");
                order.setQuantity("853");
                ordersDELL.add(order);
            }
        }
        orders.put("DELL",ordersDELL);
        List<Order> ordersIBM = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i==1) {
                Order order = new Order();
                order.setOrderId("0000001");
                order.setItem("LAPTOP");
                order.setQuantity("406");
                ordersIBM.add(order);
                order = new Order();
                order.setOrderId("0000001");
                order.setItem("PRINTER");
                order.setQuantity("773");
                ordersIBM.add(order);
                order = new Order();
                order.setOrderId("0000001");
                order.setItem("ROUTER");
                order.setQuantity("488");
                ordersIBM.add(order);
            } else if(i ==2) {
                Order order = new Order();
                order.setOrderId("0000002");
                order.setItem("LAPTOP");
                order.setQuantity("937");
                ordersIBM.add(order);
                order = new Order();
                order.setOrderId("0000002");
                order.setItem("PRINTER");
                order.setQuantity("942");
                ordersIBM.add(order);
                order = new Order();
                order.setOrderId("0000002");
                order.setItem("ROUTER");
                order.setQuantity("324");
                ordersIBM.add(order);
            } else if(i==4) {
                Order order = new Order();
                order.setOrderId("0000004");
                order.setItem("LAPTOP");
                order.setQuantity("817");
                ordersIBM.add(order);
                order = new Order();
                order.setOrderId("0000004");
                order.setItem("PRINTER");
                order.setQuantity("893");
                ordersIBM.add(order);
                order = new Order();
                order.setOrderId("0000004");
                order.setItem("ROUTER");
                order.setQuantity("136");
                ordersIBM.add(order);
            } else if(i==8) {
                Order order = new Order();
                order.setOrderId("0000008");
                order.setItem("LAPTOP");
                order.setQuantity("448");
                ordersIBM.add(order);
                order = new Order();
                order.setOrderId("0000008");
                order.setItem("PRINTER");
                order.setQuantity("42");
                ordersIBM.add(order);
                order = new Order();
                order.setOrderId("0000008");
                order.setItem("ROUTER");
                order.setQuantity("881");
                ordersIBM.add(order);
            }
        }
        orders.put("IBM",ordersIBM);

        HashMap<String, List<Order>> actual = this.sortOrdersService.sort(queueInput);

        assertThat(actual).isNotEmpty();
        assertThat(actual.get("HP")).isNotEmpty();
        assertThat(actual.get("DELL")).isNotEmpty();
        assertThat(actual.get("IBM")).isNotEmpty();

        assertThat(actual.get("HP").size()).isEqualTo(ordersHP.size());
        actual.get("HP").forEach( order -> {
            assertThat(ordersHP.stream().filter( o ->
                o.getOrderId().equals(order.getOrderId()) && o.getItem().equals(order.getItem()) && o.getQuantity().equals(order.getQuantity())
            ).findFirst().get()).isNotNull();
        });
        assertThat(actual.get("DELL").size()).isEqualTo(ordersDELL.size());
        actual.get("DELL").forEach( order -> {
            assertThat(ordersDELL.stream().filter( o ->
                    o.getOrderId().equals(order.getOrderId()) && o.getItem().equals(order.getItem()) && o.getQuantity().equals(order.getQuantity())
            ).findFirst().get()).isNotNull();
        });
        assertThat(actual.get("IBM").size()).isEqualTo(ordersIBM.size());
        actual.get("IBM").forEach( order -> {
            assertThat(ordersIBM.stream().filter( o ->
                    o.getOrderId().equals(order.getOrderId()) && o.getItem().equals(order.getItem()) && o.getQuantity().equals(order.getQuantity())
            ).findFirst().get()).isNotNull();
        });
    }
}
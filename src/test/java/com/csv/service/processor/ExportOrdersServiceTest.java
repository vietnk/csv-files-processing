package com.csv.service.processor;

import com.csv.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExportOrdersServiceTest {

    @Mock
    private ExportOrdersService exportOrdersService;

    @Test
    void run() throws IOException {

        File folder = new ClassPathResource("OrdersByVendor").getFile();

        String csvFileName = "DELL_orders.csv";
        String csvFilePath = folder.getAbsolutePath();
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setOrderId("0633700");
        order.setItem("PRINTER");
        order.setQuantity("490");
        orders.add(order);
        order = new Order();
        order.setOrderId("0633710");
        order.setItem("PRINTER");
        order.setQuantity("603");
        orders.add(order);
        order = new Order();
        order.setOrderId("2714425");
        order.setItem("ROUTER");
        order.setQuantity("903");
        orders.add(order);
        order = new Order();
        order.setOrderId("2714435");
        order.setItem("ROUTER");
        order.setQuantity("262");
        orders.add(order);
        order = new Order();
        order.setOrderId("0775270");
        order.setItem("LAPTOP");
        order.setQuantity("148");
        orders.add(order);
        order = new Order();
        order.setOrderId("0775280");
        order.setItem("LAPTOP");
        order.setQuantity("894");
        orders.add(order);

        this.exportOrdersService = new ExportOrdersService(csvFileName, csvFilePath, orders);
        this.exportOrdersService.run();

        List<Order> actual = new ArrayList<>();
        folder = new ClassPathResource("OrdersByVendor").getFile();
        BufferedReader reader = Files.newBufferedReader(Objects.requireNonNull(folder.listFiles())[0].toPath());

        reader.lines().parallel().forEach( line -> {
            if (!line.equals("order_id,item,quantity")) {
                String[] data = line.split(",");
                Order actualOrder = new Order();
                actualOrder.setOrderId(data[0]);
                actualOrder.setItem(data[1]);
                actualOrder.setQuantity(data[2]);
                actual.add(actualOrder);
            }
        });

        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual.size()).isEqualTo(orders.size());
        actual.forEach( aOrder -> {
            assertThat(orders.stream().filter( o ->
                    o.getOrderId().equals(aOrder.getOrderId()) && o.getItem().equals(aOrder.getItem()) && o.getQuantity().equals(aOrder.getQuantity())
            ).findFirst().get()).isNotNull();
        });
    }
}
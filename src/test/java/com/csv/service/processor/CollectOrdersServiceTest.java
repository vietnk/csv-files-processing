package com.csv.service.processor;

import com.csv.filter.CsvFilter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CollectOrdersServiceTest {

    @InjectMocks
    private CollectOrdersService collectOrdersService;

    @Test
    void run() throws IOException, InterruptedException {
        File folder = new ClassPathResource("Orders").getFile();
        File[] fileList = folder.listFiles(new CsvFilter());
        assert fileList != null;
        Path csvFilePath = fileList[0].toPath();
        String csvFileName = fileList[0].getName();
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        queue.put("1001,LAPTOP,20,HP");
        queue.put("1002,LAPTOP,9,DELL");
        queue.put("1003,LAPTOP,12,IBM");

        BlockingQueue<String> actual = new LinkedBlockingDeque<>();
        this.collectOrdersService = new CollectOrdersService(csvFileName, csvFilePath, actual);
        this.collectOrdersService.run();

        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual.size()).isEqualTo(queue.size());

        while(true) {
            String line = actual.poll();
            if (line == null) {
                break;
            }
            assertThat(queue.contains(line)).isTrue();
        }
    }
}
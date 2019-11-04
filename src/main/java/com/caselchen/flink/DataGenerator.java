package com.caselchen.flink;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;

public class DataGenerator {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        List<String> events = Arrays.asList("page_view", "adv_click", "thumbs_up");
        Random random = new Random();
        Data data = null;
        ProducerRecord<String, String> record = null;
        try {
            while (true) {
                long timestamp = System.currentTimeMillis();
                String event = events.get(random.nextInt(events.size()));
                String uuid = UUID.randomUUID().toString();
                data = new Data(timestamp, event, uuid);
                record = new ProducerRecord<>("flink_test", JSON.toJSONString(data));
                producer.send(record);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.flush();
            producer.close();
        }
    }

}

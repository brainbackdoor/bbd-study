package com.brainbackdoor.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.concurrent.Future;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(KafkaApplication.class, args);
    }
}

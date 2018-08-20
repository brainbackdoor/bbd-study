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

    public static void main(String[] args) {
        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers", "localhost:9092");
                                                // 카프카 브로커 주소의 목록
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                                                // 어떤 직렬화 클래스가 Key를 바이트 배열로 변환할 떄 사용되었는지 알려줌
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                                                // 어떤 직렬화 클래스가 Value를 바이트 배열로 변환할 떄 사용되었는지 알려줌
        producerProps.put("acks", "all");       // all : 리더가 모든 복제에 대해 ACK를 수신한 경우에만 프로듀서는 ACK를 받는다.
        producerProps.put("retries", 1);        // 메시지 전송이 실패하면 프로듀서가 예외를 발생시키기 전에 메시지의 전송을 다시 시도하는 수
        producerProps.put("batch.size", 20000); // 설정된 크기만큼 파티션에서 메시지의 배치 처리를 허용
                                                // 설정된 임계값에 도달하거나 특정주기로 메시지를 전송
        producerProps.put("linger.ms", 1);      // 브로커로 현재의 배치를 전송하기 전에 프로듀서가 추가 메시지를 기다리는 시간
                                                // 카프카 프로듀서는 배치가 다 차기를 기다리거나 설정된 linger.ms의 시간 동안 기다림
        producerProps.put("buffer.memory", 24568545);
                                                // 전송되지 않은 메시지를 보관하는 자바 프로듀서가 사용할 전체 메모리
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(producerProps);

        for (int i = 0; i< 2000; i++) {
            ProducerRecord data = new ProducerRecord<String, String>("test1", "Hello this is record " + i);
            Future<RecordMetadata> recordMetadataFuture = producer.send(data);
        }
        producer.close();
    }
}

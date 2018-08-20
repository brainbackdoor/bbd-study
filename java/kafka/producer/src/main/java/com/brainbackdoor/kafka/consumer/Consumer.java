package com.brainbackdoor.kafka.consumer;


import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Consumer {
    private static final Logger log = Logger.getLogger(Consumer.class);

    public static void main(String[] args) throws Exception {
        String topic = "test1";
        List<String> topicList = new ArrayList<>();
        topicList.add(topic);
        Properties consumserProperties = new Properties();
        consumserProperties.put("bootstrap.servers", "localhost:9092");         // 카프카 브로커의 IP 주소
        consumserProperties.put("group.id", "Demo_group");                      // 필요에 따라 컨슈머를 관리하는데 사용(성능 개선)
        consumserProperties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumserProperties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        consumserProperties.put("enable.auto.commit", "true");      // 컨슈머는 설정된 주기에 따라 최근에 읽은 메시지의 오프셋을 자동으로 커밋한다.
        consumserProperties.put("auto.commit.interval.ms", "1000"); // 매초마다 커밋
        consumserProperties.put("session.timeout.ms", "30000");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(consumserProperties);

        kafkaConsumer.subscribe(topicList);         // 컨슈머가 구독하기를 원하는 토픽의 목록을 가져옴
        log.info("Subscribed to topic " + topic);

        int i = 0;
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(500);
                                                    // 폴링은 카프카 토픽에서 데이터를 가져오는 것이다.
                for (ConsumerRecord<String, String> record : records)
                    log.info("offset = " + record.offset() + "key = " + record.key() + "value = " + record.value());

                //TODO : Do processing for data here
                kafkaConsumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {

                    }
                });
            }
        } catch (Exception ex) {
            //TODO : Log Exception Here
        } finally {
            try {
                kafkaConsumer.commitSync();
            } finally {
                kafkaConsumer.close();
            }
        }
    }
}

package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerRunnable implements Runnable{

    public void setRecords(ConsumerRecords<String, String> records) {
        this.records = records;
    }

    ConsumerRecords<String,String> records;

    public void setConsumer(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }

    KafkaConsumer<String, String> consumer;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("topic = " + record.topic() + " offset = " + record.offset() + " value = "+record.key() );
        }
        //consumer.commitAsync();
        consumer.commitSync();

        consumer = null;
        records = null;
    }
}
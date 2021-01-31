package com.kafka;


import com.google.protobuf.ByteString;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.ByteBufferDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

/*
-verbose:gc  -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC  -Xms100M -Xmx200M -Xmn100M
 */
public class CustomConsumer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.30.186:9092,192.168.30.187:9092,192.168.30.188:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "1205");

        //1.创建1个消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList("original-afc-device-state"));
        System.out.println(Thread.currentThread().getName());
        ReentrantLock  reentrantLock = new ReentrantLock();
        //2.调用poll
        while (true) {
            try{
                reentrantLock.lock();
                ConsumerRecords<String,String> records = consumer.poll(1000);
                reentrantLock.unlock();
                if(records.count() > 0){

                    new Thread(()->{
                        reentrantLock.lock();
                        System.out.println(Thread.currentThread().getName()+":"+records.hashCode()+":"+records.count());
                        for (ConsumerRecord<String, String> record : records) {
                            //System.out.println("topic = " + record.topic() + " offset = " + record.offset() + " value = "+record.key() );
                        }

                        //consumer.commitAsync();
                        consumer.commitSync();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        reentrantLock.unlock();



                    },"consume records").start();


//                ConsumerRunnable consumerRunnable = new ConsumerRunnable();
//                consumerRunnable.setRecords(records);
//                consumerRunnable.setConsumer(consumer);
//                System.out.println("-------------------------->");
//                consumerRunnable.run();
//                System.out.println("<--------------------------");
                }
                else
                {
                    System.out.println("No Records Here!");
                }

            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }





        }
    }
}
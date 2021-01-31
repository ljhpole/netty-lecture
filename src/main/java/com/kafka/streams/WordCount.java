package com.kafka.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Properties;

public class WordCount {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,"word-count-dsl");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.30.181:9192,192.168.30.181:9292,192.168.30.181:9392");
//设置本地状态存储
        props.put(StreamsConfig.STATE_DIR_CONFIG,"E:\\kafkastates");
//消息队列状态清空延迟时间（本地存储的文件 即：StreamsConfig.STATE_DIR_CONFIG）
        props.put(StreamsConfig.STATE_CLEANUP_DELAY_MS_CONFIG,"30000");
//1.构建StreamsBuilder
        StreamsBuilder builder = new StreamsBuilder();
        builder.stream("shafc_rcvcmd", Consumed.with(Serdes.String(), Serdes.String()))
                .flatMapValues((v)-> Arrays.asList(v.split("\\W+")))
                .map((k,v)-> new KeyValue<String,Integer>(v,1))
                .groupByKey(Grouped.with(Serdes.String(),Serdes.Integer()).withName("word_group"))
                //对相同的key对应的value求和
                .aggregate(()->0,(k,v,agg)-> agg+v, Materialized.
                        <String,Integer, KeyValueStore<Bytes, byte[]>>as("wordcount")
                        .withKeySerde(Serdes.String())
                        .withValueSerde(Serdes.Integer()))

                .toStream()
                .print(Printed.toSysOut());

//3.启动拓扑
        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(),props);

        kafkaStreams.start();
    }
}

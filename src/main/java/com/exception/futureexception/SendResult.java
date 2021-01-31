package com.exception.futureexception;


public class SendResult<K, V> {

    static class Record<K,V>{
        K  name;
        V  value;
        Record(K key,V value){
            this.name = key;
            this.value = value;
        }
    }
    static class RecordMeta{
        String name;
        RecordMeta(String strValue){
            this.name = strValue;
        }
    }
    private final Record<K, V> producerRecord;
    private final RecordMeta recordMetadata;

    public SendResult(Record<K, V> producerRecord, RecordMeta recordMetadata) {
        this.producerRecord = producerRecord;
        this.recordMetadata = recordMetadata;
    }

    public Record<K, V> getProducerRecord() {
        return this.producerRecord;
    }

    public RecordMeta getRecordMetadata() {
        return this.recordMetadata;
    }

    @Override
    public String toString() {
        return "SendResult [producerRecord=" + this.producerRecord + ", recordMetadata=" + this.recordMetadata + "]";
    }
}


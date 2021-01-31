package com.jdk1_8.test2;

import org.apache.kafka.streams.KeyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class streamTest {

    static void  init(List<String> stringCollection){

        stringCollection.add("1 ddd2 cc2");
        stringCollection.add("2 aaa2 ttt");
        stringCollection.add("3 bbb1 bbb1");
        stringCollection.add("4 aaa1 aaa1");
        stringCollection.add("5 bbb3 bbb3");
        stringCollection.add("6 ccc ccc");
        stringCollection.add("7 bbb2 bbb2");
        stringCollection.add("8 ddd1 ddd1");
    }

    public static void main(String[] args) {
        List<String> stringCollectionTest = new ArrayList<>();
        init(stringCollectionTest);
        // stringCollectionTest.stream().filter((s) -> s.startsWith("b")).forEach(System.out::println);
        //Optional<String> reduced;
        stringCollectionTest
                .stream()
                .sorted()
                .flatMap((v) -> Stream.of(v.split(" ")))
                .map((v)-> new KeyValue(v,1))
                .forEach(System.out::println);

        //.reduce("customer", ((s1, s2) -> s1 + "#" + s2)));
        //reduced.ifPresent(System.out::println);


    }

}

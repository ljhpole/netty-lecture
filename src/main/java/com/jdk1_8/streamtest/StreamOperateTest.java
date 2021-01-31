package com.jdk1_8.streamtest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamOperateTest {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());

        wordLengths.forEach(elemetn-> System.out.println(elemetn));

        System.out.println("======================================================");
        List<String[]> wordLengths1 = words.stream()
                .map(element->element.split(""))
                .distinct()
                .collect(toList());
        wordLengths1.forEach(element-> Arrays.stream(element).forEach(System.out::println));


        System.out.println("======================================================");
        List<Stream<String>> collect = words.stream()
                .map(element -> element.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());
        // collect.forEach(element-> Arrays.stream(element).forEach(System.out::println));

        System.out.println("======================================================");
        List<String> collect1 = words.stream()
                .map(element -> element.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        collect1.forEach((System.out::println));
    }
}

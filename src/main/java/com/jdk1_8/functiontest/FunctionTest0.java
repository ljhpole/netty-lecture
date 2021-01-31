package com.jdk1_8.functiontest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;

public class FunctionTest0 {
    public static void main(String[] args) {
        Function<Integer, Integer> doubleToIntFunction = x -> x + x;
        FunctionTest0 functionTest0 = new FunctionTest0();
        int compute = functionTest0.compute(10, doubleToIntFunction);

        System.out.println(doubleToIntFunction);
        System.out.println(compute);

        List<String> words = Arrays.asList("apple", "banana", "pear");
        Comparator<String>  comparator = (t0,t1)->t0.compareTo(t1);
        BinaryOperator<String> stringBinaryOperator = BinaryOperator.minBy(comparator);

        System.out.println(stringBinaryOperator.apply(words.get(1), words.get(2)));

        //words.forEach(element->stringBinaryOperator.);
    }

    public int compute(Integer param,Function<Integer, Integer> function){
        Integer apply = function.apply(param);
        return apply.intValue();
    }
}

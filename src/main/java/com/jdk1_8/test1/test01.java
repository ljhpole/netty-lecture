package com.jdk1_8.test1;

public class test01 {
    public static void main(String[] args) {
//        IFormula formula = new IFormula() {
//            @Override
//            public double calculate(int a) {
//                return a * a;
//            }
//        };
        IFormula formula = a -> {return  a*a;};
        System.out.println(formula.calculate(2));
        System.out.println(formula.sqrt(2));
    }

}

package com.jdk1_8.test1;

public interface IFormula {
    double calculate(int a);

    // 平方
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

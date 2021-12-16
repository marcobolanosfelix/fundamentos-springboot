package com.fundamentos.springboot.fundamentos.bean;

public class MyNumbersImplements implements MyNumbers {
    @Override
    public void printNumbers(int numberLimit) {
        for (int i=1; i<=numberLimit; i++) {
            System.out.print(" <-> " + i + " <-> ");
        }
    }
}

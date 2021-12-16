package com.fundamentos.springboot.fundamentos.bean;

public class MyNumbersWithDependencyImplement implements MyNumbersWithDependency {
    private MyNumbers myNumbers;

    public MyNumbersWithDependencyImplement(MyNumbers myNumbers) {
        this.myNumbers = myNumbers;
    }

    @Override
    public void printPares(int limit) {
        System.out.println();
        for (int i=1; i<=limit; i++) {
            if (i%2 == 0)
                System.out.print(" <-> " + i + " <-> ");
        }
        System.out.println();
    }

}

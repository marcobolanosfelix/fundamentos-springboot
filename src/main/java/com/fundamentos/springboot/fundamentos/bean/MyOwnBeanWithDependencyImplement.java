package com.fundamentos.springboot.fundamentos.bean;

import java.util.List;

public class MyOwnBeanWithDependencyImplement implements MyOwnBeanWithDependency {
    private MyOwnOperation myOwnOperation;

    public MyOwnBeanWithDependencyImplement(MyOwnOperation myOwnOperation) {
        this.myOwnOperation = myOwnOperation;
    }

    @Override
    public void displayElements() {
        System.out.println("Llamamos a MyOwnOperationImplement para generar lista aleatoria");
        List<Integer> randNums = this.myOwnOperation.generateRandomElements(500, 5);
        randNums.forEach(System.out::println);
        System.out.println("Hola desde mi implementacion de un bean con dependencia usando Streams y Listas");
    }

}

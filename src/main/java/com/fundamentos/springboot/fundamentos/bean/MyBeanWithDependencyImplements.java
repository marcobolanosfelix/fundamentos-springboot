package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplements implements MyBeanWithDependency {
    private final Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplements.class);
    private MyOperation myOperation;

    public MyBeanWithDependencyImplements(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos ingresado al metodo printWithDependency()");
        int numero = 1;
        LOGGER.debug("El numero enviado como parametro a la dependencia operacion es: " + numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementación de un Bean con Dependencia");
    }
}

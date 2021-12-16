package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation() {
        return new MyBean2Implement();
    }

    @Bean
    public MyOperation beanOperationOperation() {
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation) {
        return new MyBeanWithDependencyImplements(myOperation);
    }

    @Bean
    public MyNumbers beanNumbersOperation() {
        return new MyNumbersImplements();
    }

    @Bean
    public MyNumbersWithDependency beanNumbersWithDependencyOperation(MyNumbers myNumbers) {
        return new MyNumbersWithDependencyImplement(myNumbers);
    }

    @Bean
    public MyOwnOperation beanOperationMyOwn() {
        return new MyOwnOperationImplement();
    }

    @Bean
    public MyOwnBeanWithDependency beanOperationMyOwnWithDependency(MyOwnOperation myOwnOperation){
        return new MyOwnBeanWithDependencyImplement(myOwnOperation);
    }

}

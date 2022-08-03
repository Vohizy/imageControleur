package com.vohizy.demospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"images" , "com.vohizy.demospring"})
public class DemoSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSpringApplication.class, args);
    }

}

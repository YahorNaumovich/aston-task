package com.example.astontask;

import org.springframework.boot.SpringApplication;

public class TestAstonTaskApplication {

    public static void main(String[] args) {
        SpringApplication.from(AstonTaskApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

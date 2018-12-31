package com.github.levry.imq.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ImqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImqApplication.class, args);
    }

}

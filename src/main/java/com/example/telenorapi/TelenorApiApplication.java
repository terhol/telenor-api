package com.example.telenorapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class TelenorApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelenorApiApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(TelenorApiApplication.class, args);
        LOGGER.info("Server started");
        LOGGER.info("locale={}",Locale.getDefault());
    }

}

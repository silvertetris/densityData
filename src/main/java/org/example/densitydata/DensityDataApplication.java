package org.example.densitydata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example"})
@EnableScheduling
public class DensityDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(DensityDataApplication.class, args);
    }
}

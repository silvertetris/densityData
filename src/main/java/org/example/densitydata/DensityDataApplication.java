package org.example.densitydata;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.densitydata.service.KosisSeoulDataExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example"})
@EnableScheduling
public class DensityDataApplication implements CommandLineRunner {
    private final KosisSeoulDataExtractionService kosisSeoulDataExtractionService;

    @Autowired
    public DensityDataApplication(KosisSeoulDataExtractionService kosisSeoulDataExtractionService) {
        this.kosisSeoulDataExtractionService = kosisSeoulDataExtractionService;
    }

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure()
                .directory("../")
                .load();
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
        SpringApplication.run(DensityDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kosisSeoulDataExtractionService.populationDensity();
        kosisSeoulDataExtractionService.abroadMove();
        kosisSeoulDataExtractionService.internalMove();
        kosisSeoulDataExtractionService.bornDeath();
    }
}

package org.example.densitydata;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.densitydata.service.KosisSeoulDataExtractionService;
import org.example.densitydata.service.eachCountyData.CountyDataExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example"})
@EnableScheduling
public class DensityDataApplication implements CommandLineRunner {
    private final KosisSeoulDataExtractionService kosisSeoulDataExtractionService;
    private final CountyDataExtractionService countyDataExtractionService;

    @Autowired
    public DensityDataApplication(KosisSeoulDataExtractionService kosisSeoulDataExtractionService, CountyDataExtractionService countyDataExtractionService) {
        this.kosisSeoulDataExtractionService = kosisSeoulDataExtractionService;
        this.countyDataExtractionService = countyDataExtractionService;
    }

    private static final Logger logger = LogManager.getLogger(DensityDataApplication.class);

    public static void main(String[] args) {
        try {
            // Check if the .env file exists in the current directory
            File envFile = new File("./.env");

            if (envFile.exists()) {
                Dotenv dotenv = Dotenv.configure()
                        .directory("./")  // Ensure it's pointing to the correct directory
                        .load();

                dotenv.entries().forEach(entry ->
                        System.setProperty(entry.getKey(), entry.getValue())
                );
            } else {
                logger.warn(".env file not found, skipping dotenv loading.");
            }
        } catch (Exception e) {
            logger.error("An error occurred while loading .env file, if you are running via Docker, this can be ignored.");
        }


        SpringApplication.run(DensityDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kosisSeoulDataExtractionService.populationDensity();
        kosisSeoulDataExtractionService.abroadMove();
        kosisSeoulDataExtractionService.internalMove();
        kosisSeoulDataExtractionService.bornDeath();
        kosisSeoulDataExtractionService.grdp();
        countyDataExtractionService.bornData();
        countyDataExtractionService.deathData();
        countyDataExtractionService.moveData();
        countyDataExtractionService.populationData();
    }
}

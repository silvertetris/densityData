package org.example.densitydata.service.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.densitydata.service.TollgateDataExtractionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TollgateDataScheduler {
    private static final Logger LOGGER = LogManager.getLogger(TollgateDataScheduler.class);
    private final TollgateDataExtractionService tollgateDataExtractionService;

    public TollgateDataScheduler(TollgateDataExtractionService tollgateDataExtractionService) {
        this.tollgateDataExtractionService = tollgateDataExtractionService;
    }

    @Scheduled(cron = "0 59 23 * * *", zone = "Asia/Seoul")
    public void tollgateSchedule() {
        try {
            tollgateDataExtractionService.downloadSeoulData();
            LOGGER.info("[TollgateDataScheduler] API 호출 완료: {}", LocalDateTime.now());
        } catch (Exception e) {
            LOGGER.error("[TollgateDataScheduler] {}", e.getMessage());
        }
    }
}

package org.example.densitydata.service.sgisData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SgisDataExtractionService {

    private static Logger logger = LogManager.getLogger(SgisDataExtractionService.class);

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${api-key.sgis")
    private String apiKey;



}

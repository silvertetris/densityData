package org.example.densitydata.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
public class KosisSeoulDataExtractionService {
    private final Logger logger = LogManager.getLogger(KosisSeoulDataExtractionService.class);
    @Autowired
    private RestTemplate restTemplate;
    @Value("${api-key.kosis}")
    private String KOSISApiKey;

    public void populationDensity() {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do"
                + "?method=getList"
                + "&apiKey=" + KOSISApiKey
                + "&itmId=T7+&objL1=11+&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8="
                + "&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=30&orgId=101&tblId=DT_1B08024";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String responseBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        logger.info("[KosisSeoulDataExtractionService] 응답 body: {}", responseBody);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(responseBody);
            File dir = new File("../PopulationData");
            if (!dir.exists()) dir.mkdirs();
            String fileName = "kosis_populationDensity.json";
            File output = new File(dir, fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);
            logger.info("[KosisSeoulDataExtractionService] 파일 저장 성공: {}", output.getName());
        } catch (Exception e) {
            logger.error("[KosisSeoulDataExtractionService] 파일 저장 실패", e);
        }
    }

    public void abroadMove() {
        try {
            String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=" + KOSISApiKey + "&itmId=T10+&objL1=0+&objL2=0+&objL3=000+&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=30&orgId=101&tblId=DT_1B28025";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            String responseBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(responseBody);
            File dir = new File("../PopulationData");
            if (!dir.exists()) dir.mkdirs();
            String fileName = "kosis_abroadMove.json";
            File output = new File(dir, fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);
            logger.info("[abroadMove] 파일 저장 성공: {}", dir.getName());
        } catch (Exception e) {
            logger.error("[abroadMove] {}", e.getMessage());
        }
    }

    public void internalMove() {
        try {
            String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=" + KOSISApiKey + "&itmId=T25+&objL1=11+&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=360&orgId=101&tblId=DT_1B26001_A01";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            String responseBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(responseBody);
            File dir = new File("../PopulationData");
            if (!dir.exists()) dir.mkdirs();
            String fileName = "kosis_internalMove.json";
            File output = new File(dir, fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);
            logger.info("[internalMove] 파일 저장 성공: {}", dir.getName());
        } catch (Exception e) {
            logger.error("[internalMove] {}", e.getMessage());
        }
    }

    public void bornDeath() {
        try {
            String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=" + KOSISApiKey + "&itmId=T1+&objL1=11+&objL2=10+15+&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=360&orgId=101&tblId=DT_1B8000G";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            String responseBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(responseBody);
            File dir = new File("../PopulationData");
            if(!dir.exists()) dir.mkdirs();
            String fileName = "kosis_bornDeath.json";
            File output = new File(dir, fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);
            logger.info("[bornDeath] 파일 저장 성공: {}", dir.getName());
        } catch (Exception e) {
            logger.error("[bornDeath] {}", e.getMessage());
        }
    }
}

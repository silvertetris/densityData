package org.example.densitydata.service.eachCountyData;

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
public class CountyDataExtractionService {
    private Logger logger = LogManager.getLogger(CountyDataExtractionService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api-key.kosis}")
    private String apiKey;


    public void bornData() {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=" + apiKey + "&itmId=T1+&objL1=11+11010+11020+11030+11040+11050+11060+11070+11080+11090+11100+11110+11120+11130+11140+11150+11160+11170+11180+11190+11200+11210+11220+11230+11240+11250+&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=360&orgId=101&tblId=DT_1B81A01";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String responseBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(responseBody);
            File dir = new File("./PopulationData");
            if (!dir.exists()) dir.mkdirs();
            String fileName = "countyBorn_data.json";
            File output = new File(dir, fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);
            logger.info("[CountyDataExtractionService] 파일 저장 성공: {}", output.getName());
        } catch (Exception e) {
            logger.error("[CountyDataExtractionService] 파일 저장 실패", e);
        }
    }

    public void deathData() {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=" + apiKey + "&itmId=T1+&objL1=11+11010+11020+11030+11040+11050+11060+11070+11080+11090+11100+11110+11120+11130+11140+11150+11160+11170+11180+11190+11200+11210+11220+11230+11240+11250+&objL2=0+&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=360&orgId=101&tblId=DT_1B82A01";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String responseBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(responseBody);
            File dir = new File("./PopulationData");
            if (!dir.exists()) dir.mkdirs();
            String fileName = "countyDeath_data.json";
            File output = new File(dir, fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);
            logger.info("[CountyDataExtractionService] 파일 저장 성공: {}", output.getName());
        } catch (Exception e) {
            logger.error("[CountyDataExtractionService] 파일 저장 실패", e);
        }
    }

    public void moveData() {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=" + apiKey + "&itmId=T10+T20+T25+&objL1=11+11110+11140+11170+11200+11215+11230+11260+11290+11305+11320+11350+11380+11410+11440+11470+11500+11530+11545+11560+11590+11620+11650+11680+11710+11740+&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=360&orgId=101&tblId=DT_1B26001_A01";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String responseBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(responseBody);
            File dir = new File("./PopulationData");
            if (!dir.exists()) dir.mkdirs();
            String fileName = "countyImmigrate_data.json";
            File output = new File(dir, fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);
            logger.info("[CountyDataExtractionService] 파일 저장 성공: {}", output.getName());
        } catch (Exception e) {
            logger.error("[CountyDataExtractionService] 파일 저장 실패", e);
        }
    }

    public void populationData() {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=" + apiKey + "&itmId=T20+T21+T22+&objL1=11+11110+11140+11170+11200+11215+11230+11260+11290+11305+11320+11350+11380+11410+11440+11470+11500+11530+11545+11560+11590+11620+11650+11680+11710+11740+&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=360&orgId=101&tblId=DT_1B040A3";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String responseBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(responseBody);
            File dir = new File("./PopulationData");
            if (!dir.exists()) dir.mkdirs();
            String fileName = "countyPopulation_data.json";
            File output = new File(dir, fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);
            logger.info("[CountyDataExtractionService] 파일 저장 성공: {}", output.getName());
        } catch (Exception e) {
            logger.error("[CountyDataExtractionService] 파일 저장 실패", e);
        }
    }

}

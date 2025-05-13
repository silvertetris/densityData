package org.example.densitydata.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TollgateDataExtractionService {
    private static final Logger logger = LogManager.getLogger(TollgateDataExtractionService.class);

    @Value("${api-key.tollgate}")
    private String TollGateApiKey;

    public void downloadSeoulData() {
        String tollGateApiUrl = "https://data.ex.co.kr/openapi/trafficapi/trafficIc";
        try {
            /*
            key	string	필수	발급받은 인증키
            type	string	필수	검색결과 포맷
            unitCode	string	선택	영업소코드 :101 102 103
            inoutType	string	선택	입출구 구분코드(0:입구,1:출구)
            tcsType	string	선택	TCS/hi-pass 구분(1:TCS,2:hi-pass)
            carType	string	선택	차종구분코드(1:1종,2:2종,3:3종,4:4종,5:5종,6:6종,7:7종,8:8종)
            exDivCode	string	선택	도공/민자 구분코드(도공:00/이외 민자)
            numOfRows	string	선택	한 페이지당 출력건수
            pageNo	string	선택	출력 페이지번호
            tmType	string	필수	자료구분(1:1시간, 2:15분)
             */
            String encodedKey = URLEncoder.encode(TollGateApiKey, StandardCharsets.UTF_8);
            String url = tollGateApiUrl
                    + "?key=" + encodedKey
                    + "&type=json"
                    + "&tmType=1"
                    + "&unitCode=101"
                    + "&numOfRows=1000000"
                    + "&pageNo=1";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.body());
            File dir = new File("../PopulationData");
            if (!dir.exists()) dir.mkdirs();
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String fileName = "tollgateDataSet_" +now+ ".json";
            File output = new File(dir, fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);

            logger.info("[TollgateDataExtractionService] 파일 이름 생성 성공 {}", output.getName());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}

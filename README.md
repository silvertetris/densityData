# 공공데이터 추출
서울시 기준 인구밀도, 전입전출, 내외국인 이동자 수, 출생 및 사망자 수 => 최근 30년개 데이터 JSON 파일로 추출

스케쥴링을 통해 매일 서울 고속도로 톨게이트 차량 통과량 수 데이터 추출 (당일)


## required environment
jdk-21 or upper version

## 사용법

1. .env 파일 생성 후 변수 설정
- download latest release (.jar)
- create .env file next to .jar
- execute: java -jar densityData.jar
- example
```
KOSIS_API_TOKEN = your_api_token_here
```

2. docker-compose.yml 변수 설정 후 실행

- example
```
version: "3.8"
services:
  myapp:
    container_name: densitydata
    image: silvertetris/densitydata:latest
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8081:8081"
    environment:
      - KOSIS_API_TOKEN=your_api_token_here

```

## environment
```
KOSIS_API_TOKEN = #kosis_api_token_here
```

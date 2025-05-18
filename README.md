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
- example (.env)
```
KOSIS_API_TOKEN = your_api_token_here
```

2. docker-compose.yml 변수 설정 후 실행 (not working currently)

- example (docker-compose.yml)
```
services:
  densitydata:
    container_name: densitydata
    image: silvertetris/densitydata:latest
    restart: unless-stopped
    ports:
      - "8081:8081"
    environment:
      - KOSIS_API_TOKEN=your_api_token_here
```
- execute
```
!docker-compose up -d --pull always
```

## environment
```
KOSIS_API_TOKEN = #kosis_api_token_here
```

## OUTPUT
1. ./ 경로에 PopulationData 파일 생성
2. 5개의 파일 생성됨 (서울시 데이터)
- kosis_abroadMove.json : 해외 이민 순이동 (연도별)
- kosis_bornDeath.json : 출생, 사망자 수 (연도별)
- kosis_grdp.json :  지역내총생산 (연도별)
- kosis_internalMove : 지역내 순전입.전출자 수 (월별)
- kosis_populationDensity.json : 인구밀도 (연도별)
3. 데이터에 따른 출력 변수는 아래 링크 참조

  https://kosis.kr/openapi/devGuide/devGuide_0201List.do

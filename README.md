# Project Overview (프로젝트 개요)
- 프로젝트 이름: WeatherApp [Mini Project]
- 프로젝트 설명: 세계 도시의 현재 날씨 조회 앱

<br/>
<br/>

# DataBase
![image](https://github.com/user-attachments/assets/789ba817-8b72-47a8-82d5-7463b8315ee1)
<br/>
<br/>

# Key Features (주요 기능)
- **날씨 정보 조회**:
  - 도시명을 입력하면 해당 도시의 날씨 데이터를 가져옵니다.

- **도시 정보 관리**:
  - 도시를 추가하고 삭제 할 수 있습니다.

- **현재 위치 날씨 조회**:
  - 현재 위치의 날씨 정보를 조회 할 수 있습니다.
<br/>
<br/>

# Technology Stack (기술 스택)
## Frotend
- HTML
- CSS
- JavaScript
- jQuery
- AJAX

<br/>

## Backend
- Java
- Spring MVC
- MyBatis

<br/>

## Tools,Api
- STS
- OpenWeatherAPI
- GeoLocationAPI

<br/>
## DB
- Oracle

<br/>
<br/>

# 수행 결과
## 메인페이지
![image](https://github.com/user-attachments/assets/b638611f-ba32-4b32-b691-4c7507162b4b)
- 유저가 등록한 도시의 날씨 정보를 직관적으로 확인할 수 있도록 설계하였습니다. 이를 위해 현재 날씨를 나타내는 아이콘을 삽입하고, 유저가 가장 많이 확인하는 항목인 기온, 풍량, 습도, 강수확률을 포함하여 정보를 구성하였습니다.
<br/>

## 도시 추가
![image](https://github.com/user-attachments/assets/3e90f65d-9494-460f-a183-ae64a6501a9f)
- 화면 좌측 상단의 + 버튼을 클릭하여 도시명을 입력하면, OpenWeatherAPI를 통해 해당 도시의 날씨 정보를 가져와 메인 화면에 표시됩니다.
<br/>

## 도시 삭제
![image](https://github.com/user-attachments/assets/471e7c32-94b6-42ad-bd12-cb3b88cbfe73)
- 삭제하려는 도시의 정보를 선택하면 해당 도시가 간편하게 삭제됩니다. 유저의 편의성을 고려하여 동작을 최대한 간결하게 설계하였습니다.
<br/>

## 현재 위치 날씨 조회
![image](https://github.com/user-attachments/assets/6c54e0a7-8293-481e-bc2c-01e566d225ae)
- 좌측 하단에 있는 GPS 아이콘을 클릭하면 GeoLocationAPI를 이용해 현재 위치의 위도와 경도 정보를 가져옵니다. 이를 바탕으로 OpenWeatherAPI를 사용하여 현재 위치의 날씨 정보를 실시간으로 조회할 수 있도록 구현하였습니다.
<br/>

# 프로젝트 후기
- WeatherApp 프로젝트를 진행하며 평소에 어려워했던 RestAPI를 깊이 있게 공부할 수 있었습니다. 클라이언트가 Ajax를 통해 요청을 보내면, 서버가 해당 요청에 맞는 정보를 처리해 응답하는 과정을 익힐 수 있었고, 이를 통해 서버와 클라이언트 간의 상호작용에 대한 이해를 높였습니다. 또한, 날씨 정보를 제공하기 위해 적합한 API를 선정하는 것부터, API 요청 후 받은 데이터를 가공해 사용자에게 전달하는 전 과정을 직접 경험할 수 있었습니다.
아쉬운 점은 OpenWeatherAPI가 해외 API라 도시명을 영어로 입력해야 한다는 불편함이 있었던 것입니다. 향후에는 도시명을 한글과 영어로 모두 검색할 수 있도록 개선할 계획입니다.





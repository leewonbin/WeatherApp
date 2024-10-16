package com.weather.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.dto.WeatherDto;
import com.weather.service.WeatherService;

@RestController
@RequestMapping("/rest")
public class RestWeatherController {

	@Autowired
	private WeatherService w_service;

	@RequestMapping(value = "/weatherSetting", method = RequestMethod.GET)
	public ResponseEntity<?> weatherSet() throws Exception {
	    ArrayList<WeatherDto> list = w_service.listAll();
	    String apiKey = "f3076caed1b231dae89cb49a3844b2a2";
	    String urlFormat = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=" + apiKey;
	    RestTemplate restTemplate = new RestTemplate();
	    
	    // ObjectMapper를 사용하여 JSON 객체를 조작할 준비
	    ObjectMapper objectMapper = new ObjectMapper();

	    ArrayList<Object> weatherData = new ArrayList<Object>();

	    for (WeatherDto dto : list) {
	        String url = String.format(urlFormat, dto.getCity());
	        try {
	            // Make the API call
	            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
	            if (response.getStatusCode() == HttpStatus.OK) {
	                // response.getBody()를 LinkedHashMap으로 변환하여 JSON 데이터로 다룸
	                LinkedHashMap<String, Object> weatherInfo = objectMapper.convertValue(response.getBody(), LinkedHashMap.class);

	                // w_id 추가
	                weatherInfo.put("w_id", dto.getW_id());

	                // weatherData에 추가
	                weatherData.add(weatherInfo);
	            }
	        } catch (Exception e) {
	            // 예외 처리 (예: 오류 메시지 추가)
	            weatherData.add("Error fetching data for " + dto.getCity() + ": " + e.getMessage());
	        }
	    }
	    return ResponseEntity.ok(weatherData);
	}


	@RequestMapping(value = "/weatherAdd", method = RequestMethod.GET)
	public ResponseEntity<?> weatherAdd(@RequestParam String city) throws Exception {
		WeatherDto dto = new WeatherDto();
		dto.setCity(city); // 클라이언트에서 전송한 도시명을 dto에 설정

		String apiKey = "f3076caed1b231dae89cb49a3844b2a2";
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + dto.getCity() + "&appid=" + apiKey;

		RestTemplate restTemplate = new RestTemplate();

		try {
			// OpenWeather API에 요청을 보냄
			ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

			// 응답 상태가 200(OK)일 경우
			if (response.getStatusCode() == HttpStatus.OK) {
				// 도시명을 DB에 저장
				w_service.insertCity(dto.getCity());
				// 성공 응답 전송
				return new ResponseEntity<Object>("City added successfully", HttpStatus.OK);
			} else {
				// API 요청이 실패한 경우, 클라이언트에 실패 응답을 전송
				return new ResponseEntity<Object>("Failed to retrieve weather data", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// 예외가 발생하면 서버 오류로 응답
			return new ResponseEntity<Object>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/currLocation", method = RequestMethod.GET)
	public ResponseEntity<?> currLocation(@RequestParam String lat, @RequestParam String lon) throws Exception {
	    String apiKey = "f3076caed1b231dae89cb49a3844b2a2";
	    String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey;
	    
	    System.out.println("lat : " + lat);
	    System.out.println("lon : " + lon);
	    
	    // RestTemplate 인스턴스 생성
	    RestTemplate restTemplate = new RestTemplate();
	    
	    // 응답을 Map 형식으로 받음
	    ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
	    
	    // 응답에서 "name" 필드를 추출
	    Map<String, Object> responseBody = response.getBody();
	    if (responseBody != null && responseBody.containsKey("name")) {
	        String cityName = (String) responseBody.get("name");
	        w_service.insertCity(cityName);
	        return ResponseEntity.ok(cityName); // 도시 이름 반환
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City name not found");
	    }
	}
	@RequestMapping(value = "/weatherDelete", method = RequestMethod.POST)
	public ResponseEntity<?> weatherDelete(@RequestParam int wid) throws Exception {
	    try {
	        // 도시 이름으로 날씨 정보를 삭제하는 서비스 호출
	        w_service.delete(wid);

	        // 삭제 성공 시 응답
	        return ResponseEntity.ok(wid + "가(이) 성공적으로 삭제되었습니다.");
	    } catch (Exception e) {
	        // 삭제 중 예외 발생 시 오류 메시지와 함께 500 상태 코드 반환
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("삭제하는 동안 오류가 발생했습니다: " + e.getMessage());
	    }
	}

}

package com.human.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.human.dto.HumanDto;
import com.human.dto.WeatherDto;
import com.human.service.HumanService;
import com.human.service.WeatherService;

@RestController
@RequestMapping("/rest")
public class RestHumanController {

	@Autowired
	private HumanService service;

	@Autowired
	private WeatherService w_service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ArrayList<HumanDto> home() throws Exception {
		return service.selectAll();
	}

	@RequestMapping(value = "/weatherSetting", method = RequestMethod.GET)
	public ResponseEntity<?> weatherSet() throws Exception {
		ArrayList<WeatherDto> list = w_service.listAll();
		String apiKey = "f3076caed1b231dae89cb49a3844b2a2";
		String urlFormat = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();

		ArrayList<Object> weatherData = new ArrayList<Object>();

		for (WeatherDto dto : list) {
			String url = String.format(urlFormat, dto.getCity());
			try {
				// Make the API call
				ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
				if (response.getStatusCode() == HttpStatus.OK) {
					weatherData.add(response.getBody());
				}
			} catch (Exception e) {
				// Handle exceptions (e.g., log the error or add an error message)
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
	public ResponseEntity<?> weatherDelete(@RequestParam String city) throws Exception {
	    try {
	        // 도시 이름으로 날씨 정보를 삭제하는 서비스 호출
	        w_service.delete(city);

	        // 삭제 성공 시 응답
	        return ResponseEntity.ok(city + "가(이) 성공적으로 삭제되었습니다.");
	    } catch (Exception e) {
	        // 삭제 중 예외 발생 시 오류 메시지와 함께 500 상태 코드 반환
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("삭제하는 동안 오류가 발생했습니다: " + e.getMessage());
	    }
	}

}

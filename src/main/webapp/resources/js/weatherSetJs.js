/**
 * 
 */
$(function() {
	getWeather();
	
})

function getWeather() {
    $.ajax({
        url: "/ex/rest/weatherSetting",
        type: "GET",
        success: function(response) {
            console.log(response);
            // 결과를 저장할 요소를 비워줍니다.
            $('.weather_list').empty();
            
            // 날씨 데이터가 없을 경우
            if (response.length === 0) {
                // 도시가 없을 때 보여줄 HTML을 추가
                const noCityHTML = `
                    <div class="no_city_message">
                        <p class="notCity">등록된 도시가 없습니다.</p>
                        <button class="add_city_button">도시 추가하기</button>
                    </div>
                `;
                $('.weather_list').append(noCityHTML);
                
                // 도시 추가 버튼 클릭 이벤트
                $('.add_city_button').on('click', function() {
                    $('#popup').show(); // 도시 추가 팝업 표시
                });
            } else {
                // 날씨 데이터가 있을 경우 각 날씨 데이터를 반복합니다.
                response.forEach(function(weather) {
                    weatherLoad(weather); // 각 날씨 요소 추가
                });
            }
        }
    });
}


function weatherLoad(weather) {
	 const temperature = Math.floor(weather.main.temp-273); // 온도
     const description = weather.weather[0].description; // 날씨 설명
     const country = weather.sys.country;
     const location = weather.name; // 도시 이름
     const windSpeed = weather.wind.speed; // 바람 속도
     const humidity = weather.main.humidity; // 습도
     const cloudiness = weather.clouds.all; // 구름의 양
     var imgURL = weather.weather[0].icon + ".png";

     // HTML 요소를 생성합니다.
     const weatherElement = `
         <div class="weather_element">
             <div class="top_section">
                 <img src="${baseUrl}/resources/img/weatherIcon/${imgURL}" alt="날씨 아이콘" class="weather_icon" />
                 <div class="weather_info">
                     <span class="temperature">${temperature}°C</span> 
                     <span class="description"><b>${description}</b></span>
                      <span class="location">
                		<span class="city">${location}</span>, 
                		<span class="country">${country}</span>
            		  </span>
                 </div>
             </div>
             <div class="bottom_section">
                 <div class="icon_group">
                     <img src="${baseUrl}/resources/img/wind_icon.png" alt="아이콘 1" class="bottom_icon" /> 
                     <span class="value">${windSpeed} m/s</span>
                 </div>
                 <div class="icon_group">
                     <img src="${baseUrl}/resources/img/humidity_icon.png" alt="아이콘 2" class="bottom_icon" /> 
                     <span class="value">${humidity}%</span>
                 </div>
                 <div class="icon_group">
                     <img src="${baseUrl}/resources/img/cloud_icon.png" alt="아이콘 3" class="bottom_icon" /> 
                     <span class="value">${cloudiness}%</span>
                 </div>
             </div>
         </div>
     `;

     // 생성한 요소를 날씨 컨테이너에 추가합니다.
     $('.weather_list').append(weatherElement);
}

$('.add').on('click', function() {
    var addCity = $('.addInput').val(); // 입력된 도시명을 가져옴
    
    // 도시 이름이 비어 있는 경우 경고
    if (!addCity) {
        alert("도시 이름을 입력해 주세요.");
        return;
    }
	 $.ajax({
	        url: "/ex/rest/weatherAdd",
	        type: "GET",
	        data : {
	        	city : addCity
	        },
	        success: function(response) {
	            console.log("Success: ", response);
	            alert('도시 추가가 완료되었습니다 !!');
	            $('#popup').hide();
	            getWeather();
	        },
	        error: function(xhr, status, error) {
	            console.log("Error: ", error);
	            $('.popupInfo').text("도시 명을 다시 입력해주세요.");
	            $('.popupInfo').css("color","red");
	            $('.addInput').val("");
	        }
	    });
	
})
$('.plus').on('click', function() {
    $('#popup').show(); // 팝업 보여주기
});

$('.close').on('click', function() {
    $('#popup').hide(); // 팝업 숨기기
});

//weather_element를 클릭하면 confirm 창 띄우기
$(document).on('click', '.weather_element', function() {
    let weatherElement = $(this); // 클릭된 요소 참조
    
    // 해당 요소에서 city (도시) 값을 추출
    let city = weatherElement.find('.city').text(); // .city 클래스에서 도시 정보 추출

    // confirm 창 띄우기
    if (confirm("정말 " + city + "을(를) 삭제하시겠습니까?")) {
        // "예"를 누르면 Ajax 요청을 보냄
        $.ajax({
            url: "/ex/rest/weatherDelete",  // 서버로 보낼 URL
            type: "POST",                   // 요청 메서드는 POST
            data: {
                city: city                  // 도시 이름을 데이터로 전송
            },
            success: function(response) {
                // 서버 응답이 성공적이면 해당 요소 삭제
            	getWeather();
                alert(city + '이(가) 삭제되었습니다.');
            },
            error: function(xhr, status, error) {
                // 에러 처리
                console.log("Error: ", error);
                alert('삭제 중 문제가 발생했습니다.');
            }
        });
    }
});




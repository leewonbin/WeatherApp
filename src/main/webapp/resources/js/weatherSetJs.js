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
                    addBtn();
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
     const w_id = weather.w_id;
     var imgURL = weather.weather[0].icon + ".png";

     // HTML 요소를 생성합니다.
     const weatherElement = `
         <div class="weather_element">
         	<input type="hidden" value="${w_id}" class="wid"/>
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
function add (addCity) {
	console.log(addCity == null);
	// 도시 이름이 비어 있는 경우 경고
	if (!addCity) {
		Swal.fire({
			icon: 'warning',
			title: 'warning',
			text: '도시명을 적어주세요',
		});
		return;
	}
	$.ajax({
		url: "/ex/rest/weatherAdd",
		type: "GET",
		data : {
			city : addCity
		},
		success: function(response) {
			Swal.fire({
				icon: 'success',
				title: 'success',
				text: '도시 추가가 완료되었습니다.',
			});
			getWeather();
		},
		error: function(xhr, status, error) {
			Swal.fire({
				icon: 'error',
				title: 'error',
				text: '도시명을 다시 적어주세요',
			});
		}
	});
}
	
$('.plus').on('click', function() {
	addBtn();
});

function addBtn() {
	 (async () => {
	        const { value: getName } = await Swal.fire({
	            title: '도시 추가',
	            text: '추가할 도시를 입력해주세요',
	            input: 'text',
	            inputPlaceholder: '도시를 영어로 입력해주세요. (ex : 서울 -> Seoul)'
	        })

	        // 이후 처리되는 내용.
	        if (getName) {
	        	add(getName);
	        }
	    })()
}

$('.close').on('click', function() {
    $('#popup').hide(); // 팝업 숨기기
});

// weather_element를 클릭하면 confirm 창 띄우기
$(document).on('click', '.weather_element', function() {
    let weatherElement = $(this); // 클릭된 요소 참조
    
    // 해당 요소에서 city (도시) 값을 추출
    let city = weatherElement.find('.city').text(); // .city 클래스에서 도시 정보 추출
    let wid = weatherElement.find('.wid').val(); 

    // confirm 창 띄우기
    Swal.fire({
        title: city + ' 삭제',
        text: "정말 " + city + "을(를) 삭제하시겠습니까?",
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: '삭제',
        cancelButtonText: '취소',
        
      }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/ex/rest/weatherDelete",  // 서버로 보낼 URL
                type: "POST",                   // 요청 메서드는 POST
                data: {
                    wid: wid                  // 도시 이름을 데이터로 전송
                },
                success: function(response) {
                    // 서버 응답이 성공적이면 해당 요소 삭제
                	getWeather();
                	 Swal.fire({
                         icon: 'success',
                         title: '완료',
                         text: city+'이(가) 삭제되었습니다.',
                       });
                },
                error: function(xhr, status, error) {
                    // 에러 처리
                    console.log("Error: ", error);
               	 Swal.fire({
                     icon: 'error',
                     title: '실패',
                     text: city+'이(가) 삭제 실패했습니다.',
                   });
                }
            });
        }
      })
});




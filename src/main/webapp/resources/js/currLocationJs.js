$('.gps').on('click', function() {
    Swal.fire({
        title: '도시 추가',
        text: "현재 위치의 날씨를 추가하시겠습니까 ?",
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '추가',
        cancelButtonText: '취소',
        
      }).then((result) => {
        if (result.isConfirmed) {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition, showError);
            } else {
                alert("Geolocation is not supported by this browser.");
            }
        }
      })
});

function showPosition(position) {
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;
	 $.ajax({
	        url: "/ex/rest/currLocation",
	        type: "GET",
	        data : {
	        	lat : latitude,
	        	lon : longitude
	        },
	        success: function(response) {
              	 Swal.fire({
                     icon: 'success',
                     title: '성공',
                     text: '현재 위치 날씨가 추가되었습니다.',
                   });
	            $('#popup').hide();
	            getWeather();
	        },
	    });
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
            alert("User denied the request for Geolocation.");
            break;
        case error.POSITION_UNAVAILABLE:
            alert("Location information is unavailable.");
            break;
        case error.TIMEOUT:
            alert("The request to get user location timed out.");
            break;
        case error.UNKNOWN_ERROR:
            alert("An unknown error occurred.");
            break;
    }
}

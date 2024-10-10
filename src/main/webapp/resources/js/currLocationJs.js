$('.gps').on('click', function() {
    // Check if Geolocation is supported
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError);
    } else {
        alert("Geolocation is not supported by this browser.");
    }
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
	            console.log("Success: ", response);
	            alert('도시 추가가 완료되었습니다 !!');
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

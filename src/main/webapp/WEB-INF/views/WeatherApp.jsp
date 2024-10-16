<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/styles.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
</head>
<body>
	<script>
		var baseUrl = '${pageContext.request.contextPath }';
	</script>
	<div class="container">
		<div>
			<b class="plus">+</b>
		</div>
		<div class="weather_list">
			
		</div>

	</div>
	<div class="gps-container">
		<img class="gps"
			src="${pageContext.request.contextPath }/resources/img/gps.png" />
	</div>
	<script
		src="${pageContext.request.contextPath }/resources/js/weatherSetJs.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/currLocationJs.js"></script>
</body>
</html>

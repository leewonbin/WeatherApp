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

		<div id="popup" class="popup" style="display: none;">
			<div class="popup_content">
				<h2>도시 추가</h2>
				<p class="popupInfo"></p>
				<input type="text" placeholder="도시를 영어로 입력해주세요. (ex : 서울 -> Seoul)" class="addInput"/>
				<div class="button_group">
					<button class="add">추가</button>
					<button class="close">취소</button>
				</div>
			</div>
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

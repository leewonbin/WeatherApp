/**
 * 
 */

function update() {
	var data = {
			id : $('input[name="rest_id"]').val(),
			name : $('input[name="rest_name"]').val(),
			age : $('input[name="rest_age"]').val(),
			height : $('input[name="rest_height"]').val(),
			birthday : $('input[name="rest_birthday"]').val(),
	};
	console.log(data);
	 $.ajax({
         type: 'PUT', // 수정이므로 PUT 메서드 사용
         url: '/ex/rest/update', // 실제 업데이트할 API 엔드포인트
         contentType: 'application/json', // JSON 형식으로 전송
         data: JSON.stringify(data), // 데이터 객체를 JSON 문자열로 변환
         success: function(response) {
             alert(response); // 성공 메시지 표시
             location.href = '/ex/'; // 목록으로 리디렉션
         },
         error: function() {
             alert('수정에 실패했습니다.');
         }
     });
}

function humanDelete() {
	$.ajax({
        type: 'DELETE', // 수정이므로 PUT 메서드 사용
        url: '/ex/rest/'+$('input[name="rest_id"]').val(), // 실제 업데이트할 API 엔드포인트
        success: function(response) {
            alert(response); // 성공 메시지 표시
            location.href = '/ex/'; // 목록으로 리디렉션
        },
        error: function() {
            alert('삭제에 실패했습니다.');
        }
    });
	
}
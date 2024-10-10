/**
 * 
 */

$(function() {
	$.ajax({
		type : 'GET',
		url : '/ex/rest/',
		success : function(res) {
			createTable(res);
		}
	})
})

function createTable(res) {
	var tableInnerHtml = 
		"<tr><th>아이디</th>" +
		"<th>이름</th>" +
		"<th>나이</th>" +
		"<th>키</th>" +
		"<th>생년월일</th></tr>";
	var table = $('.restTable');
	table.empty(); // 테이블 초기화
	table.append(tableInnerHtml)

    // 데이터가 없을 경우 처리
    if (res.length === 0) {
    	var element = '<tr><td colspan="5">데이터가 없습니다.</td></tr>';
        table.append(element);
        return;
    }

    // 데이터를 테이블에 추가
    res.forEach(function(item) {
        var element = 
        	"<tr><td><a href='/ex/selectOne?id="+item.id+"'>"+item.id+"</a></td>"+
        	"<td>"+item.name+"</td>"+
        	"<td>"+item.age+"</td>"+
        	"<td>"+item.height+"</td>"+
        	"<td>"+item.birthday+"</td></tr>";
        table.append(element);
    });
}
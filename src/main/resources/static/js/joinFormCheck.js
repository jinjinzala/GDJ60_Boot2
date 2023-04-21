

$("#userName").blur(idDuplicateCheck);

function idDuplicateCheck(){
	//console.log("id 중복체크 실행")
	$.ajax({
		type:"GET",
		url : "./idDuplicateCheck",
		data:{
			userName: $('#userName').val() 
		},
		success : function(result){
			console.log(result)
		},
		error:function(){
			console.log('error')
		}
		
		
	})
}